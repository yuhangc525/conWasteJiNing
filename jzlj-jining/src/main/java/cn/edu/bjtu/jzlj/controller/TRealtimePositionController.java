package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;

import cn.edu.bjtu.jzlj.dao.TRealtimePosition;
import cn.edu.bjtu.jzlj.service.TRealtimePositionService;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.results.Resp;

import cn.edu.bjtu.jzlj.vo.Point;
import cn.edu.bjtu.jzlj.vo.RegionalVehicleSelectionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.edu.bjtu.jzlj.service.CarInfoService;

import java.util.List;

import javax.swing.plaf.synth.Region;

@Api(description = "车辆实时位置接口接口")
@RestController
@RequestMapping(value = "/tRealTimePostionController")
public class TRealtimePositionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysOrganizationController.class);
    @Autowired
    TRealtimePositionService tRealTimePositionService;
    @Autowired
    CarInfoService carInfoService;
    //    @ControllerEndpoint(operation = "查询实时位置列表", exceptionMessage = "实时位置列表查询失败")
    @ApiOperation(value = "查询实时位置表", httpMethod = "GET")
    @GetMapping("/selectAll")
    public Resp getAllList(){
//        List<TRealtimePosition> tRealtimePositionList=tRealTimePositionService.getAllList();


        long startTime = System.currentTimeMillis();
        try {

            long endTime = System.currentTimeMillis();
            List<TRealtimePosition> tRealtimePositionList=tRealTimePositionService.getAllList();
            // LOGGER.info("车辆实时状态列表查询成功，用时："+ (endTime - startTime) +"ms");
            return Resp.getInstantiationSuccess("车辆实时状态列表查询成功", Resp.LIST,tRealtimePositionList);
//            return Resp.getInstantiationSuccess("车辆实时状态列表查询成功",null,dirverIPage);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("车辆实时状态列表查询失败，原因：" + e.getMessage() + "，用时：" + (endTime - startTime) +"ms");
            return Resp.getInstantiationError("车辆实时状态列表查询失败，原因：" + e.getMessage(),null,null);
        }
    }
    @ApiOperation(value = "根据车牌查历史轨迹", httpMethod = "POST")
    @RequestMapping(value = "getPositionByCarNo")
    @ControllerEndpoint(operation = "根据车牌查历史轨迹", exceptionMessage = "根据车牌查历史轨迹失败")
    public Resp getRealtimePostionByCarNo(String carNo){
        long startTime = System.currentTimeMillis();

        try {
            String terninalId=carInfoService.getTerminalIdByCarNo(carNo);
            List<TRealtimePosition> tRealtimePosition=tRealTimePositionService.getPositionByTerminalId(terninalId);
            return Resp.getInstantiationSuccess("车辆实时状态列表查询成功", Resp.LIST, tRealtimePosition);
        }catch (Exception e){
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据车牌号查询车辆实时状态查询失败，原因：" + e.getMessage() + "，用时：" + (endTime - startTime) +"ms");
            return Resp.getInstantiationError("根据车牌号查询车辆实时状态查询失败，原因：" + e.getMessage(),null,null);

        }


    }

    @ApiOperation(value = "矩形区域 - 实时查询x小时（默认1）内出现的车辆，interval单位为小时", httpMethod = "POST")
    @RequestMapping(value = "getRectangleRegionalVehicles")
    @ControllerEndpoint(operation = "实时查询矩形区域的车辆", exceptionMessage = "实时查询矩形区域的车辆失败")
    public Resp getRectangleRegionalVehicles(double startLat, double startLong, double endLat, double endLong, @RequestParam(defaultValue = "1.0", required = false) double interval){
        long startTime = System.currentTimeMillis();
        try {
            List<RegionalVehicleSelectionVo> res = tRealTimePositionService.getRectangleRegionalVehicles(startLat, startLong, endLat, endLong, interval);
            return Resp.getInstantiationSuccess("实时查询矩形区域的车辆", Resp.LIST, res);
        }catch (Exception e){
            long endTime = System.currentTimeMillis();
            LOGGER.error("实时查询矩形区域的车辆失败，原因：" + e.getMessage() + "，用时：" + (endTime - startTime) +"ms");
            return Resp.getInstantiationError("实时查询矩形区域的车辆失败，原因：" + e.getMessage(),null,null);
        }
    }

    @ApiOperation(value = "圆形区域 - 实时查询x小时（默认1）内出现的车辆，interval单位为小时", httpMethod = "POST")
    @RequestMapping(value = "getCircleRegionalVehicles")
    @ControllerEndpoint(operation = "实时查询圆形区域的车辆", exceptionMessage = "实时查询圆形区域的车辆失败")
    public Resp getCircleRegionalVehicles (double centerLat, double centerLong, double semidiameter, @RequestParam(defaultValue = "1.0", required = false) double interval) {
        long startTime = System.currentTimeMillis();
        try {
            List<RegionalVehicleSelectionVo> res = tRealTimePositionService.getCircleRegionalVehicles(centerLat, centerLong, semidiameter, interval);
            return Resp.getInstantiationSuccess("实时查询圆形区域的车辆", Resp.LIST, res);
        }catch (Exception e){
            long endTime = System.currentTimeMillis();
            LOGGER.error("实时查询圆形区域的车辆失败，原因：" + e.getMessage() + "，用时：" + (endTime - startTime) +"ms");
            return Resp.getInstantiationError("实时查询圆形区域的车辆失败，原因：" + e.getMessage(),null,null);
        }
    }

    @ApiOperation(value = "多边形区域，- 实时查询x小时（默认1）内出现的车辆，interval单位为小时", httpMethod = "POST")
    @RequestMapping(value = "getPolygonRegionalVehicles")
    @ControllerEndpoint(operation = "实时查询多边形区域的车辆", exceptionMessage = "实时查询多边形区域的车辆失败")
    public Resp getPolygonRegionalVehicles (@RequestBody List<Point> points, @RequestParam(defaultValue = "1.0", required = false) double interval) {
        long startTime = System.currentTimeMillis();
        try {
            List<RegionalVehicleSelectionVo> res = tRealTimePositionService.getPolygonRegionalVehicles(points, interval);
            return Resp.getInstantiationSuccess("实时查询多边型区域的车辆", Resp.LIST, res);
        }catch (Exception e){
            long endTime = System.currentTimeMillis();
            LOGGER.error("实时查询多边型区域的车辆失败，原因：" + e.getMessage() + "，用时：" + (endTime - startTime) +"ms");
            return Resp.getInstantiationError("实时查询多边型区域的车辆失败，原因：" + e.getMessage(),null,null);
        }
    }
}
