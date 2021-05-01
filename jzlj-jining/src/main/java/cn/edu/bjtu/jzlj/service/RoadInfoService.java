package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.dao.RouteInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface RoadInfoService extends IService<RoadInfo> {

    // 根据IntakePlantName查询数据
    List<RoadInfo> getInfoByRoadName(String roadName);

    // 根据IntakePlantName插入或者更新数据，并返回id
    Integer updateOrInsertRoad(RoadInfo roadInfo);

    List<RoadInfo> getRoadInfoByRoadId(String roadId);
}
