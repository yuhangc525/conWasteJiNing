package cn.edu.bjtu.jzlj.service.impl;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.edu.bjtu.jzlj.service.CommonInfoService;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author wangzhijin <wangzhijin@kuaishou.com>
 * Created on 2021-08-11
 */
@Service
public class CommonInfoServiceImpl implements CommonInfoService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CommonInfoServiceImpl.class);

    @Override
    public JSONObject getGetRequestContent(String url) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        response.headers(); //响应头
        ResponseBody responseBody = response.body();
        JSONObject jsonObject = JSON.parseObject(responseBody.string());
        return jsonObject;
    }

    @Override
    public String getSign() {
        String url = "http://219.151.22.122:8008/openapi/authservice/v1/config";
        String result = null;
        try {
            JSONObject response = getGetRequestContent(url);
            if(response != null) result = response.get("sign").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public JSONObject getLogin() throws IOException {
        MediaType json = MediaType.parse("application/json;");
        String sign = getSign();
        sign = "www";
        String url = "http://219.151.22.122:8008/openapi/authservice/login";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "济宁鹏翔");
        jsonObject.put("password", "C5SEVCjYoQPnYfIa/2keFeg7OyQO8Rl9qsU3wPhsfyc7cU3g/ByK+bDRWefx6tY+mcORNUhrQZ4ZSp5Nca3j7/hNeNmXO1aDhMOiD8d91LJ79RAdiMHOWwLiZ5TpNxfrO0r94RHcExHNyeJAb/LZKI3ZqBVd6KfTCoCg4LRIUdo=");
        String jsonObjectStr = jsonObject.toJSONString();
        RequestBody body = RequestBody.create(json, jsonObjectStr);
//        RequestBody formBody = new FormBody.Builder()
//                .add("username", "济宁鹏翔")
//                .add("password", "Z8d+TYngTI5qI27rAc+XxPddgwhTy8Xcn42EjRsUM8adxJ3h/Lenl5c49BAAVXyRqEZRVegwBcVcOBDVfsbgGhga9eMzte7PzK/O8aakshNXxj7/4l2QR/k8Q1uRRzXiwqeHXYgHSmDYoovbuZ0O4kc3yZyuo/cOVIWpe4ZHdcQ=")
//                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
//                .addHeader("Sign", sign)
//                .header("Sign", sign)
//                .addHeader("Content-Type", "application/json")
//                .header("Content-Type", "application/json")
//                .post(body)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        response.headers(); //响应头
        ResponseBody responseBody = response.body();
        JSONObject resultJsonObject = JSON.parseObject(responseBody.string());
        return resultJsonObject;
    }


}
