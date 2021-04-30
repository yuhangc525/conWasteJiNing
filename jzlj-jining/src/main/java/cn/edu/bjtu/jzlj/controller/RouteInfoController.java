package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.CarBelong;
import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.dao.RouteInfo;
import cn.edu.bjtu.jzlj.service.RoadInfoService;
import cn.edu.bjtu.jzlj.service.RouteInfoService;
import cn.edu.bjtu.jzlj.util.results.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: zjwang
 * @date: 2021/4/23 0:09
 * @description:
 */

@Api(description = "路线信息接口")
@RestController
@RequestMapping("/routeInfo")
public class RouteInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcePlantInfoController.class);

    @Autowired
    private RouteInfoService routeInfoService;

    /**
     * @Author zjwang
     * @Description //TODO
     * @Date 19:34 2021/4/27
     * @ParamTypes [cn.edu.bjtu.jzlj.dao.RouteInfo]
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     **/
    @ApiOperation(value = "根据route_name判断插入或者更新数据，并返回route_id", httpMethod = "PUT")
    @PutMapping("/updateOrInsertRoute")
    @ControllerEndpoint(operation = "更新或插入成功！", exceptionMessage = "操作失败")
    public Resp updateOrInsertRoad(RouteInfo routeInfo) {
        long startTime = System.currentTimeMillis();
        try {
            // 业务逻辑
            Integer id = routeInfoService.updateOrInsertRoute(routeInfo);
            if(id == null){
                throw new Exception("routeName为空！Error！");
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据route_name判断插入或者更新数据，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("updateOrInsertRoad成功", Resp.STRING, id);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据route_name判断插入或者更新数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("updateOrInsertRoute失败："+ e.getMessage(),Resp.LIST,null );
        }
    }

    /*
     * @Author zjwang
     * @Description //TODO
     * @Date 19:34 2021/4/27
     * @ParamTypes [cn.edu.bjtu.jzlj.dao.RouteInfo]
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     **/
    @ApiOperation(value = "根据routeId查询数据", httpMethod = "GET")
    @GetMapping("/selectByRouteId")
    @ControllerEndpoint(operation = "根据routeId查询数据", exceptionMessage = "查询失败")
    public Resp getRouteInfoByRouteId(RouteInfo routeInfo) {
        long startTime = System.currentTimeMillis();
        try {
            JSONObject jsonObject = JSONObject.fromObject(routeInfo);
            List<RouteInfo> routeInfoList = routeInfoService.getRouteInfoByRouteId(jsonObject.getString("routeId"));
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据routeId查询数据，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("查询成功", Resp.STRING, routeInfoList);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据routeId查询数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("根据routeId查询数据失败："+ e.getMessage(),Resp.LIST,null );
        }
    }

    @ApiOperation(value = "查询路段信息", httpMethod = "GET")
    @GetMapping("/getRoadInfo")
    public Resp getRoadInfo(RoadInfo roadInfo){
        long time = System.currentTimeMillis();
        try{

            RouteInfo roadInfo1 = routeInfoService.getRoadInfo(roadInfo.getRoadId());
            LOGGER.info("查询路段信息成功，用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationSuccess("查询路段信息成功", Resp.SINGLE, roadInfo1.getLngLat());
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error("查询路段信息异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("查询路段信息异常", Resp.STRING, null);
        }
    }


    @ApiOperation(value = "路线信息更改", httpMethod = "PUT")
    @PutMapping("/putRouteInfo")
    @ControllerEndpoint(operation = "编辑", exceptionMessage = "编辑失败")
    public Resp update(@RequestBody RouteInfo routeInfo){
        long startTime =  System.currentTimeMillis();
        try {
            //更新
            if(routeInfoService.getById(routeInfo.getRouteId()) == null){
                long endTime = System.currentTimeMillis();
                LOGGER.error("修改失败，原因：修改不存在，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationError("修改失败，原因：修改的id不存在，", null, null);
            }
            routeInfoService.updateData(routeInfo);
            long endTime =  System.currentTimeMillis();
            LOGGER.info("修改成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("修改成功", null, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("修改失败，原因："+ e.getMessage() +"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("修改失败：" + e.getMessage(), null, null);
        }
    }

}