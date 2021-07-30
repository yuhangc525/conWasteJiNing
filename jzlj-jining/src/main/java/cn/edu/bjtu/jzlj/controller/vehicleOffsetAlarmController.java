package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.dao.*;

import cn.edu.bjtu.jzlj.mapper.TRealtimePositionMapper;
import cn.edu.bjtu.jzlj.redis.RedisConfig;
import cn.edu.bjtu.jzlj.service.CarAlarmService;
import cn.edu.bjtu.jzlj.service.CarRouteService;
import cn.edu.bjtu.jzlj.service.RoadInfoService;
import cn.edu.bjtu.jzlj.service.RouteInfoService;
import cn.edu.bjtu.jzlj.util.GetDistance;
import cn.edu.bjtu.jzlj.util.Gps;
import cn.edu.bjtu.jzlj.util.MapUtils;
import cn.edu.bjtu.jzlj.util.PositionTransformationUtil;
import cn.edu.bjtu.jzlj.util.results.Resp;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * geojson的生成与解析
 * https://www.cnblogs.com/aixing/p/13327381.html
 *
 */
@Api(description = "车辆报警接口")
@RestController
@RequestMapping("/vehicleAlarm")
public class vehicleOffsetAlarmController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private RoadInfoService roadInfoService;

    @Autowired
    private CarRouteService carRouteService;

    @Autowired
    private CarAlarmService carAlarmService;

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
//    @ApiOperation(value = "判断车辆是否偏离路段", httpMethod = "GET")
//    @GetMapping("/ifOffset")

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




    @ApiOperation(value = "根据车辆终端id获取路线id列表", httpMethod = "POST")
    @PostMapping("/getRoadList")
    public List<CarRoute> getRoadListByTerminalId(String terminalId) {

        List<CarRoute> roadList = carRouteService.getRouteListByTerminalId(terminalId);
        if (roadList.size() == 0){
            System.out.println("没有对应路线");
        }
        for (CarRoute roadId : roadList){
            System.out.println("路线id：" + roadId);

        }
        return roadList;
    }


    @ApiOperation(value = "增加车辆--路线对应关系信息", httpMethod = "POST")
    @PostMapping("/insertCarRoadInfo")
    public Resp insertCarRoadInfo(@RequestBody CarRoute carRoute){
        long startTime = System.currentTimeMillis();
        if (null == carRoute) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
            carRouteService.insertCarRouteInfo(carRoute);
            long endTime = System.currentTimeMillis();
            LOGGER.info("增加车辆--路线对应关系信息成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("增加车辆--路线对应关系信息成功", Resp.STRING, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("增加车辆--路线对应关系信息失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("增加车辆--路线对应关系信息异常，原因：" + e.getMessage(), Resp.SINGLE, carRoute);
        }
    }




    @ApiOperation(value = "批量插入车辆路线绑定信息", httpMethod = "POST")
    @PostMapping("/insertMCarRoute")
    @ResponseBody
    public Resp insertMCarRoute(@RequestBody List<CarRoute> carRoutes) {
        long startTime = System.currentTimeMillis();
        try {
            carRouteService.insertMCarRoute(carRoutes);
            long endTime =System.currentTimeMillis();
            LOGGER.info("批量插入车辆路线信息成功，用时:" + (endTime - startTime));
            return Resp.getInstantiationSuccess("批量插入车辆路线信息成功",Resp.STRING,null);
        } catch (Exception e) {
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            LOGGER.error("批量插入车辆路线信息失败，原因：" + e.getMessage() +",用时:" + (endTime - startTime));
            return Resp.getInstantiationError("批量插入车辆路线信息失败",Resp.STRING,null);
        }
    }




    @ApiOperation(value = "查询路线已绑定的车辆信息", httpMethod = "GET")
    @GetMapping("/getAllLinkedCarRoute")
    @ResponseBody
    public Resp getAllLinkedCarRoute(@RequestParam(value = "routeID") Integer routeId){
        long startTime = System.currentTimeMillis();
        try {
            List<String> carNoList = carRouteService.getAllLinkedCarRoute(routeId);
            long endTime =System.currentTimeMillis();
            LOGGER.info("查询路线已绑定车辆信息成功，用时:" + (endTime - startTime));
            return Resp.getInstantiationSuccess("查询路线已绑定车辆信息成功",Resp.LIST,carNoList);
        } catch (Exception e) {
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            LOGGER.error("查询路线已绑定车辆信息失败，原因：" + e.getMessage() +",用时:" + (endTime - startTime));
            return Resp.getInstantiationError("查询路线已绑定车辆信息失败",Resp.STRING,null);
        }
    }




    @ApiOperation(value = "插入车辆报警信息", httpMethod = "POST")
    @PostMapping("/insertCarAlarmInfo")
    public Resp insertCarAlarmInfo(@RequestBody  CarAlarm carAlarm){
//        System.out.println(carAlarm);
        long startTime = System.currentTimeMillis();
        if (null == carAlarm) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
                carAlarmService.insertCarAlarmInfo(carAlarm);
                long endTime = System.currentTimeMillis();
                LOGGER.info("插入车辆报警信息成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("插入车辆报警信息成功", Resp.STRING, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("插入车辆报警信息失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("创建异常，原因：" + e.getMessage(), Resp.SINGLE, carAlarm);
        }

    }



     /**
      * @Author: 田英杰
      * @Description: 获取需要处理的车辆报警信息
      * @Date 2021/4/29 17:15
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "获取未处理的报警信息", httpMethod = "GET")
    @GetMapping("/getAllUnHandle")
    public Resp getAllUnHandle(@RequestParam(defaultValue = "1") Integer pageNo,
                               @RequestParam(defaultValue = "10")Integer pageSize){
        long startTime = System.currentTimeMillis();
        try {
            IPage<CarAlarm> carAlarmIPage = carAlarmService.getAllUnHandle(pageNo,pageSize);
            long endTime = System.currentTimeMillis();
            LOGGER.info("获取未处理的车辆报警信息成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("获取未处理的车辆报警信息成功", Resp.LIST, carAlarmIPage);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("获取未处理的车辆报警信息失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("获取未处理的车辆报警信息异常，原因：" + e.getMessage(), Resp.SINGLE,null);
        }

    }


     /**
      * @Author: 田英杰
      * @Description: 获取处理完毕的报警信息
      * @Date 2021/5/17 14:40
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "获取已处理的报警信息", httpMethod = "GET")
    @GetMapping("/getAllHandled")
    public Resp getAllHandled(@RequestParam(defaultValue = "1") Integer pageNo,
                               @RequestParam(defaultValue = "10")Integer pageSize){
        long startTime = System.currentTimeMillis();
        try {
            IPage<CarAlarm> carAlarmIPage = carAlarmService.getAllHandled(pageNo,pageSize);
            long endTime = System.currentTimeMillis();
            LOGGER.info("获取已处理的车辆报警信息成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("获取已处理的车辆报警信息成功", Resp.LIST, carAlarmIPage);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("获取已处理的车辆报警信息失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("获取已处理的车辆报警信息异常，原因：" + e.getMessage(), Resp.SINGLE,null);
        }

    }


     /**
      * @Author: 田英杰
      * @Description: 处理报警信息，也就是改变handle的状态，同时修改status（正常报警还是误报）和备注信息
      * @Date 2021/4/29 17:14
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "处理报警信息", httpMethod = "POST")
    @PostMapping("/handleCarAlarm")
    public Resp handleCarAlarm(@RequestParam(value = "CarAlarmId") Integer id,
                               @RequestParam(value = "updateUser") String updateUser,
                               @RequestParam(value = "status") Integer status,
                               @RequestParam(value = "remarks") String remarks){
        long startTime = System.currentTimeMillis();
        Date updateTime = new Date();
        try {
            carAlarmService.handleCarAlarm(id, updateUser, updateTime, status, remarks);
            long endTime = System.currentTimeMillis();
            LOGGER.info("处理车辆报警信息成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("处理车辆报警信息成功", Resp.STRING, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("处理车辆报警信息失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("处理车辆报警信息异常，原因：" + e.getMessage(), Resp.SINGLE, null);
        }

    }



    @ApiOperation(value = "根据多个id处理报警信息", httpMethod = "GET")
    @GetMapping("/handleMCarAlarm")
    @ResponseBody
//    @RequestParam(value = "updateTime") Date updateTime
    public Resp  handleMCarAlarm(@RequestParam(value = "CarAlarmId") List<Integer> id,
                                 @RequestParam(value = "updateUser") String updateUser,
                                 @RequestParam(value = "status") Integer status,
                                 @RequestParam(value = "remarks") String remarks
                                 ){
        long startTime = System.currentTimeMillis();
        try {
            Date updateTime = new Date();
            carAlarmService.handleMCarAlarm(id, updateUser, updateTime, status, remarks);
            long endTime =System.currentTimeMillis();
            LOGGER.info("根据多个id处理报警信息成功，用时：" + (endTime - startTime));
            return Resp.getInstantiationSuccess("根据多个id处理报警信息成功",Resp.STRING,null);
        } catch (Exception e){
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据多个id处理报警信息失败，原因：" + e.getMessage() +",用时:" + (endTime - startTime));
            return Resp.getInstantiationError("根据多个id处理报警信息失败",Resp.STRING,null);
        }
    }









    @Autowired
    TRealtimePositionMapper tRealtimePositionMapper;

    @Autowired
    RouteInfoService routeInfoService;

    private static RedisConfig redisConfig;

    @Resource
    public void setRedisConfig(RedisConfig redisConfig) {
        vehicleOffsetAlarmController.redisConfig = redisConfig;
    }

    /**
     * 模拟终端上报gps文件为：19ok_gps.bin（在北京南四环）
     * @throws Exception
     */
//    @Scheduled(cron = "*/10 * * * * ?")
    public void testAlarm() throws Exception {
        System.out.println("定时查村车辆实时位置表成功");
        TRealtimePosition info = tRealtimePositionMapper.getOneList();
        giveAnAlarmTest(info);

    }

    public void giveAnAlarmTest (TRealtimePosition carRealTime_msg) throws Exception {


        String terminalId = carRealTime_msg.getTerminalId();
        Date updateTime = carRealTime_msg.getUptime();

        String terminalIdKey = terminalId + "-key";
        String terminalRouteKey = terminalId + "-route";

        //获取车辆的经纬度坐标
        double lat = carRealTime_msg.getLat().doubleValue();
        double lon = carRealTime_msg.getLon().doubleValue();

        PointEntity pointEntity = new PointEntity();

        List<CarRoute> routeList = carRouteService.getRouteListByTerminalId(terminalId);

        if (routeList.size() == 0 || routeList == null){
            LOGGER.info("车辆终端id为: " + terminalId + "的车辆没有对应的路线！");
//                System.out.println("车辆终端id为：" + terminalId + "的车辆没有对应的路线！");
        } else {
            //车辆实时位置点信息
            pointEntity.setPointLongitude(lon);
            pointEntity.setPointLatitude(lat);
            System.out.println(pointEntity);
            //onRoad  记录车辆是否偏移了路线，0表示偏移了，大于0说明没有偏移
            int onRoad = 0;
            for (CarRoute routeId : routeList ) {
                int oneRoadId = routeId.getRouteId();
                //获取路线信息
                RouteInfo routeInfo = routeInfoService.getOneRouteInfoByRouteId(oneRoadId);
                if (routeInfo == null) {
                    throw new Exception("路线id为：" + oneRoadId + "的路线不存在");
                }
                String roadAddress = routeInfo.getLngLat();
                onRoad += pointIfOffsetRoad(roadAddress, pointEntity);
//                    车在路线上，记录下路线的id,跳出循环
                if (onRoad > 0) {
                    redisConfig.set(terminalIdKey, "NoOffset");
                    redisConfig.set(terminalRouteKey, oneRoadId);
                    System.out.println("存入当前所在路线上的路线id");
                    break;
                }
            };
            //onRoad > 0 说明车辆没有偏移路线
            if (onRoad > 0){
                System.out.println("车辆没有偏移路线");

            }else {
                System.out.println("车辆偏移le路线");

                String curOffsetState = "offset";
                String  lastOffsetState  = (String) redisConfig.get(terminalIdKey);

                System.out.println("车辆："+ terminalId +"上一次的状态是：" + lastOffsetState);
                System.out.println("车辆："+ terminalId +"这一次的状态是：" + curOffsetState);
                //车辆由不偏变成偏，报警，拿出存在redis里面的onRouteId

//                    System.out.println("oooooooooooooo" + offRouteId);

                if (lastOffsetState != null &&
                        "NoOffset".equals(lastOffsetState) &&
                        "offset".equals(curOffsetState)){
                    System.out.println("车辆报警!!!");
                    int lastOnRoute = (int) redisConfig.get(terminalRouteKey);

                    CarAlarm carAlarm = new CarAlarm();
                    carAlarm.setHandled(0);
                    System.out.println("idDDDDDDDDDD" + lastOnRoute);
                    carAlarm.setRouteId(lastOnRoute);
                    carAlarm.setLatitude(lat);
                    carAlarm.setLongitude(lon);
                    carAlarm.setTerminalId(terminalId);
                    carAlarm.setUpdateTime(updateTime);

                    carAlarmService.insertCarAlarmInfo(carAlarm);

                }
                redisConfig.set(terminalIdKey, curOffsetState);


            }
        }

    }


    public int pointIfOffsetRoad(String s, PointEntity pointEntity) {
        try{
            //设定’圆的半径‘，超过说明暂时偏离了路线
            double setMAx = 150;

//            s = this.s;
            if (s == null){
                throw new Exception("路段数据为空");
            }
//            System.out.println("00=="+s);
            JSONObject jo = JSONObject.parseObject(s); // 将字符串转换成json格式
            JSONArray pointSet = (JSONArray) jo.getJSONObject("geometry").getJSONArray("coordinates");

//            pointEntity.setPointLatitude(36.000076);
//            pointEntity.setPointLongitude(116.00522);


            for (int i = 0; i < pointSet.size(); i++){
                double wgs84lat = ((BigDecimal)((JSONArray)pointSet.get(i)).get(1)).doubleValue();
                double wgs84lng = ((BigDecimal)((JSONArray)pointSet.get(i)).get(0)).doubleValue();

                double res = MapUtils.GetDistance(pointEntity.getPointLatitude(), pointEntity.getPointLongitude(), wgs84lat, wgs84lng);
//                System.out.println("距离" + i + " : " + res);
                //有一个点满足在半径之内就算没有偏移
                if (res < setMAx){
                    // 1表示没有偏移，意思就是有一个的‘圆’内包含了车辆实时位置点
                    return 1;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        //表示偏移了
        return 0;
    }






}
