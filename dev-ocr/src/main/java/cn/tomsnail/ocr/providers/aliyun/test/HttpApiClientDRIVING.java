//
//  Created by  fred on 2017/1/12.
//  Copyright © 2016年 Alibaba. All rights reserved.
//

package cn.tomsnail.ocr.providers.aliyun.test;
import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.alibaba.cloudapi.sdk.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.enums.WebSocketApiType;




public class HttpApiClientDRIVING extends ApacheHttpClient  implements Post{
    public final static String HOST = "dm-52.data.aliyun.com";
    static HttpApiClientDRIVING instance = new HttpApiClientDRIVING();
    public static HttpApiClientDRIVING getInstance(){return instance;}

    public void init(HttpClientBuilderParams httpClientBuilderParams){
        httpClientBuilderParams.setScheme(Scheme.HTTP);
        httpClientBuilderParams.setHost(HOST);
        super.init(httpClientBuilderParams);
    }




    public void post(byte[] body , ApiCallback callback) {
        String path = "/rest/160601/ocr/ocr_driver_license.json";
        ApiRequest request = new ApiRequest(HttpMethod.POST_BODY , path, body);
        


        sendAsyncRequest(request , callback);
    }
}