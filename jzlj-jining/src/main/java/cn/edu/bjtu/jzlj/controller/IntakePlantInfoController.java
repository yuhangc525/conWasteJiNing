package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.service.IntakePlantInfoService;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.UuidTool;
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
 * @ClassName: IntakePlantController
 * @Description:
 * @Author 55057
 * @Date 2020/12/8 9:50
 */
@Api(description = "消纳场信息接口")
@RestController
@RequestMapping("/newIntakePlant")
public class IntakePlantInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IntakePlantInfoController.class);

    @Autowired
    private IntakePlantInfoService intakePlantInfoService;

    /* * @Author: 55057
     * @Description:
     * @Date 2020/12/8 15:01
     * @Param queryRequest
     * @param intakePlantInfo
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "查询消纳场列表", httpMethod = "GET")
    @GetMapping("/selectAll")
    @ControllerEndpoint(operation = "查询消纳场列表", exceptionMessage = "消纳场列表查询失败")
    public Resp getListByPage(QueryRequest queryRequest, IntakePlantInfo intakePlantInfo){
        //查询列表数据
        long startTime = System.currentTimeMillis();
        if (queryRequest.getPageNo() < 1 || queryRequest.getPageNo() < 1)
        {
            return Resp.getInstantiationError("分页查看失败，分页页数或分页大小不合法", null, null);
        }
        try
        {
            /*分页查询*/
            if(queryRequest.isPageFlag())
            {
                if (queryRequest.getPageNo() < 1 || queryRequest.getPageNo() < 1)
                {
                    return Resp.getInstantiationError("分页查看失败，分页页数或分页大小不合法", null, null);
                }
                IPage<IntakePlantInfo> intakePlantInfoList = intakePlantInfoService.getListByPage(queryRequest, intakePlantInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, intakePlantInfoList);
            }
            else
            {
                List<IntakePlantInfo> intakePlantInfoList = intakePlantInfoService.getAllList(queryRequest,intakePlantInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("消纳场列表查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("消纳场列表查询查看", Resp.LIST, intakePlantInfoList);
            }
        }
        catch (Exception e)
        {
            long endTime = System.currentTimeMillis();
            LOGGER.error("分页列举失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }
    }

    /* * @Author: 55057
     * @Description: 普通用户插入数据
     * @Date 2020/12/8 15:05
     * @Param
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "普通用户插入数据", httpMethod = "POST")
    @PostMapping("/insert")
    @ControllerEndpoint(operation = "新增", exceptionMessage = "新增失败")
    public Resp insertinfo(@RequestBody IntakePlantInfo intakePlantInfo){
        long startTime = System.currentTimeMillis();
        if (null == intakePlantInfo) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
            intakePlantInfo.setIntakePlantId(UuidTool.getUUID());
            intakePlantInfoService.insertinfo(intakePlantInfo);
            long endTime = System.currentTimeMillis();
            LOGGER.info("创建成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("创建失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("创建异常，原因：" + e.getMessage(), Resp.SINGLE, intakePlantInfo);
        }
    }

    /* * @Author: 55057
     * @Description: 普通用户更新数据
     * @Date 2020/12/8 15:05
     * @Param
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "普通用户更新数据", httpMethod = "PUT")
    @PutMapping("/updateData")
    @ControllerEndpoint(operation = "普通用户更新数据", exceptionMessage = "更新失败")
    public Resp updateinfo(@RequestBody IntakePlantInfo intakePlantInfo) {
        long startTime = System.currentTimeMillis();
        try {
            if(intakePlantInfo.getIntakePlantId() == null){
                long endTime = System.currentTimeMillis();
                LOGGER.error("修改失败，原因：修改不存在，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationError("修改失败，原因：修改的id不存在，", null, null);
            }
            intakePlantInfoService.updateinfo(intakePlantInfo);
            long endTime =  System.currentTimeMillis();
            LOGGER.info("修改成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("修改成功", null, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("普通用户更新数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("普通用户更新数据失败："+ e.getMessage(),Resp.LIST,null );
        }
    }
    /* * @Author: 55057
     * @Description: 普通用户删除数据
     * @Date 2020/12/8 15:19
     * @Param null
     * @return
     * @throws:
     **/

    @ApiOperation(value = "普通用户删除数据",httpMethod = "DELETE")
    @DeleteMapping("/{delete}")
    @ControllerEndpoint(operation = "普通用户删除数据", exceptionMessage = "普通用户删除数据失败")
    public Resp deleteByINTAKE_PLANT_ID(@RequestParam("intakePlantId") String intakePlantId){
        long startTime = System.currentTimeMillis();
        try {
            intakePlantInfoService.deleteByINTAKE_PLANT_ID(intakePlantId);
            long endTime = System.currentTimeMillis();
            LOGGER.info("批量删除成功,用时:" + (endTime-startTime) + "ms");
            return Resp.getInstantiationSuccess("批量删除成功", Resp.LIST, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("批量删除失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("批量删除失败:"+e.getMessage(), Resp.LIST, null);
        }
    }

    /* * @Author: 55057
     * @Description: 管理员更新审核状态
     * @Date 2020/12/8 15:22
     * @Param intakePlantInfo
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "管理员更新审核状态", httpMethod = "PUT")
    @PutMapping("/updateReviewStatus")
    @ControllerEndpoint(operation = "管理员更新审核状态", exceptionMessage = "管理员更新审核状态失败")
    public Resp updateReviewStatus(IntakePlantInfo intakePlantInfo) {
        long startTime = System.currentTimeMillis();
        try {
            JSONObject jsonObject = JSONObject.fromObject(intakePlantInfo);
            int intakeplantinfo = intakePlantInfoService.updateReviewStatus(jsonObject.getInt("reviewStatus"),jsonObject.getString("intakePlantId"));
            long endTime = System.currentTimeMillis();
            LOGGER.info("管理员更新审核状态成功，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("管理员更新审核状态成功", Resp.STRING, intakeplantinfo);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("管理员更新审核状态失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("管理员更新审核状态失败："+ e.getMessage(),Resp.LIST,null );
        }
    }


    /* * @Author: 55057
     * @Description: 根据INTAKE_PLANT_ID查询数据
     * @Date 2020/12/8 15:25
     * @Param null
     * @return 
     * @throws:
     **/
    @ApiOperation(value = "根据INTAKE_PLANT_ID查询数据", httpMethod = "GET")
    @GetMapping("/selectByINTAKE_PLANT_ID")
    @ControllerEndpoint(operation = "根据INTAKE_PLANT_ID查询数据", exceptionMessage = "查询失败")
    public Resp getIntakePlantInfoByINTakePlantID(IntakePlantInfo intakePlantInfo) {
        long startTime = System.currentTimeMillis();
        try {
            JSONObject jsonObject = JSONObject.fromObject(intakePlantInfo);
            List<IntakePlantInfo> intakePlantInfoList = intakePlantInfoService.getIntakePlantInfoByINTakePlantID(jsonObject.getString("intakePlantId"));
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据INTAKE_PLANT_ID查询数据，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("查询成功", Resp.STRING, intakePlantInfoList);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据INTAKE_PLANT_ID查询数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("根据INTAKE_PLANT_ID查询数据失败："+ e.getMessage(),Resp.LIST,null );
        }
//
    }

    /* * @Author: 55057
     * @Description: 根据INTAKE_PLANT_ID查询数据
     * @Date 2020/12/8 15:25
     * @Param null
     * @return
     * @throws:
     **/
    @ApiOperation(value = "根据InputName查询数据", httpMethod = "GET")
    @GetMapping("/selectByInputName")
    @ControllerEndpoint(operation = "根据InputName查询数据", exceptionMessage = "查询失败")
    public Resp getIntakePlantInfoByInputName(IntakePlantInfo intakePlantInfo) {
        long startTime = System.currentTimeMillis();
        try {
            JSONObject jsonObject = JSONObject.fromObject(intakePlantInfo);
            List<IntakePlantInfo> intakePlantInfoList = intakePlantInfoService.getIntakePlantInfoByInputName(jsonObject.getString("inputName"));
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据InputName查询数据，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("查询成功", Resp.STRING, intakePlantInfoList);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据InputName查询数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("根据InputName查询数据失败："+ e.getMessage(),Resp.LIST,null );
        }
//
    }

    /*
     * @Author zjwang
     * @Description 根据intake_name判断插入或者更新数据，并返回intake_id
     * @Date 11:27 2021/4/22
     * @ParamTypes [cn.edu.bjtu.jzlj.dao.IntakePlantInfo]
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     **/
    @ApiOperation(value = "根据intake_name判断插入或者更新数据，并返回intake_id", httpMethod = "PUT")
    @PutMapping("/updateOrInsertIntake")
    @ControllerEndpoint(operation = "更新或插入成功！", exceptionMessage = "操作失败")
    public Resp updateOrInsertIntake(@RequestBody IntakePlantInfo intakePlantInfo) {
        long startTime = System.currentTimeMillis();
        try {
            // 业务逻辑
            String id = intakePlantInfoService.updateOrInsertIntake(intakePlantInfo);
            if(id == null){
                throw new Exception("intakePlantName为空！Error！");
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据intake_name判断插入或者更新数据，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("updateOrInsertIntake成功", Resp.STRING, id);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据intake_name判断插入或者更新数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("updateOrInsertIntake失败："+ e.getMessage(),Resp.LIST,null );
        }
//
    }

    /**
     * @Author: sjyzj
     * @Description: 
     * @Date 2021/4/29 9:46
     * @Param intakePlantInfo
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "根据ApplyInfo更新数据", httpMethod = "PUT")
    @PutMapping("/updateDataAccordingApplyInfo")
    @ControllerEndpoint(operation = "根据ApplyInfo更新数据", exceptionMessage = "更新失败")
    public Resp updateDataAccordingApplyInfo(@RequestBody IntakePlantInfo intakePlantInfo) {
        long startTime = System.currentTimeMillis();
        try {
            if(intakePlantInfo.getIntakePlantId() == null){
                long endTime = System.currentTimeMillis();
                LOGGER.error("修改失败，原因：修改不存在，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationError("修改失败，原因：修改的id不存在，", null, null);
            }
            intakePlantInfoService.updateInfoByApplyInfo(intakePlantInfo);
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
