package cn.edu.bjtu.jzlj.redis;

import cn.edu.bjtu.jzlj.dao.*;
import cn.edu.bjtu.jzlj.service.*;
import cn.edu.bjtu.jzlj.util.GetDistance;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class Subscriber extends JedisPubSub {
    private static final Logger LOGGER = LoggerFactory.getLogger(Subscriber.class);

    private static CarRouteService carRouteService;

    @Resource
    public void setCarRoadService(CarRouteService carRouteService) {
        Subscriber.carRouteService = carRouteService;
    }

    private static CarInfoService carInfoService;
    @Resource
    public void serCarInfoService(CarInfoService carInfoService) {Subscriber.carInfoService = carInfoService;}


    private static CarAlarmService carAlarmService;

    @Resource
    public void setCarAlarmService(CarAlarmService carAlarmService) {
        Subscriber.carAlarmService = carAlarmService;
    }

    private static RoadInfoService roadInfoService;

    @Resource
    public void setRoadInfoService(RoadInfoService roadInfoService) {
        Subscriber.roadInfoService = roadInfoService;
    }

    private static RouteInfoService routeInfoService;

    @Resource
    public void setRouteInfoService(RouteInfoService routeInfoService) {
        Subscriber.routeInfoService = routeInfoService;
    }

    private static RedisConfig redisConfig;

    @Resource
    public void setRedisConfig(RedisConfig redisConfig) {
        Subscriber.redisConfig = redisConfig;
    }

    public Subscriber(){}




    @Override
    //收到消息会调用
    public void onMessage(String channel, String message) {
//        System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
        try {
            giveAnAlarm(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    };



    public void giveAnAlarm (String message) throws Exception {

        // 将字符串转换成json格式
        JSONObject carRealTime_msg = JSONObject.parseObject(message);
        Integer acc =  carRealTime_msg.getInteger("acc");
        Integer position = carRealTime_msg.getInteger("position");
        //只处理处于开火状态的车辆
        if (acc == 1 && position == 1){
            System.out.println(message);
            String terminalId = carRealTime_msg.getString("terminalId");
            String carNo = carInfoService.getCarNoByTerminalId(terminalId);
            Long updateTime = carRealTime_msg.getLong("uptime");
            Date date = new Date();
            date.setTime(updateTime);

//            String terminalIdKey = "14268527765" + "-key";
            String terminalIdKey = terminalId + "-key";
            String terminalRouteKey = terminalId + "-route";

            //获取车辆的经纬度坐标
            double lat = carRealTime_msg.getDouble("lat");
            double lon = carRealTime_msg.getDouble("lon");
//            long updateTime = carRealTime_msg.getLong("uptime");


            PointEntity pointEntity = new PointEntity();

            //获取车辆相应的路线列表
//            14268527702 这车对应‘路线B’
            List<CarRoute> routeList = carRouteService.getRouteListByTerminalId(terminalId);

            if (routeList.size() == 0 || routeList == null){
                LOGGER.info("车辆终端id为: " + terminalId + "的车辆没有对应的路线！");
//                System.out.println("车辆终端id为：" + terminalId + "的车辆没有对应的路线！");
            } else {
                //车辆实时位置点信息
                pointEntity.setPointLongitude(lon);
                pointEntity.setPointLatitude(lat);
                //onRoad  记录车辆是否偏移了路线，0表示偏移了，大于0说明没有偏移
                int onRoad = 0;
                int onRouteId = -1;
                int offRouteId = -1;
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
//                    if (onRoad > 0) {
//                        onRouteId = oneRoadId;
//                        redisConfig.set(terminalIdKey, "NoOffset" + "-" + onRouteId);
//                        System.out.println("存入当前所在路线上的路线id");
//                        break;
//                    }
                    //偏移了路线，记下路线
                    if (onRoad == 0) {
                       offRouteId = oneRoadId;
                    }
                };
                //onRoad > 0 说明车辆没有偏移路线
                if (onRoad > 0){
                    System.out.println("车辆没有偏移路线");

                    String lastOffsetState = (String) redisConfig.get(terminalIdKey);
                    if (lastOffsetState != null && (!"NoOffset".equals(lastOffsetState) || !"offset".equals(lastOffsetState))){
                        redisConfig.remove(terminalIdKey);
                    }
                    String curOffsetState = "NoOffset";
                    redisConfig.set(terminalIdKey, curOffsetState);


                }else {
                    System.out.println("车辆偏移le路线");

                    String curOffsetState = "offset";
                    String  lastOffsetState  = (String) redisConfig.get(terminalIdKey);
                    if (lastOffsetState != null && !"NoOffset".equals(lastOffsetState) && !"offset".equals(lastOffsetState)){
                        redisConfig.remove(terminalIdKey);
                    }
                    System.out.println("车辆："+ terminalId +"上一次的状态是：" + lastOffsetState);
                    System.out.println("车辆："+ terminalId +"这一次的状态是：" + curOffsetState);
                    //车辆由不偏变成偏，报警，拿出存在redis里面的onRouteId

                    System.out.println("oooooooooooooo" + offRouteId);

                    if (lastOffsetState != null &&
                            "NoOffset".equals(lastOffsetState) &&
                            "offset".equals(curOffsetState)){
                        System.out.println("车辆报警!!!");

                        CarAlarm carAlarm = new CarAlarm();
                        carAlarm.setHandled(0);
//                        System.out.println("carNNNNNNNNN" + carNo);
                        carAlarm.setCarNo(carNo);
                        System.out.println("idDDDDDDDDDD" + offRouteId);
                        carAlarm.setRouteId(offRouteId);
                        carAlarm.setLatitude(lat);
                        carAlarm.setLongitude(lon);
                        carAlarm.setTerminalId(terminalId);
                        carAlarm.setUpdateTime(date);

                        carAlarmService.insertCarAlarmInfo(carAlarm);

                    }
                    redisConfig.set(terminalIdKey, curOffsetState);


                }
            }


        }

    }





    @Override
    //订阅了频道会调用
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
                channel, subscribedChannels));
    }
    @Override
    //取消订阅 会调用
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
                channel, subscribedChannels));
    }

    /**
     * 判断车辆是否偏移
     * @param s 路线的gps信息
     * @param pointEntity
     * @return
     */
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

                double res = GetDistance.getDistance(pointEntity.getPointLatitude(), pointEntity.getPointLongitude(), wgs84lat, wgs84lng);
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




