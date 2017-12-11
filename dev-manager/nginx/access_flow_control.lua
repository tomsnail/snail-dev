local _M = {}


function wait()
  ngx.sleep(1)
end

function _M.access_flow_control()
  local RedisManager = require("RedisManager")
  local uri = ngx.var.uri
  local uriKey = "req:uri:"..uri
  res = RedisManager.runCommand("incr",uriKey)
  if res ==1 then
    local resexpire = RedisManager.runCommand("expire",uriKey,1)
  end
  
  local resCount = RedisManager.runCommand("get","req:"..uri)
  if resCount == nil then
    resCount = 200
  end
  
  if resCount == -1 then
    return;
  end
  
  while (res > resCount)
  do
    local twait, err = ngx.thread.spawn(wait)
    ok, threadres = ngx.thread.wait(twait)
    if not ok then
      ngx_log(ngx_ERR, "wait sleep error: ", err)
      ngx.exit(ngx.HTTP_FORBIDDEN)
      return
    end
    res = RedisManager.runCommand("incr",uriKey)
    if res == 1 then
      local resexpire = RedisManager.runCommand("expire",uriKey,1)
    end
  end
end

return _M
