package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.dao.RouteInfo;
import cn.edu.bjtu.jzlj.dao.RouteInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    RouteInfo getRoadInfo(Integer roadId);

    List<RouteInfo> getRouteInfoByRouteId(@Param("routeId") String routeId);

}
