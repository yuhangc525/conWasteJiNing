package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.dao.CarRoad;
import cn.edu.bjtu.jzlj.dao.PointEntity;

//import com.alibaba.fastjson.JSONArray;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.service.CarRoadService;
import cn.edu.bjtu.jzlj.service.RoadInfoService;
import cn.edu.bjtu.jzlj.util.GetDistance;
import cn.edu.bjtu.jzlj.util.Gps;
import cn.edu.bjtu.jzlj.util.PositionTransformationUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import net.sf.json.JSONObject;
//import net.sf.json.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * geojson的生成与解析
 * https://www.cnblogs.com/aixing/p/13327381.html
 *
 */
@Api(description = "车辆报警接口")
@RestController
@RequestMapping("/vehicleAlarm")
public class vehicleOffsetAlarmController {

    @Autowired
    private RoadInfoService roadInfoService;

    @Autowired
    private CarRoadService carRoadService;

//    测试实现车辆偏移路线逻辑
    String s = "{\n" +
            "    \"type\": \"Feature\", \n" +
            "    \"properties\": { }, \n" +
            "    \"geometry\": {\n" +
            "        \"type\": \"LineString\", \n" +
            "        \"coordinates\": [\n" +
            "            [\n" +
            "                119.30705698315252, \n" +
            "                26.022566359436638\n" +
            "            ], \n" +
            "            [\n" +
            "                119.30683284057635, \n" +
            "                26.01467155980447\n" +
            "            ], \n" +
            "            [\n" +
            "                119.30239979851157, \n" +
            "                26.008320853475727\n" +
            "            ]\n" +
            "        ]\n" +
            "    }\n" +
            "}\n";

//    String t = "{\"type\":\"Feature\",\"properties\":{},\"geometry\":{\"type\":\"LineString\",\"coordinates\":[[113.568885,23.278423]]}}";


     /**
      * @Author: 田英杰
      * @Description: 解析geojson格式数据，判断车辆是否偏离路段
      * @Date 2021/4/14 10:31
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "判断车辆是否偏离路段", httpMethod = "GET")
    @GetMapping("/ifOffset")

    public int ifOffset(String s, PointEntity pointEntity) {
        try{
            //设定’圆的半径‘，超过说明暂时偏离了路线
            double setMAx = 100;

//            s = this.s;
            if (s == null){
                throw new Exception("路段数据为空");
            }
//            System.out.println("00=="+s);
            JSONObject jo = JSONObject.parseObject(s); // 将字符串转换成json格式
            JSONArray pointSet = (JSONArray) jo.getJSONObject("geometry").getJSONArray("coordinates");

            pointEntity.setPointLatitude(36.000076);
            pointEntity.setPointLongitude(116.00522);


            for (int i = 0; i < pointSet.size(); i++){
                double BDlng = ((BigDecimal)((JSONArray)pointSet.get(i)).get(0)).doubleValue();
                double BDlat = ((BigDecimal)((JSONArray)pointSet.get(i)).get(1)).doubleValue();

                //路径点的百度坐标转化为gcj02坐标（车辆位置时gcj02坐标）
                Gps roadPoint = PositionTransformationUtil.bd09_To_Gcj02(BDlat,BDlng);
                System.out.println(i + "点经度：" + roadPoint.getWgLon());
                System.out.println(i + "点纬度：" + roadPoint.getWgLat());
                //计算车辆位置点和路径上点的距离
                double res = GetDistance.getDistance(pointEntity.getPointLatitude(), pointEntity.getPointLongitude(), roadPoint.getWgLat(), roadPoint.getWgLon());
                System.out.println("距离" + i + " : " + res);
                //有一个点满足在半径之内就算没有偏移
                if (res < setMAx){
                    // 0表示没有偏移
                   return 0;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        //1表示偏移了
        return 1;
    }


     /**
      * @Author: 田英杰
      * @Description:
      * 1、明确车要走哪条路线，得到这条路线
      * 2、根据车辆的位置去调用ifOffset方法看车辆现在在没在路上
      * 3、记录状态，判断是否满足报警条件。
      * @Date 2021/4/22 10:29
      * @Param  * @param null
      * @return
      * @throws:
      **/
//     @ApiOperation(value = "记录偏移状态，判断是否报警", httpMethod = "POST")
//     @PostMapping("/ifAlarm")
//    public boolean ifAlarm(Integer roadId){
//         //获取路线信息
//        RoadInfo roadInfo = roadInfoService.getRoadInfo(roadId);
//        String roadAddress = roadInfo.getRoadAddress();
//        //获取车辆位置信息
//        PointEntity pointEntity = new PointEntity();
//        //判断是否偏离
//        boolean A = ifOffset(roadAddress, pointEntity);
//
//        return A;
//    }


    @ApiOperation(value = "根据车辆终端id获取路线id列表", httpMethod = "POST")
    @PostMapping("/getRoadList")
    public List<CarRoad> getRoadListByTerminalId(String terminalId) {

         List<CarRoad> roadList = carRoadService.getRoadListByTerminalId(terminalId);
         if (roadList.size() == 0){
             System.out.println("没有对应路线");
         }
         for (CarRoad roadId : roadList){
             System.out.println("路线id：" + roadId);

         }
         return roadList;
    }





}
