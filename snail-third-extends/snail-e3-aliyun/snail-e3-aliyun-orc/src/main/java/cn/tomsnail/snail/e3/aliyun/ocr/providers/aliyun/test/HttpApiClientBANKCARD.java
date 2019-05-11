//
//  Created by  fred on 2017/1/12.
//  Copyright © 2016年 Alibaba. All rights reserved.
//

package cn.tomsnail.snail.e3.aliyun.ocr.providers.aliyun.test;
import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.alibaba.cloudapi.sdk.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.enums.WebSocketApiType;




public class HttpApiClientBANKCARD extends ApacheHttpClient implements Post{
    public final static String HOST = "yhk.market.alicloudapi.com";
    static HttpApiClientBANKCARD instance = new HttpApiClientBANKCARD();
    public static HttpApiClientBANKCARD getInstance(){return instance;}

    public void init(HttpClientBuilderParams httpClientBuilderParams){
        httpClientBuilderParams.setScheme(Scheme.HTTP);
        httpClientBuilderParams.setHost(HOST);
        super.init(httpClientBuilderParams);
    }




    public void post(byte[] body , ApiCallback callback) {
        String path = "/rest/160601/ocr/ocr_bank_card.json";
        ApiRequest request = new ApiRequest(HttpMethod.POST_BODY , path, body);
        


        sendAsyncRequest(request , callback);
    }
}