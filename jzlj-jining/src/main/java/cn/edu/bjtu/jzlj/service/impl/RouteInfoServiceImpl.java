package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.controller.IntakePlantInfoController;
import cn.edu.bjtu.jzlj.dao.RouteInfo;
import cn.edu.bjtu.jzlj.mapper.RouteInfoMapper;

import cn.edu.bjtu.jzlj.service.RouteInfoService;
import cn.edu.bjtu.jzlj.util.CommonUtil;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.SortUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
}
