package cn.edu.bjtu.jzlj.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;

 /**
  * @Author: 田英杰
  * @Description: 路段信息前端传参
  * @Date 2021/4/18 16:58
  * @Param  * @param null
  * @return
  * @throws:
  **/
 @Data
public class RoadInfoVo {

    private Integer roadId;

    private String roadName;

    /**
     * 路线信息
     */
    private Object roadAddress;

    private String inputName;

    private Date inputTime;

    private String content;
}
