# 数据服务_5_1_身份证识别
---------------------------------------
<br />

数据服务_5_1_身份证识别

## API列表

<div>
    <table>
        <tr>
            <th >API名称</th>
            <th >认证方式</th>
            <th >描述</th>
        </tr>
            <tr>
                <td>印刷文字识别_身份证识别</td>
                <td>APP</td>
                <td>身份证识别服务可以自动地从图片中定位身份证图片区域，识别出其中包含的身份信息。详见：https://market.aliyun.com/products/57002003/cmapi010401.html</td>
            </tr>
    </table>
</div>

## API调用
### 公共入参
公共请求参数是指每个接口都需要使用到的请求参数。

参数名称 | 位置 |必须 | 描述
-------|------|--------|----
X-Ca-Key |Header| 是  | Appkey，调用API的身份标识，可以到阿里云[API网关控制台](https://apigateway.console.aliyun.com/#/apps/list)申请
X-Ca-Signature | Header| 是  | 通过签名计算规则计算的请求签名串，参照：<a href="#Signature">签名计算规则</a>
X-Ca-Timestamp | Header| 否  | API 调用者传递时间戳，值为当前时间的毫秒数，也就是从1970年1月1日起至今的时间转换为毫秒，时间戳有效时间为15分钟
X-Ca-Nonce|Header| 否  |API请求的唯一标识符，15分钟内同一X-Ca-Nonce不能重复使用，建议使用 UUID，结合时间戳防重放
Content-MD5|Header| 否  |当请求 Body 非 Form 表单时，需要计算 Body 的 MD5 值传递给云网关进行 Body MD5 校验
X-Ca-Signature-Headers|Header|否|指定哪些Header参与签名，支持多值以","分割，默认只有X-Ca-Key参与签名，为安全需要也请将X-Ca-Timestamp、X-Ca-Nonce进行签名，例如：X-X-Ca-Signature-Headers:Ca-Timestamp,X-Ca-Nonce

### <span id="Signature">签名计算规则</span>
请求签名，是基于请求内容计算的数字签名，用于API识别用户身份。客户端调用API时，需要在请求中添加计算的签名（X-Ca-Signature）。
#### 签名计算流程
_________________________________________________________
准备APPkey → 构造待签名字符串stringToSign → 使用Secret计算签名
_________________________________________________________

##### 1.准备APPKey
Appkey，调用API的身份标识，可以到阿里云[API网关控制台](https://apigateway.console.aliyun.com/#/apps/list)申请

##### 2.构造待签名字符串stringToSign

````
String stringToSign=
HTTPMethod + "\n" +
Accept + "\n" +                //建议显示设置 Accept Header。当 Accept 为空时，部分 Http 客户端会给 Accept 设置默认值为 */*，导致签名校验失败。
Content-MD5 + "\n"
Content-Type + "\n" +
Date + "\n" +
Headers +
Url
````

###### HTTPMethod
为全大写，如 POST。

````
Accept、Content-MD5、Content-Type、Date 如果为空也需要添加换行符”\n”，Headers如果为空不需要添加”\n”。
````

###### Content-MD5

Content-MD5 是指 Body 的 MD5 值，只有当 Body 非 Form 表单时才计算 MD5，计算方式为：

String content-MD5 = Base64.encodeBase64(MD5(bodyStream.getbytes("UTF-8")));
bodyStream 为字节数组。

###### Headers

Headers 是指参与 Headers 签名计算的 Header 的 Key、Value 拼接的字符串，建议对 X-Ca 开头以及自定义 Header 计算签名，注意如下参数不参与 Headers 签名计算：X-Ca-Signature、X-Ca-Signature-Headers、Accept、Content-MD5、Content-Type、Date。

###### Headers 组织方法：
先对参与 Headers 签名计算的 Header的Key 按照字典排序后使用如下方式拼接，如果某个 Header 的 Value 为空，则使用 HeaderKey + “:” + “\n”参与签名，需要保留 Key 和英文冒号。

````
String headers =
HeaderKey1 + ":" + HeaderValue1 + "\n"\+
HeaderKey2 + ":" + HeaderValue2 + "\n"\+
...
HeaderKeyN + ":" + HeaderValueN + "\n"
````

将 Headers 签名中 Header 的 Key 使用英文逗号分割放到 Request 的 Header 中，Key为：X-Ca-Signature-Headers。

###### Url

Url 指 Path + Query + Body 中 Form 参数，组织方法：对 Query+Form 参数按照字典对 Key 进行排序后按照如下方法拼接，如果 Query 或 Form 参数为空，则 Url = Path，不需要添加 ？，如果某个参数的 Value 为空只保留 Key 参与签名，等号不需要再加入签名。

````
String url =
Path +
"?" +
Key1 + "=" + Value1 +
"&" + Key2 + "=" + Value2 +
...
"&" + KeyN + "=" + ValueN
````

注意这里 Query 或 Form 参数的 Value 可能有多个，多个的时候只取第一个 Value 参与签名计算。

##### 3.使用Secret计算签名

````
Mac hmacSha256 = Mac.getInstance("HmacSHA256");
byte[] keyBytes = secret.getBytes("UTF-8");
hmacSha256.init(new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacSHA256"));
String sign = new String(Base64.encodeBase64(Sha256.doFinal(stringToSign.getBytes("UTF-8")),"UTF-8"));
````

Secret 为 APP 的密钥，请在[应用管理](https://apigateway.console.aliyun.com/#/apps/list)中获取。



## API名称：印刷文字识别_身份证识别

### *描述*

身份证识别服务可以自动地从图片中定位身份证图片区域，识别出其中包含的身份信息。详见：https://market.aliyun.com/products/57002003/cmapi010401.html

### *请求信息*

HTTP协议：HTTP,HTTPS

调用地址：dm-51.data.aliyun.com/rest/160601/ocr/ocr_idcard.json

方法：POST

<br />
### *请求参数*

<div>
<table>
<tr>
<th style="width: 20%;">名称</th>
<th style="width: 10%;">位置</th>
<th style="width: 10%;">类型</th>
<th style="width: 10%;">必填</th>
<th style="width: 30%;">描述</th>
</tr>
</table>
</div>

<br />
### *请求Body描述(非Form表单数据)*
{
            "image":  "图片二进制数据的base64编码",
            "configure": "{\"side\":\"face\"}"  #身份证正反面类型:face/back
}
<br />
### *返回信息*

#### 返回参数类型

JSON

#### 返回结果示例

````
正面返回结果：
{
    
	"address"    : "浙江省杭州市余杭区文一西路969号",   #地址信息
	"config_str" : "{\\\"side\\\":\\\"face\\\"}",                #配置信息，同输入configure
	"face_rect":{
		"angle": -90,
		"center":{
			"x" : 952,
			"y" : 325.5
		},
		"size":{
			"height":181.99,
			"width":164.99
			}
	},                          #人脸位置，center表示人脸矩形中心坐标，size表示人脸矩形长宽，angle表示矩形顺时针旋转的度数。
	"name" : "张三",                                  #姓名
	"nationality": "汉"， #民族 
	"num" : "1234567890",                             #身份证号
	"sex" : "男",                                     #性别
	"birth" : "20000101",                             #出生日期
	"nationality" : "汉",                             #民族
	"success" : true                                  #识别结果，true表示成功，false表示失败
}


反面返回结果:
{
    "config_str" : "{\\\"side\\\":\\\"back\\\"}",#配置信息，同输入configure
    "start_date" : "19700101",       #有效期起始时间
    "end_date" : "19800101",         #有效期结束时间
    "issue" : "杭州市公安局",         #签发机关
    "success" : true                   #识别结果，true表示成功，false表示失败
}
````

#### 异常返回示例

````

````

<br />
### *错误码*

<div>
<table>
<tr>
<th style="width: 15%;">错误码</th>
<th style="width: 20%;">错误信息</th>
<th style="width: 25%;">描述</th>
</tr>
<tr>
<td>公共错误码</td>
<td>--</td>
<td>所有API公用的错误码，请参照《 <a href="#pubError">公共错误码</a> 》</td>
</tr>
</table>
</div>




## <span id='pubError'>公共错误</span>
### 如何获取公共错误
所有的 API 请求只要到达了网关，网关就会返回请求结果信息。

用户需要查看返回结果的头部，即 Header 部分。返回参数如示例：

	//请求唯一ID，请求一旦进入API网关应用后，API网关就会生成请求ID并通过响应头返回给客户端，建议客户端与后端服务都记录此请求ID，可用于问题排查与跟踪
	X-Ca-Request-Id: 7AD052CB-EE8B-4DFD-BBAF-EFB340E0A5AF

	//API网关返回的错误消息，当请求出现错误时API网关会通过响应头将错误消息返回给客户端
	X-Ca-Error-Message: Invalid Url

	//当打开Debug模式后会返回Debug信息，此信息后期可能会有变更，仅用做联调阶段参考
	X-Ca-Debug-Info: {"ServiceLatency":0,"TotalLatency":2}

在 Header 中获得 X-Ca-Error-Message 可以基本明确报错原因，而 X-Ca-Request-Id 可以用于提供给这边的支持人员，供支持人员搜索日志。
### 公共错误码
#### 客户端错误

错误代码|Http 状态码|语义|解决方案
------|-----------|---|------
Throttled by USER Flow Control|403|因用户流控被限制|调用频率过高导致被流控，可以联系 API 服务商协商放宽限制。
Throttled by APP Flow Control|403|因APP流控被限制|调用频率过高导致被流控，可以联系 API 服务商协商放宽限制。
Throttled by API Flow Control|403	|因 API 流控被限制|调用频率过高导致被流控，可以联系 API 服务商协商放宽限制。
Throttled by DOMAIN Flow Control	|403|	因二级域名流控被限制|直接访问二级域名调用 API，每天被访问次数上限1000次。
TThrottled by GROUP Flow Control|403|因分组流控被限制|调用频率过高导致被流控，可以联系 API 服务商协商放宽限制。
Quota Exhausted	|403|	调用次数已用完	|购买的次数已用完。
Quota Expired	|403|	购买次数已过期	|购买的次数已经过期。
User Arrears	|403|	用户已欠费	|请尽快充值续费。
Empty Request Body	|400|	body 为空|	请检查请求 Body 内容。
Invalid Request Body	|400	|body 无效	|请检查请求 Body。
Invalid Param Location	|400|	参数位置错误|请求参数位置错误。
Unsupported Multipart	|400|	不支持上传|不支持上传文件。
Invalid Url	|400	|Url 无效	|请求的 Method、Path 或者环境不对。请参照错误说明 Invalid Url。
Invalid Domain	|400|	域名无效	|请求域名无效，根据域名找不到 API。请联系 API 服务商。
Invalid HttpMethod	|400	|HttpMethod 无效|输入的 Method 不合法。
Invalid AppKey|400|AppKey 无效或不存在	|请检查传入的 AppKey。注意左右空格的影响。
Invalid AppSecret	|400	|APP 的Secret 错误|	检查传入的 AppSecret。注意左右空格的影响。
Timestamp Expired|400|时间戳过时|请核对请求系统时间是否为标准时间。
Invalid Timestamp	|400|	时间戳不合法|请参照 请求签名说明文档。
Empty Signature	|404|签名为空|请传入签名字符串，请参照 请求签名说明文档。
Invalid Signature, Server StringToSign:%s|400|签名无效|签名无效，参照 Invalid Signature 错误说明
Invalid Content-MD5|400|	Content-MD5 值不合法|请求 Body 为空，但传入了 MD5 值，或 MD5 值计算错误。请参照 请求签名说明文档。
Unauthorized	|403|	未被授权|	APP 未获得要调用的 API 的授权。请参照错误说明 Unauthorized。
Nonce Used|400|	SignatureNonce| 已被使用|SignatureNonce 不能被重复使用。
API Not Found|	400	|找不到 API|传入的APIdi地址或者HttpMethod不正确，或已下线。

#### 服务器端错误（调用 API）
以下为API服务端错误，如果频繁错误，可联系服务商。

错误代码|Http状态码|语义|解决方案
------|----------|---|----
Internal Error	|500	|内部错误|建议重试,或者联系服务商
Failed To Invoke Backend Service	|500|	底层服务错误|API 提供者底层服务错误，建议重试，如果重试多次仍然不可用，可联系 API 服务商解决
Service Unavailable|503|	服务不可用	|建议重试,或者联系服务商
Async Service	|504	|后端服务超时|建议重试,或者联系服务商

