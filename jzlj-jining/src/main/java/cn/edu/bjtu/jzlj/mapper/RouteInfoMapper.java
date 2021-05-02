package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;;
import cn.edu.bjtu.jzlj.dao.RouteInfo;

import cn.edu.bjtu.jzlj.dao.RouteInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zjwang
 * @date: 2021/4/22 23:55
 * @description:
 */
@Repository
public interface RouteInfoMapper extends BaseMapper<RouteInfo> {

    List<RouteInfo> getRouteInfoByRouteId(@Param("routeId") String routeId);


    RouteInfo getOneRouteInfoByRouteId(Integer routeId);

    // 分页查询
    IPage<RouteInfo> getListByPage(Page<RouteInfo> page, RouteInfo routeInfo);

    // 不分页查询
    List<RouteInfo> getAllList(QueryWrapper<RouteInfo> queryWrapper, RouteInfo routeInfo);
}
