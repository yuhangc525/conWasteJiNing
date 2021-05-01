package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.util.results.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "APP客户端接口")
@RestController
@RequestMapping("/app")
public class TestController {

    @ApiOperation(value = "新增车辆调度时间", httpMethod = "GET")
    @GetMapping("/test")

    public Resp test()
    {
        return  Resp.getInstantiationSuccess("ok",Resp.STRING,"Are you ok?");
    }
}
