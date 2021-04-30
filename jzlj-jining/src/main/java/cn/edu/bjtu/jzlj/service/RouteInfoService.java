package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.dao.RouteInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author: zjwang
 * @date: 2021/4/23 0:01
 * @description:
 */
public interface RouteInfoService extends IService<RouteInfo> {
    // 根据IntakePlantName查询数据
    List<RouteInfo> getInfoByRouteName(String routeName);

    // 根据IntakePlantName插入或者更新数据，并返回id
    Integer updateOrInsertRoute(RouteInfo routeInfo);

    List<RouteInfo> getRouteInfoByRouteId(String routeId);
    RouteInfo getRoadInfo(Integer roadId);
    int updateData(RouteInfo routeInfo);
}
