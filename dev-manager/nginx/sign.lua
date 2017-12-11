local _M = {}

function _M.sign()
  local cjson = require "cjson"
  ngx.req.read_body()
  local args = ngx.req.get_body_data()
  if args == nil then
    ngx.exit(ngx.HTTP_FORBIDDEN)
    return
  else
  end
  local jsonbody = cjson.decode(args)
  local RedisManager = require("RedisManager")
  local token =  RedisManager.runCommand("get","token:user:"..jsonbody["key"])
  if token == nil then
    ngx.exit(ngx.HTTP_FORBIDDEN)
    return
  end
  local fingerprint = jsonbody["fingerprint"]
  jsonbody["fingerprint"] = undefined
  local str = cjson.encode(jsonbody)
  local f = ngx.hmac_sha1(token,str)
  local now_f = ngx.encode_base64(f);
  if now_f == fingerprint then
  else
    ngx.exit(ngx.HTTP_FORBIDDEN)
  end
end

return _M