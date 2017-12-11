local _M = {}

function _M.access_limit()
  local clientIP = ngx.req.get_headers()["X-Real-IP"]
  if clientIP == nil then
    clientIP = ngx.req.get_headers()["x_forwarded_for"]
  end
  if clientIP == nil then
    clientIP = ngx.var.remote_addr
  end
  local RedisManager = require("RedisManager")
  local is_limit = RedisManager.runCommand("get","VC:"..clientIP)
  if is_limit == nil then

  else
        ngx.exit(ngx.HTTP_FORBIDDEN)
        return
  end
  local incrKey = "user:"..clientIP..":freq"
  local blockKey = "user:"..clientIP..":block:freq"
  local opKey = "user:"..clientIP..":block:system"
  local RedisManager = require("RedisManager")
  local is_block = RedisManager.runCommand("get",opKey);
   if tonumber(is_block) == 1 then
    ngx.exit(ngx.HTTP_FORBIDDEN)
    return
  end
  is_block = RedisManager.runCommand("get",blockKey);
  if tonumber(is_block) == 1 then
    ngx.exit(ngx.HTTP_FORBIDDEN)
    return
  end
  res = RedisManager.runCommand("incr",incrKey)
  if res == 1 then
    res = RedisManager.runCommand("expire",incrKey,1)
  end
  if res > 100 then
    res = RedisManager.runCommand("set",blockKey,1)
    res = RedisManager.runCommand("expire",blockKey,3600)
  end
end

return _M