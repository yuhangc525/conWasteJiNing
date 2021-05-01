package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;

import cn.edu.bjtu.jzlj.dao.TRealtimePosition;
import cn.edu.bjtu.jzlj.service.TRealtimePositionService;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.results.Resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.bjtu.jzlj.service.CarInfoService;

import java.util.List;

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
            return Resp.getInstantiationSuccess("车辆实时状态列表查询成功", Resp.LIST,tRealtimePosition);
        }catch (Exception e){
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据车牌号查询车辆实时状态查询失败，原因：" + e.getMessage() + "，用时：" + (endTime - startTime) +"ms");
            return Resp.getInstantiationError("根据车牌号查询车辆实时状态查询失败，原因：" + e.getMessage(),null,null);

        }


    }
}
