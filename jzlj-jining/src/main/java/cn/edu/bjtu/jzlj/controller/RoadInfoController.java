package cn.edu.bjtu.jzlj.controller;


import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.service.RoadInfoService;
import cn.edu.bjtu.jzlj.util.results.Resp;
import cn.edu.bjtu.jzlj.vo.RoadInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 田英杰
 * @Description:
 * @Date 2021/4/14 14:48
 * @Param * @param null
 * @return
 * @throws:
 **/
@Api(description = "路段信息接口")
@RestController
@RequestMapping("/roadInfo")
public class RoadInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoadInfoController.class);

    @Autowired
    private RoadInfoService roadInfoService;


    @ApiOperation(value = "添加路段信息", httpMethod = "POST")
    @PostMapping("/insertRoadInfo")
    public Resp insertRoadInfo(@RequestBody RoadInfoVo roadInfoVo) {
        long time = System.currentTimeMillis();
        try {
            Boolean bl = roadInfoService.insertRoadInfo(roadInfoVo);
            LOGGER.info("新增路段信息成功，用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationSuccess("新增路段信息成功", Resp.SINGLE, bl);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("新增路段信息异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("新增路段信息异常", Resp.STRING, null);
        }
    }


     /**
      * @Author: 田英杰
      * @Description: 查询路段信息
      * @Date 2021/4/16 11:23
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "查询路段信息", httpMethod = "GET")
    @GetMapping("/getRoadInfo")
    public Resp getRoadInfo(RoadInfo roadInfo){
        long time = System.currentTimeMillis();
        try{

            RoadInfo roadInfo1 = roadInfoService.getRoadInfo(roadInfo.getRoadId());
            LOGGER.info("查询路段信息成功，用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationSuccess("查询路段信息成功", Resp.SINGLE, roadInfo1.getRoadAddress());
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error("查询路段信息异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("查询路段信息异常", Resp.STRING, null);
        }
    }

    @ApiOperation(value = "根据road_name判断插入或者更新数据，并返回road_id", httpMethod = "PUT")
    @PutMapping("/updateOrInsertRoad")
    @ControllerEndpoint(operation = "更新或插入成功！", exceptionMessage = "操作失败")
    public Resp updateOrInsertRoad(@RequestBody RoadInfo roadInfo) {
        long startTime = System.currentTimeMillis();
        try {
            // 业务逻辑
            Integer id = roadInfoService.updateOrInsertRoad(roadInfo);
            if(id == null){
                throw new Exception("roadName为空！Error！");
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据road_name判断插入或者更新数据，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("updateOrInsertRoad成功", Resp.STRING, id);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据road_name判断插入或者更新数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("updateOrInsertRoad失败："+ e.getMessage(),Resp.LIST,null );
        }
    }

    @ApiOperation(value = "根据roadId查询数据", httpMethod = "GET")
    @GetMapping("/selectByRoadId")
    @ControllerEndpoint(operation = "根据roadId查询数据", exceptionMessage = "查询失败")
    public Resp getRoadInfoByRoadId(RoadInfo roadInfo) {
        long startTime = System.currentTimeMillis();
        try {
            JSONObject jsonObject = JSONObject.fromObject(roadInfo);
            List<RoadInfo> roadInfoList = roadInfoService.getRoadInfoByRoadId(jsonObject.getString("roadId"));
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据roadId查询数据，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("查询成功", Resp.STRING, roadInfoList);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据roadId查询数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("根据roadId查询数据失败："+ e.getMessage(),Resp.LIST,null );
        }
//
    }

}
