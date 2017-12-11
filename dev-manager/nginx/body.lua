
if ngx.var.log_level == '1' then
        return
end

local resp_body = string.sub(ngx.arg[1], 1, 1000)
ngx.ctx.buffered = (ngx.ctx.buffered or"")..resp_body
if ngx.arg[2] then
    ngx.var.resp_body = ngx.ctx.buffered
end