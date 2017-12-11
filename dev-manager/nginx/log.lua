local config = require("config")


if config.isLogd() == "0" then
  return
end

local cjson = require "cjson"
local producer = require "resty.kafka.producer"
local broker_list = {
  { host = config.kafkaAddress(), port = config.kafkaPort() },
}
local log_json = {}


local isLogger = ngx.var.isLogger;

if isLogger == nil then
   return
end

if isLogger == '0' then
  return
end

log_json["uri"]=ngx.var.uri
log_json["host"]=ngx.var.host
log_json["user_id"] = ngx.var.user_id
log_json["user_name"] = ngx.var.user_name
log_json["remote_addr"] = ngx.var.remote_addr
log_json["remote_user"] = ngx.var.remote_user
log_json["request_body"]=ngx.var.request_body
log_json["resp_body"]=ngx.var.resp_body
log_json["time_local"] = ngx.var.time_local
log_json["status"] = ngx.var.status
log_json["request_time"] = ngx.var.request_time
log_json["http_referer"] = ngx.var.http_referer
log_json["http_user_agent"] = ngx.var.http_user_agent
log_json["http_x_forwarded_for"] = ngx.var.http_x_forwarded_for
local message = cjson.encode(log_json);

if config.isLocalLogd() == "1" then
        ngx.log(ngx.ERR,"",message)
else
        local bp = producer:new(broker_list, { producer_type = "async" })
        local ok, err = bp:send("nginxlog", nil, message)

        if not ok then
                ngx.log(ngx.INFO, "kafka send err:", err)
        end
end