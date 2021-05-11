package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.CarBelong;
import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.dao.RouteInfo;
import cn.edu.bjtu.jzlj.service.RoadInfoService;
import cn.edu.bjtu.jzlj.service.RouteInfoService;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.results.Resp;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
    public Resp updateOrInsertRoad(@RequestBody RouteInfo routeInfo) {
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
//
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

    /*
     * @Author zjwang
     * @Description // 路线信息查询分页Or不分页
     * @Date 16:19 2021/5/2
     * @ParamTypes [cn.edu.bjtu.jzlj.util.QueryRequest, cn.edu.bjtu.jzlj.dao.RouteInfo]
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     **/
    @ApiOperation(value = "路线信息查询", httpMethod = "GET")
    @GetMapping("/listRoute")
    @ControllerEndpoint(operation = "路线信息查询", exceptionMessage = "路线信息查询失败")
    public Resp getListByPage(QueryRequest queryRequest, RouteInfo routeInfo){
        //查询列表数据
        long startTime = System.currentTimeMillis();
        if (queryRequest.getPageNo() < 1 || queryRequest.getPageNo() < 1) {
            return Resp.getInstantiationError("分页查看失败，分页页数或分页大小不合法", null, null);
        }
        try {

            /*分页查询*/
            if(queryRequest.isPageFlag())
            {
                if (queryRequest.getPageNo() < 1 || queryRequest.getPageNo() < 1)
                {
                    return Resp.getInstantiationError("分页查看失败，分页页数或分页大小不合法", null, null);
                }
                IPage<RouteInfo> routeInfoList = routeInfoService.getListByPage(queryRequest, routeInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, routeInfoList);
            }
            else
            {
                List<RouteInfo> sysUserList = routeInfoService.getAllList(queryRequest, routeInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("路线信息查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("路线信息查询", Resp.LIST, sysUserList);
            }
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("分页列举失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }
    }

    @ApiOperation(value = "转换的路线信息查询, 会根据routeDesign查询roadName并返回", httpMethod = "GET")
    @GetMapping("/listConvertedRoute")
    @ControllerEndpoint(operation = "转换的路线信息查询", exceptionMessage = "转换的路线信息查询失败")
    public Resp getConvertedListByPage(QueryRequest queryRequest, RouteInfo routeInfo){
        //查询列表数据
        long startTime = System.currentTimeMillis();
        if (queryRequest.getPageNo() < 1 || queryRequest.getPageNo() < 1) {
            return Resp.getInstantiationError("转换的分页查看失败，分页页数或分页大小不合法", null, null);
        }
        try {

            /*分页查询*/
            if(queryRequest.isPageFlag())
            {
                if (queryRequest.getPageNo() < 1 || queryRequest.getPageNo() < 1)
                {
                    return Resp.getInstantiationError("转换的分页查看失败，分页页数或分页大小不合法", null, null);
                }
                List<RouteInfo> routeInfoList = routeInfoService.getConvertedListByPage(queryRequest, routeInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("转换的分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("转换的分页查看", Resp.LIST, routeInfoList);
            }
            else
            {
                List<RouteInfo> sysUserList = routeInfoService.getConvertedAllList(queryRequest, routeInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("转换的路线信息查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("转换的路线信息查询", Resp.LIST, sysUserList);
            }
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("转换的分页列举失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }
    }

    @ApiOperation(value = "删除路线数据",httpMethod = "DELETE")
    @DeleteMapping("/{delete}")
    @ControllerEndpoint(operation = "删除路线数据", exceptionMessage = "删除路线数据失败")
    public Resp deleteByRouteId(@RequestParam("routeId") String routeId){
        long startTime = System.currentTimeMillis();
        try {
            routeInfoService.deleteByRouteId(routeId);
            long endTime = System.currentTimeMillis();
            LOGGER.info("批量删除成功,用时:" + (endTime-startTime) + "ms");
            return Resp.getInstantiationSuccess("批量删除成功", Resp.LIST, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("批量删除失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("批量删除失败:"+e.getMessage(), Resp.LIST, null);
        }
    }

    @ApiOperation(value = "更新路线数据", httpMethod = "PUT")
    @PutMapping("/updateData")
    @ControllerEndpoint(operation = "更新路线数据", exceptionMessage = "更新失败")
    public Resp updateinfo(@RequestBody RouteInfo routeInfo) {
        long startTime = System.currentTimeMillis();
        try {
            if(routeInfo.getRouteId() == null){
                long endTime = System.currentTimeMillis();
                LOGGER.error("修改失败，原因：修改不存在，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationError("修改失败，原因：修改的id不存在，", null, null);
            }
            routeInfoService.updateinfo(routeInfo);
            long endTime =  System.currentTimeMillis();
            LOGGER.info("修改成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("修改成功", null, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("普通用户更新数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("普通用户更新数据失败："+ e.getMessage(),Resp.LIST,null );
        }
    }

}
