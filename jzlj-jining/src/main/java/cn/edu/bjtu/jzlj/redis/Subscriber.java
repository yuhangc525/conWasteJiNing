package cn.edu.bjtu.jzlj.redis;

import cn.edu.bjtu.jzlj.controller.SysUserController;
import cn.edu.bjtu.jzlj.dao.CarRoad;
import cn.edu.bjtu.jzlj.dao.PointEntity;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.mapper.CarRoadMapper;
import cn.edu.bjtu.jzlj.service.CarRoadService;
import cn.edu.bjtu.jzlj.service.RoadInfoService;
import cn.edu.bjtu.jzlj.util.GetDistance;
import cn.edu.bjtu.jzlj.util.Gps;
import cn.edu.bjtu.jzlj.util.PositionTransformationUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class Subscriber extends JedisPubSub {
    private static final Logger LOGGER = LoggerFactory.getLogger(Subscriber.class);

    private static CarRoadService carRoadService;

    @Resource
    public void setCarRoadService(CarRoadService carRoadService) {
        Subscriber.carRoadService = carRoadService;
    }

    private static RoadInfoService roadInfoService;

    @Resource
    public void setRoadInfoService(RoadInfoService roadInfoService) {
        Subscriber.roadInfoService = roadInfoService;
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
        giveAnAlarm(message);

    };



    public void giveAnAlarm (String message){

        // 将字符串转换成json格式
        JSONObject carRealTime_msg = JSONObject.parseObject(message);
        Integer acc =  carRealTime_msg.getInteger("acc");
        //只处理处于开火状态的车辆
        if (acc == 1){
            System.out.println(message);
            String terminalId = carRealTime_msg.getString("terminalId");

            String terminalIdKey = terminalId + "-key";

            //获取车辆的经纬度坐标
            double lat = carRealTime_msg.getDouble("lat");
            double lon = carRealTime_msg.getDouble("lon");

            //保存到redis的偏移状态
            JSONObject terminalIdValue = new JSONObject();
            terminalIdValue.put("terminalId", terminalId);
            terminalIdValue.put("updateTime",carRealTime_msg.getLong("uptime"));
            terminalIdValue.put("latitude", lat);
            terminalIdValue.put("longitude", lon);

            PointEntity pointEntity = new PointEntity();

            //获取车辆相应的路线列表
            List<CarRoad> roadList = carRoadService.getRoadListByTerminalId("14335712832");;

            if (roadList.size() == 0 || roadList == null){
                LOGGER.info("车辆终端id为：" + terminalId + "的车辆没有对应的路线！");
//                System.out.println("车辆终端id为：" + terminalId + "的车辆没有对应的路线！");
            } else {
                //车辆实时位置点信息
                pointEntity.setPointLongitude(lon);
                pointEntity.setPointLatitude(lat);
                //onRoad  记录车辆是否偏移了路线，0表示偏移了，大于0说明没有偏移
                int onRoad = 0;
                for (CarRoad roadId : roadList ) {
                    int oneRoadId = roadId.getRoadId();
                    //获取路线信息
                    RoadInfo roadInfo = roadInfoService.getRoadInfo(oneRoadId);
                    String roadAddress = roadInfo.getRoadAddress();
                    onRoad += pointIfOffsetRoad(roadAddress, pointEntity);
                };
                //onRoad > 0 说明车辆没有偏移路线
                if (onRoad > 0){
                    System.out.println("车辆没有偏移路线");
                    //1表示没有偏移
                    terminalIdValue.put("isOffset", 1);

                    String offsetState1 = redisConfig.get(terminalIdKey,0,300);
                    String offsetState2 = redisConfig.get(terminalIdKey,300,600);
                    String offsetState3 = redisConfig.get(terminalIdKey,600,900);

                    String terminalIdValueString = terminalIdValue.toJSONString();
                    System.out.println(terminalIdValueString);


                    if (offsetState1.equals(null) || offsetState1.equals("")){
                        System.out.println("状态放入第一个位置");
                        redisConfig.set(terminalIdKey, terminalIdValueString, 0);
                    } else if (offsetState2.equals(null) || offsetState2.equals("")) {
                        System.out.println("放入第二个");

                        redisConfig.set(terminalIdKey, terminalIdValueString, 300);
                    } else if (offsetState3.equals(null) || offsetState3.equals("")) {
                        System.out.println("放入第三个");
                        redisConfig.set(terminalIdKey, terminalIdValueString, 600);
                    } else {
                        redisConfig.remove(terminalIdKey);

                        //第二个的状态放入第一个位置
                        redisConfig.set(terminalIdKey, offsetState2, 0);
                        //第三个的状态放入第二个位置
                        redisConfig.set(terminalIdKey, offsetState3, 300);
                        //最新的状态放入第三个位置
                        redisConfig.set(terminalIdKey, terminalIdValueString, 600);
                    }

                }else {
                    System.out.println("车辆偏移le路线");
                    //0表示车辆偏移了
                    terminalIdValue.put("isOffset", 0);

                    String offsetState1 = redisConfig.get(terminalIdKey,0,300).trim();
                    String offsetState2 = redisConfig.get(terminalIdKey,300,600).trim();
                    String offsetState3 = redisConfig.get(terminalIdKey,600,900).trim();

                    //车辆的偏离状态转成字符串
                    String terminalIdValueString = terminalIdValue.toString();
                    System.out.println(terminalIdValueString);


                    if (offsetState1.equals(null) || offsetState1.equals("")){
                        System.out.println("状态放入第一个位置");
//                        redisConfig.set(terminalIdKey, terminalIdValueString, 0);
                        redisConfig.set(terminalIdKey, terminalIdValueString + "", 0);
                    } else if (offsetState2.equals(null) || offsetState2.equals("")) {
                        System.out.println("放入第二个");
//                        redisConfig.set(terminalIdKey, terminalIdValueString, 300);
                        redisConfig.set(terminalIdKey, terminalIdValueString + "", 300);
                    } else if (offsetState3.equals(null) || offsetState3.equals("")) {
                        System.out.println("放入第三个");
//                        redisConfig.set(terminalIdKey, terminalIdValueString, 600);
                        redisConfig.set(terminalIdKey, terminalIdValueString + "", 600);
                    } else {

                        redisConfig.remove(terminalIdKey);
                        //第二个的状态放入第一个位置
                        redisConfig.set(terminalIdKey, offsetState2, 0);
                        //第三个的状态放入第二个位置
                        redisConfig.set(terminalIdKey, offsetState3, 300);
                        //最新的状态放入第三个位置
//                        redisConfig.set(terminalIdKey, terminalIdValueString,600);
                        redisConfig.set(terminalIdKey, terminalIdValueString + "",600);
                    }

                    String offsetState11 =  redisConfig.get(terminalIdKey,0,300);
                    System.out.println("==========" + offsetState11);
                    String offsetState22 =  redisConfig.get(terminalIdKey,300,600);
                    String offsetState33 =  redisConfig.get(terminalIdKey,600,900);

                    if ((!offsetState11.equals(null)  && !offsetState11.equals("")) && (!offsetState22.equals(null) && !offsetState22.equals("")) && (!offsetState33.equals(null)  && !offsetState33.equals(""))){

                        StringEscapeUtils.unescapeJavaScript(offsetState11);

                        JSONObject offsetState11json = JSONObject.parseObject(offsetState11);
                        JSONObject offsetState22json = JSONObject.parseObject(offsetState22);
                        JSONObject offsetState33json = JSONObject.parseObject(offsetState33);

                        //连续三个点都偏移
                        if (offsetState11json.getInteger("isOffset") == 0 && offsetState22json.getInteger("isOffset") == 0
                                && offsetState33json.getInteger("isOffset") == 0) {
                            System.out.println("车辆连续偏移三次，需要报警！！");
                        }
                    }

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
     * @param s
     * @param pointEntity
     * @return
     */
    public int pointIfOffsetRoad(String s, PointEntity pointEntity) {
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

//            pointEntity.setPointLatitude(36.000076);
//            pointEntity.setPointLongitude(116.00522);


            for (int i = 0; i < pointSet.size(); i++){
                double wgs84lat = ((BigDecimal)((JSONArray)pointSet.get(i)).get(1)).doubleValue();
                double wgs84lng = ((BigDecimal)((JSONArray)pointSet.get(i)).get(0)).doubleValue();

                //路径点的百度坐标转化为gcj02坐标（车辆位置时gcj02坐标）
//                Gps roadPoint = PositionTransformationUtil.bd09_To_Gcj02(BDlat,BDlng);
//                roadPoint.getWgLat(), roadPoint.getWgLon()
//                System.out.println(i + "点经度：" + roadPoint.getWgLon());
//                System.out.println(i + "点纬度：" + roadPoint.getWgLat());
                //计算车辆位置点和路径上点的距离
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




