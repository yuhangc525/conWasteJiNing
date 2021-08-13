package cn.edu.bjtu.jzlj.service;

import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * @author wangzhijin <wangzhijin@kuaishou.com>
 * Created on 2021-08-11
 */
public interface CommonInfoService {

    JSONObject getGetRequestContent(String url) throws IOException;

    String getSign();

    JSONObject getLogin() throws IOException;

    JSONArray getQueryVehicleInfo() throws IOException;

}
