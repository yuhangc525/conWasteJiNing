package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.controller.IntakePlantInfoController;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.dao.RouteInfo;
import cn.edu.bjtu.jzlj.mapper.RouteInfoMapper;

import cn.edu.bjtu.jzlj.service.RoadInfoService;
import cn.edu.bjtu.jzlj.service.RouteInfoService;
import cn.edu.bjtu.jzlj.util.CommonUtil;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.SortUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: zjwang
 * @date: 2021/4/23 0:02
 * @description:
 */
@Service
public class RouteInfoServiceImpl extends ServiceImpl<RouteInfoMapper, RouteInfo> implements RouteInfoService {

    @Autowired
    private RouteInfoMapper routeInfoMapper;
    @Autowired
    private RoadInfoService roadInfoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(IntakePlantInfoController.class);



    @Override
    public List<RouteInfo> getInfoByRouteDesign(String routeDesign) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("route_design", routeDesign);
        return routeInfoMapper.selectByMap(columnMap);
    }

    @Override
    public Integer updateOrInsertRoute(RouteInfo routeInfo) {
        List<RouteInfo> list;

        if(!"".equals(routeInfo.getRouteDesign())){
            list = getInfoByRouteDesign(routeInfo.getRouteDesign());
        }else{
            LOGGER.error("routeDesign为空！Error！");
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

            id = getInfoByRouteDesign(routeInfo.getRouteDesign()).get(0).getRouteId();
        }
        return id;
    }

    @Override
    public List<RouteInfo> getRouteInfoByRouteId(String routeId){
        return routeInfoMapper.getRouteInfoByRouteId(routeId);
    }

    @Override
    public RouteInfo getOneRouteInfoByRouteId(Integer routeId) {
        RouteInfo routeInfo = routeInfoMapper.getOneRouteInfoByRouteId(routeId);
        return  routeInfo;
    }

    @Override
    public int updateData(RouteInfo routeInfo) {
        return routeInfoMapper.updateById(routeInfo);
    }

    @Override
    public IPage<RouteInfo> getListByPage(QueryRequest queryRequest, RouteInfo routeInfo) {
        Page<RouteInfo> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "routeId", CommonUtil.ORDER_ASC, true);
        IPage<RouteInfo> list = routeInfoMapper.getListByPage(page, routeInfo);
        return list;
    }

    @Override
    public List<RouteInfo> getAllList(QueryRequest queryRequest, RouteInfo routeInfo) {
        QueryWrapper<RouteInfo> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "routeId", CommonUtil.ORDER_ASC, true);
        return routeInfoMapper.getAllList(queryWrapper, routeInfo);
    }

    @Override
    public List<RouteInfo> getConvertedListByPage(QueryRequest queryRequest, RouteInfo routeInfo) {
        Page<RouteInfo> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "routeId", CommonUtil.ORDER_ASC, true);
        IPage<RouteInfo> list = routeInfoMapper.getListByPage(page, routeInfo);
        List<RouteInfo> resultData = list.getRecords();
        // 根据 roadId 获取 roadName 并赋值 routeConvertedDesign
        for(int i = 0; i < resultData.size(); i++){
            String[] ids = resultData.get(i).getRouteDesign().split("\\|");
            String[] design = new String[ids.length];
            int j = 0;
            for(String id : ids){
                RoadInfo roadInfo = roadInfoService.getRoadInfoByRoadId(id).get(0);
                design[j++] = roadInfo.getRoadName();
            }
            resultData.get(i).setRouteConvertedDesign(StringUtils.join(design, '-'));
        }
        return resultData;
    }

    @Override
    public List<RouteInfo> getConvertedAllList(QueryRequest queryRequest, RouteInfo routeInfo) {
        QueryWrapper<RouteInfo> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "routeId", CommonUtil.ORDER_ASC, true);
        List<RouteInfo> resultData = routeInfoMapper.getAllList(queryWrapper, routeInfo);
        // 根据 roadId 获取 roadName 并赋值 routeConvertedDesign
        for(int i = 0; i < resultData.size(); i++){
            String[] ids = resultData.get(i).getRouteDesign().split("\\|");
            String[] design = new String[ids.length];
            int j = 0;
            for(String id : ids){
                RoadInfo roadInfo = roadInfoService.getRoadInfoByRoadId(id).get(0);
                design[j++] = roadInfo.getRoadName();
            }
            resultData.get(i).setRouteConvertedDesign(StringUtils.join(design, '-'));
        }
        return resultData;
    }
}
