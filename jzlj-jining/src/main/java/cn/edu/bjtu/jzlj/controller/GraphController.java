package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.mapper.CarAlarmMapper;
import cn.edu.bjtu.jzlj.mapper.CarInfoMapper;
import cn.edu.bjtu.jzlj.mapper.THistoryPositionMapper;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.results.Resp;
import cn.edu.bjtu.jzlj.vo.graph.*;
import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.dao.SourceInfo;
import cn.edu.bjtu.jzlj.mapper.IntakePlantInfoMapper;
import cn.edu.bjtu.jzlj.mapper.SourceInfoMapper;
import cn.edu.bjtu.jzlj.service.IntakePlantInfoService;
import cn.edu.bjtu.jzlj.service.SourceInfoService;
import cn.edu.bjtu.jzlj.vo.graph.DateAndTotal;
import cn.edu.bjtu.jzlj.vo.graph.ReviewAndNumber;
import cn.edu.bjtu.jzlj.vo.graph.SourceNameAndWasteTotal;
import cn.edu.bjtu.jzlj.vo.graph.SourceTypeAndNumber;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: zjwang
 * @date: 2021/5/24 15:23
 * @description:
 */

@Api(description = "图表接口")
@RestController
@CrossOrigin
@RequestMapping("/graph")
public class GraphController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EfInfoController.class);

    @Autowired
    CarInfoMapper carInfoMapper;

    @Autowired
    CarAlarmMapper carAlarmMapper;

    @Autowired
    THistoryPositionMapper tHistoryPositionMapper;
    IntakePlantInfoMapper intakePlantInfoMapper;
    @Autowired
    IntakePlantInfoService intakePlantInfoService;

    @Autowired
    SourceInfoService sourceInfoService;
    @Autowired
    SourceInfoMapper sourceInfoMapper;

    /* 车辆模块 * 5 */

    @ApiOperation(value = "7天报警车辆数量折线变化图", httpMethod = "GET")
    @GetMapping("/alarmCarNumberLineChart")
    @ControllerEndpoint(operation = "7天报警车辆数量折线变化图", exceptionMessage = "7天报警车辆数量折线变化图获取异常")
    public Resp getAlarmCarNumber(){
        List<getAlarmCarNumberVo> data = carAlarmMapper.getAlarmCarNumber();
        return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, data);
    }

    @ApiOperation(value = "车辆审核状态占比扇形图", httpMethod = "GET")
    @GetMapping("/proportionOfVehicleReview")
    @ControllerEndpoint(operation = "车辆审核状态占比扇形图", exceptionMessage = "车辆审核状态占比扇形图获取异常")
    public Resp proportionOfVehicleReview(){
        List<proportionOfVehicleReviewVo> data = carInfoMapper.proportionOfVehicleReview();
        return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, data);
    }

    @ApiOperation(value = "车辆使用状态占比扇形图", httpMethod = "GET")
    @GetMapping("/proportionOfVehicleStatus")
    @ControllerEndpoint(operation = "车辆使用状态占比扇形图", exceptionMessage = "车辆使用状态占比扇形图获取异常")
    public Resp proportionOfVehicleStatus(){

        List<proportionOfVehicleStatusVo> data = carInfoMapper.proportionOfVehicleStatus();
        return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, data);
    }

    @ApiOperation(value = "7天车辆使用数量的周变化折线图", httpMethod = "GET")
    @GetMapping("/numberOfVehicleUsed")
    @ControllerEndpoint(operation = "7天车辆使用数量的周变化折线图", exceptionMessage = "7天车辆使用数量的周变化折线图获取异常")
    public Resp numberOfVehicleUsed(String tableName){
        Integer data = tHistoryPositionMapper.numberOfVehicleUsed(tableName);
        return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, data);
    }

    @ApiOperation(value = "车辆报警状态的误报比例扇形图", httpMethod = "GET")
    @GetMapping("/proportionOfVehicleCorrectAlarm")
    @ControllerEndpoint(operation = "车辆报警状态的误报比例扇形图", exceptionMessage = "车辆报警状态的误报比例扇形图获取异常")
    public Resp proportionOfVehicleCorrectAlarm(){
        List<proportionOfVehicleCorrectAlarmVo> data = carAlarmMapper.proportionOfVehicleCorrectAlarm();
        return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, data);

    }

    /* 工地模块 * 4 */
    @ApiOperation(value = "获取工地垃圾种类占比图数据", httpMethod = "GET")
    @GetMapping("/proportionOfGarbageTypes")
    @ControllerEndpoint(operation = "工地垃圾种类占比图", exceptionMessage = "工地垃圾种类占比图获取异常")
    public Resp proportionOfGarbageTypes(){
        long startTime = System.currentTimeMillis();
        try {
            List<SourceTypeAndNumber> ret = sourceInfoMapper.proportionOfGarbageTypes();
            long endTime = System.currentTimeMillis();
            LOGGER.info("工地垃圾种类占比图成功,用时:" + (endTime-startTime) + "ms");
            return Resp.getInstantiationSuccess("工地垃圾种类占比图成功", Resp.LIST, ret);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("工地垃圾种类占比图失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("工地垃圾种类占比图失败:"+e.getMessage(), Resp.LIST, null);
        }
    }

    @ApiOperation(value = "获取不同工地垃圾数量直方图数据", httpMethod = "GET")
    @GetMapping("/quantityOfGarbage")
    @ControllerEndpoint(operation = "不同工地垃圾数量直方图", exceptionMessage = "不同工地垃圾数量直方图获取异常")
    public Resp quantityOfGarbage(){
        long startTime = System.currentTimeMillis();
        try {
            List<SourceNameAndWasteTotal> ret = sourceInfoMapper.quantityOfGarbage();
            long endTime = System.currentTimeMillis();
            LOGGER.info("不同工地垃圾数量直方图成功,用时:" + (endTime-startTime) + "ms");
            return Resp.getInstantiationSuccess("不同工地垃圾数量直方图成功", Resp.LIST, ret);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("不同工地垃圾数量直方图失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("不同工地垃圾数量直方图失败:"+e.getMessage(), Resp.LIST, null);
        }
    }

    @ApiOperation(value = "获取工地位置散点图数据", httpMethod = "GET")
    @GetMapping("/scatterOfSourceLocation")
    @ControllerEndpoint(operation = "工地位置散点图", exceptionMessage = "工地位置散点图获取异常")
    public Resp scatterOfSourceLocation(QueryRequest queryRequest, SourceInfo sourceInfo){
        long startTime = System.currentTimeMillis();
        try {
            List<SourceInfo> ret = sourceInfoService.getAllList(queryRequest, sourceInfo);
            long endTime = System.currentTimeMillis();
            LOGGER.info("工地位置散点图成功,用时:" + (endTime-startTime) + "ms");
            return Resp.getInstantiationSuccess("工地位置散点图成功", Resp.LIST, ret);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("工地位置散点图失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("工地位置散点图失败:"+e.getMessage(), Resp.LIST, null);
        }
    }

    @ApiOperation(value = "获取每月垃圾总量数据", httpMethod = "GET")
    @GetMapping("/quantityOfGarbageByMouth")
    @ControllerEndpoint(operation = "每月垃圾总量", exceptionMessage = "每月垃圾总量获取异常")
    public Resp quantityOfGarbageByMouth(){
        long startTime = System.currentTimeMillis();
        try {
            List<DateAndTotal> ret = sourceInfoMapper.quantityOfGarbageByMouth();
            long endTime = System.currentTimeMillis();
            LOGGER.info("获取每月垃圾总量数据成功,用时:" + (endTime-startTime) + "ms");
            return Resp.getInstantiationSuccess("获取每月垃圾总量数据成功", Resp.LIST, ret);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("获取每月垃圾总量数据失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("获取每月垃圾总量数据失败:"+e.getMessage(), Resp.LIST, null);
        }
    }


    /* 消纳场模块 * 2 */
    @ApiOperation(value = "获取消纳场审核状态饼图数据", httpMethod = "GET")
    @GetMapping("/proportionOfIntakeReview")
    @ControllerEndpoint(operation = "消纳场审核状态饼图", exceptionMessage = "消纳场审核状态饼图获取异常")
    public Resp proportionOfIntakeReview(){
        long startTime = System.currentTimeMillis();
        try {
            List<ReviewAndNumber> ret = intakePlantInfoMapper.proportionOfIntakeReview();
            long endTime = System.currentTimeMillis();
            LOGGER.info("消纳场审核状态饼图成功,用时:" + (endTime-startTime) + "ms");
            return Resp.getInstantiationSuccess("消纳场审核状态饼图成功", Resp.LIST, ret);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("消纳场审核状态饼图失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("消纳场审核状态饼图失败:"+e.getMessage(), Resp.LIST, null);
        }
    }

    @ApiOperation(value = "获取消纳场位置散点图数据", httpMethod = "GET")
    @GetMapping("/scatterOfIntakeLocation")
    @ControllerEndpoint(operation = "消纳场位置散点图", exceptionMessage = "消纳场位置散点图获取异常")
    public Resp scatterOfIntakeLocation(QueryRequest queryRequest, IntakePlantInfo intakePlantInfo){
        long startTime = System.currentTimeMillis();
        try {
            List<IntakePlantInfo> ret = intakePlantInfoService.getAllList(queryRequest, intakePlantInfo);
            long endTime = System.currentTimeMillis();
            LOGGER.info("消纳场位置散点图成功,用时:" + (endTime-startTime) + "ms");
            return Resp.getInstantiationSuccess("消纳场位置散点图成功", Resp.LIST, ret);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("消纳场位置散点图失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("消纳场位置散点图失败:"+e.getMessage(), Resp.LIST, null);
        }
    }

}
