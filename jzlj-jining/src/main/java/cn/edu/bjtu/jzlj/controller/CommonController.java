package cn.edu.bjtu.jzlj.controller;

import java.util.List;

import cn.edu.bjtu.jzlj.service.CommonInfoService;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.CarInfo;
import cn.edu.bjtu.jzlj.service.CarInfoService;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.results.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wangzhijin <wangzhijin@kuaishou.com>
 * Created on 2021-08-11
 */
@Api(description = "公用信息接口")
@RestController
@RequestMapping("/commonInfo")
public class CommonController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    CommonInfoService commonInfoService;

    @ApiOperation(value = "智慧监管平台车辆列表信息查询", httpMethod = "GET")
    @GetMapping("/getCarInfo")
    @ControllerEndpoint(operation = "智慧监管平台车辆列表信息查询", exceptionMessage = "智慧监管平台车辆列表信息查询失败")
    public Resp getCarInfo() {
        long startTime = System.currentTimeMillis();
        try {
            JSONArray jsonArray = commonInfoService.getQueryVehicleInfo();
            long endTime = System.currentTimeMillis();
            LOGGER.info("智慧监管平台车辆列表信息查询成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("车辆查询查看", Resp.LIST, jsonArray);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("智慧监管平台车辆列表信息查询失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("查询失败" + e.getMessage(), Resp.LIST, null);
        }
    }

}
