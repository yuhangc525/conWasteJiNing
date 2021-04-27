package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.CarTrail;
import cn.edu.bjtu.jzlj.dao.CarTrailNew;
import cn.edu.bjtu.jzlj.service.CarTrailNewService;
import cn.edu.bjtu.jzlj.service.CarTrailService;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.results.Resp;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: jzlj
 * @description: part of Car_Trail_Info, faster
 * @author: wzj
 * @create: 2020-12-23 15:32
 **/

@Api(description = "车辆轨迹新接口（数据更少）")
@RestController
@RequestMapping(value = "/carTrailNew")
public class CarTrailNewController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CarTrailController.class);

    @Autowired
    CarTrailNewService carTrailNewService;

    @ApiOperation(value = "车辆历史轨迹查询", httpMethod = "POST")
    @RequestMapping(value = "history")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @ControllerEndpoint(operation = "车辆历史轨迹查询", exceptionMessage = "车辆历史轨迹查询失败")
    public Resp getListByPage(QueryRequest queryRequest, @RequestParam("START_TIME") String sTime, @RequestParam("END_TIME") String eTime, CarTrailNew carTrail) {

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
                IPage<CarTrailNew> carTrailList = carTrailNewService.getListByPage(queryRequest, sTime, eTime, carTrail);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, carTrailList);
            } else {
                List<CarTrailNew> carTrailList = carTrailNewService.getAllList(queryRequest, sTime, eTime, carTrail);
                long endTime = System.currentTimeMillis();
                LOGGER.info("车辆轨迹查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("车辆轨迹查询查看", Resp.LIST, carTrailList);
            }
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("分页列举失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }
    }

    @ApiOperation(value = "车辆实时轨迹查询", httpMethod = "POST")
    @RequestMapping(value = "/dynamic", method = RequestMethod.POST)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @ControllerEndpoint(operation = "车辆实时轨迹查询", exceptionMessage = "车辆历史轨迹查询失败")
    public Resp getDynamicTrail(@RequestParam("CAR_ID") String CAR_ID, @RequestParam("ID") Integer ID) {
        //查询列表数据
        long startTime = System.currentTimeMillis();

//        JSONObject jsonobject = (JSONObject) JSONObject.toJSON(cartime);
//        IPage<CarTrailNew> carTrailList = carTrailNewService.getDynamicTrail(jsonobject.getString("CAR_ID"),
//                Integer.parseInt(jsonobject.getString("ID")));
        IPage<CarTrailNew> carTrailList = carTrailNewService.getDynamicTrail(CAR_ID,ID);
        long endTime = System.currentTimeMillis();
        LOGGER.info("车辆轨迹查询成功，用时" + (endTime - startTime) + "ms");
        return Resp.getInstantiationSuccess("车辆轨迹查询查看", Resp.LIST, carTrailList);
    }
}