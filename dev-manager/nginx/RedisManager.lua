RedisManager = {}

RedisManager.runCommand = {}

local metatable = {
        __call = function(table , ...)
                command = nil
                args = ''
                for key, value in ipairs({...}) do
                        if not command then 
                                command = value
                        else
                                args = args .. " " .. value;
                        end
                end
                command = command .. " " .. args .. '\r\n'
                local res = ngx.location.capture("/get_redis",{
                        args = { query = command}
                })
                local parser = require 'redis.parser' 
                return parser.parse_reply(res.body)
        end
}
setmetatable(RedisManager.runCommand, metatable);
return RedisManager;