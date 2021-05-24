package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.EFInfo;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.results.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zjwang
 * @date: 2021/5/24 15:23
 * @description:
 */

@Api(description = "图表接口")
@RestController
@RequestMapping("/graph")
public class GraphController {

    /* 车辆模块 * 5 */

    @ApiOperation(value = "alarmCarNumberLineChart", httpMethod = "GET")
    @GetMapping("/alarmCarNumberLineChart")
    @ControllerEndpoint(operation = "7天报警车辆数量折线变化图", exceptionMessage = "7天报警车辆数量折线变化图获取异常")
    public Resp getAlarmCarNumber(QueryRequest queryRequest, EFInfo efInfo){
        return null;
    }

    @ApiOperation(value = "Proportion of vehicle review", httpMethod = "GET")
    @GetMapping("/proportionOfVehicleReview")
    @ControllerEndpoint(operation = "车辆审核状态占比扇形图", exceptionMessage = "车辆审核状态占比扇形图获取异常")
    public Resp proportionOfVehicleReview(QueryRequest queryRequest, EFInfo efInfo){
        return null;
    }

    @ApiOperation(value = "Proportion of vehicle status", httpMethod = "GET")
    @GetMapping("/proportionOfVehicleStatus")
    @ControllerEndpoint(operation = "车辆使用状态占比扇形图", exceptionMessage = "车辆使用状态占比扇形图获取异常")
    public Resp proportionOfVehicleStatus(QueryRequest queryRequest, EFInfo efInfo){
        return null;
    }

    @ApiOperation(value = "number of vehicles used", httpMethod = "GET")
    @GetMapping("/numberOfVehicleUsed")
    @ControllerEndpoint(operation = "7天车辆使用数量的周变化折线图", exceptionMessage = "7天车辆使用数量的周变化折线图获取异常")
    public Resp numberOfVehicleUsed(QueryRequest queryRequest, EFInfo efInfo){
        return null;
    }

    @ApiOperation(value = "Proportion of correct state of vehicle alarm", httpMethod = "GET")
    @GetMapping("/proportionOfVehicleCorrectAlarm")
    @ControllerEndpoint(operation = "车辆报警状态的误报比例扇形图", exceptionMessage = "车辆报警状态的误报比例扇形图获取异常")
    public Resp proportionOfVehicleCorrectAlarm(QueryRequest queryRequest, EFInfo efInfo){
        return null;
    }

    /* 工地模块 * 4 */
    @ApiOperation(value = "Proportion of garbage types", httpMethod = "GET")
    @GetMapping("/proportionOfGarbageTypes")
    @ControllerEndpoint(operation = "工地垃圾种类占比图", exceptionMessage = "工地垃圾种类占比图获取异常")
    public Resp proportionOfGarbageTypes(QueryRequest queryRequest, EFInfo efInfo){
        return null;
    }

    @ApiOperation(value = "Quantity of garbage in different sources", httpMethod = "GET")
    @GetMapping("/quantityOfGarbage")
    @ControllerEndpoint(operation = "不同工地垃圾数量直方图", exceptionMessage = "不同工地垃圾数量直方图获取异常")
    public Resp quantityOfGarbage(QueryRequest queryRequest, EFInfo efInfo){
        return null;
    }

    @ApiOperation(value = "scatter plot of source location", httpMethod = "GET")
    @GetMapping("/scatterOfSourceLocation")
    @ControllerEndpoint(operation = "工地位置散点图", exceptionMessage = "工地位置散点图获取异常")
    public Resp scatterOfSourceLocation(QueryRequest queryRequest, EFInfo efInfo){
        return null;
    }

    @ApiOperation(value = "Quantity of garbage by month", httpMethod = "GET")
    @GetMapping("/quantityOfGarbageByMouth")
    @ControllerEndpoint(operation = "每月垃圾总量", exceptionMessage = "每月垃圾总量获取异常")
    public Resp quantityOfGarbageByMouth(QueryRequest queryRequest, EFInfo efInfo){
        return null;
    }


    /* 消纳场模块 * 2 */
    @ApiOperation(value = "Proportion of intake review", httpMethod = "GET")
    @GetMapping("/proportionOfIntakeReview")
    @ControllerEndpoint(operation = "消纳场审核状态饼图", exceptionMessage = "消纳场审核状态饼图获取异常")
    public Resp proportionOfIntakeReview(QueryRequest queryRequest, EFInfo efInfo){
        return null;
    }

    @ApiOperation(value = "scatter plot of intake location", httpMethod = "GET")
    @GetMapping("/scatterOfIntakeLocation")
    @ControllerEndpoint(operation = "消纳场位置散点图", exceptionMessage = "消纳场位置散点图获取异常")
    public Resp scatterOfIntakeLocation(QueryRequest queryRequest, EFInfo efInfo){
        return null;
    }

}
