local _M = {}

local isAL = "1"
local isAFC = "0"
local isSIGN = "0"
local isService = "1"
local isLog = "1"
local isLocalLog = "1"

local kafkaAddress="IP Address"
local kafkaPort=8457


function _M.isALd()
  return isAL;
end

function _M.isAFCd()
  return isAFC;
end

function _M.isSIGNd()
  return isSIGN;
end

function _M.isServiced()
  return isService;
end

function _M.isLogd()
    return isLog;
end

function _M.isLocalLogd()
   return isLocalLog;
end

function _M.kafkaPort()
    return kafkaPort;
end

function _M.kafkaAddress()
    return kafkaAddress;
end


return _M