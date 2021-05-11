package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.dao.RouteInfo;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author: zjwang
 * @date: 2021/4/23 0:01
 * @description:
 */
public interface RouteInfoService extends IService<RouteInfo> {
    // 根据IntakePlantName查询数据

    List<RouteInfo> getInfoByRouteDesign(String routeDesign);

    // 根据IntakePlantName插入或者更新数据，并返回id
    Integer updateOrInsertRoute(RouteInfo routeInfo);

    List<RouteInfo> getRouteInfoByRouteId(String routeId);

    RouteInfo getOneRouteInfoByRouteId(Integer routeId);

    int updateData(RouteInfo routeInfo);

    // 分页查询
    IPage<RouteInfo> getListByPage(QueryRequest queryRequest, RouteInfo routeInfo);

    // 部分也拆寻
    List<RouteInfo> getAllList(QueryRequest queryRequest, RouteInfo routeInfo);

    // 转换的routeInfo分页查询
    List<RouteInfo> getConvertedListByPage(QueryRequest queryRequest, RouteInfo routeInfo);

    // 转换的routeInfo不分页拆寻
    List<RouteInfo> getConvertedAllList(QueryRequest queryRequest, RouteInfo routeInfo);

    int deleteByRouteId(String routeId);

    int updateinfo(RouteInfo routeInfo);
}
