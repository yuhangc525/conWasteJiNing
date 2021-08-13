package cn.edu.bjtu.jzlj.controller;

import java.util.List;

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
    CarInfoService carInfoService;

    @ApiOperation(value = "车辆信息查询", httpMethod = "GET")
    @GetMapping("/listCarInfoByPage")
    @ControllerEndpoint(operation = "车辆信息列表查询", exceptionMessage = "车辆信息列表查询失败")
    public Resp getListByPage(QueryRequest queryRequest) {

        //查询列表数据
        long startTime = System.currentTimeMillis();
        if (queryRequest.getPageNo() < 1 || queryRequest.getPageNo() < 1) {
            return Resp.getInstantiationError("分页查看失败，分页页数或分页大小不合法", null, null);
        }
        try {
            /*分页查询*/
            if (queryRequest.isPageFlag()) {
                if (queryRequest.getPageNo() < 1 || queryRequest.getPageNo() < 1) {
                    return Resp.getInstantiationError("分页查看失败，分页页数或分页大小不合法", null, null);
                }
                IPage<CarInfo> carInfoList = carInfoService.getListByPage(queryRequest);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, carInfoList);
            } else {
                List<CarInfo> carInfoList = carInfoService.getAllList(queryRequest);
                long endTime = System.currentTimeMillis();
                LOGGER.info("车辆信息查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("车辆公司查询查看", Resp.LIST, carInfoList);
            }
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("分页列举失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }
    }

}
