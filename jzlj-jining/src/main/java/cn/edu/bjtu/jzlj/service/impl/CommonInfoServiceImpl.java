package cn.edu.bjtu.jzlj.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONArray;
import okhttp3.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.edu.bjtu.jzlj.service.CommonInfoService;

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
        String sign = getSign();
        String url = "http://219.151.22.122:8008/openapi/authservice/login";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "JNXP");
        jsonObject.put("password", "C5SEVCjYoQPnYfIa/2keFeg7OyQO8Rl9qsU3wPhsfyc7cU3g/ByK+bDRWefx6tY+mcORNUhrQZ4ZSp5Nca3j7/hNeNmXO1aDhMOiD8d91LJ79RAdiMHOWwLiZ5TpNxfrO0r94RHcExHNyeJAb/LZKI3ZqBVd6KfTCoCg4LRIUdo=");

        RequestBody body = RequestBody.create(null, jsonObject.toJSONString());
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Sign", sign)
                .header("Accept-Encoding", "*/*")
                .header("Content-Type", "application/json")
                .post(body)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        response.headers(); //响应头
        ResponseBody responseBody = response.body();
        JSONObject resultJsonObject = JSON.parseObject(responseBody.string());
        return resultJsonObject;
    }

    @Override
    public JSONArray getQueryVehicleInfo() throws IOException {
        String token = getLogin().get("token").toString();
        String authorization = "Bearer " + token;
        String url = "http://219.151.22.122:8008/openapi/gpsservice/v1/baseVehicle/QueryVehicleInfo";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", authorization)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        response.headers(); //响应头
        ResponseBody responseBody = response.body();
        JSONArray jsonArray = JSONArray.parseArray(responseBody.string());
        return jsonArray;
    }


}
