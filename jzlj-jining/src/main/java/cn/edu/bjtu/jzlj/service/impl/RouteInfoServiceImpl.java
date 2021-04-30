package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.controller.IntakePlantInfoController;
import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.dao.RouteInfo;
import cn.edu.bjtu.jzlj.mapper.RouteInfoMapper;

import cn.edu.bjtu.jzlj.service.RouteInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zjwang
 * @date: 2021/4/23 0:02
 * @description:
 */
@Service
public class RouteInfoServiceImpl extends ServiceImpl<RouteInfoMapper, RouteInfo> implements RouteInfoService {

    @Autowired
    private RouteInfoMapper routeInfoMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(IntakePlantInfoController.class);

    @Override
    public RouteInfo getRoadInfo(Integer roadId) {
        return routeInfoMapper.getRoadInfo(roadId);
    }

    @Override
    public List<RouteInfo> getInfoByRouteName(String routeName) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("route_name", routeName);
        return routeInfoMapper.selectByMap(columnMap);
    }

    @Override
    public Integer updateOrInsertRoute(RouteInfo routeInfo) {
        List<RouteInfo> list;
        if(!"".equals(routeInfo.getRouteName())){
            list = getInfoByRouteName(routeInfo.getRouteName());
        }else{
            LOGGER.error("routeName为空！Error！");
            return null;
        }
        int id; // 记录的id
        if(!list.isEmpty()){
            // 若数据库中存在记录
            id = list.get(0).getRouteId();
            routeInfo.setRouteId(id);
            int row = routeInfoMapper.updateById(routeInfo);
        }else{
            // 若数据库中不存在记录
            int row = routeInfoMapper.insert(routeInfo);
            id = getInfoByRouteName(routeInfo.getRouteName()).get(0).getRouteId();
        }
        return id;
    }

    @Override
    public List<RouteInfo> getRouteInfoByRouteId(String routeId){
        return routeInfoMapper.getRouteInfoByRouteId(routeId);
    }

    @Override
    public int updateData(RouteInfo routeInfo) {
        return routeInfoMapper.updateById(routeInfo);
    }
}
