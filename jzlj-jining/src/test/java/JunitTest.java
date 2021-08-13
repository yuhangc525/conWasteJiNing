import java.io.IOException;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import cn.edu.bjtu.jzlj.service.impl.CommonInfoServiceImpl;


/**
 * @author wangzhijin <wangzhijin@kuaishou.com>
 * Created on 2021-08-11
 */
public class JunitTest  {

    @Test
    public void test() throws IOException {
        CommonInfoServiceImpl service = new CommonInfoServiceImpl();
        String temp = service.getSign();
        System.out.println(temp);
        JSONObject get = service.getLogin();
        System.out.println(get.toJSONString());

    }


}
