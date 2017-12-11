local afc = require("access_flow_control")
local al = require("access_limit")
local s = require("service")
local sign = require("sign")
local config = require("config")
if config.isALd() == "1" then
  al.access_limit()
end
if config.isAFCd() == "1" then
  afc.access_flow_control();
end
if config.isServiced() == "1" then
  s.service()
end
if config.isSIGNd() == "1" then
  if ngx.var.isSign == "1" then
    sign.sign()
  end
end