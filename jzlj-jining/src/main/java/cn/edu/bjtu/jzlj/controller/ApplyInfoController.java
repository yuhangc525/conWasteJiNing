package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.ApplyInfo;
import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.service.ApplyInfoService;
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
import java.util.UUID;

/**
 * @ClassName: ApplyInfoController
 * @Description:
 * @Author sjyzj
 * @Date 2021/4/13 10:38
 */

@Api(description = "审核单信息接口")
@RestController
@RequestMapping("/ApplyInfo")
public class ApplyInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyInfoController.class);

    @Autowired
    private ApplyInfoService applyInfoService;

    /**
     * @Author: sjyzj
     * @Description:
     * @Date 2021/4/13 10:41
     * @Param null
     * @return
     * @throws:
     **/
    @ApiOperation(value = "查询审核单列表", httpMethod = "GET")
    @GetMapping("/selectAll")
    @ControllerEndpoint(operation = "查询审核单列表", exceptionMessage = "审核单列表查询失败")
    public Resp getListByPage(QueryRequest queryRequest, ApplyInfo applyInfo){
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
                IPage<ApplyInfo> applyInfoList = applyInfoService.getListByPage(queryRequest, applyInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, applyInfoList);
            }
            else
            {
                List<ApplyInfo> applyInfoList = applyInfoService.getAllList(queryRequest,applyInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("消纳场列表查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("消纳场列表查询查看", Resp.LIST, applyInfoList);
            }
        }
        catch (Exception e)
        {
            long endTime = System.currentTimeMillis();
            LOGGER.error("分页列举失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }
    }


    @ApiOperation(value = "普通用户插入数据", httpMethod = "POST")
    @PostMapping("/insert")
    @ControllerEndpoint(operation = "新增", exceptionMessage = "新增失败")
    public Resp insertinfo(@RequestBody ApplyInfo applyInfo){
        long startTime = System.currentTimeMillis();
        if (null == applyInfo) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
            if(applyInfo.getApplyId().equals("")){
                applyInfo.setApplyId(UUID.randomUUID().toString().replaceAll("-",""));
            }
            applyInfoService.insertinfo(applyInfo);
            long endTime = System.currentTimeMillis();
            LOGGER.info("创建成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("创建失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("创建异常，原因：" + e.getMessage(), Resp.SINGLE, applyInfo);
        }
    }


    /**
     * @Author: sjyzj
     * @Description:
     * @Date 2021/4/13 14:00
     * @Param applyId
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "普通用户删除数据",httpMethod = "DELETE")
    @DeleteMapping("/{delete}")
    @ControllerEndpoint(operation = "普通用户删除数据", exceptionMessage = "普通用户删除数据失败")
    public Resp deleteByApply_id(@RequestParam("applyID") String applyId){
        long startTime = System.currentTimeMillis();
        try {
            applyInfoService.deleteByApply_id(applyId);
            long endTime = System.currentTimeMillis();
            LOGGER.info("批量删除成功,用时:" + (endTime-startTime) + "ms");
            return Resp.getInstantiationSuccess("批量删除成功", Resp.LIST, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("批量删除失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("批量删除失败:"+e.getMessage(), Resp.LIST, null);
        }
    }


    /**
     * @Author: sjyzj
     * @Description:
     * @Date 2021/4/13 14:00
     * @Param null
     * @return
     * @throws:
     **/
    @ApiOperation(value = "根据ApplyId查询数据", httpMethod = "GET")
    @GetMapping("/selectByApplyId")
    @ControllerEndpoint(operation = "根据ApplyId查询数据", exceptionMessage = "查询失败")
    public Resp getApplyInfoByApplyId(ApplyInfo applyInfo) {
        long startTime = System.currentTimeMillis();
        try {
            JSONObject jsonObject = JSONObject.fromObject(applyInfo);
            List<ApplyInfo> applyInfoList = applyInfoService.getApplyInfoByApplyId(jsonObject.getString("applyId"));
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据ApplyId查询数据，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("查询成功", Resp.STRING, applyInfoList);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据ApplyId查询数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("根据ApplyId查询数据失败："+ e.getMessage(),Resp.LIST,null );
        }
//
    }

    /**
     * @Author: sjyzj
     * @Description:
     * @Date 2021/4/13 15:18
     * @Param intakePlantInfo
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "普通用户更新数据", httpMethod = "PUT")
    @PutMapping("/updateData")
    @ControllerEndpoint(operation = "普通用户更新数据", exceptionMessage = "更新失败")
    public Resp updateinfo(@RequestBody ApplyInfo applyInfo) {
        long startTime = System.currentTimeMillis();
        try {
            if(applyInfo.getApplyId() == null){
                long endTime = System.currentTimeMillis();
                LOGGER.error("修改失败，原因：修改不存在，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationError("修改失败，原因：修改的id不存在，", null, null);
            }
            applyInfoService.updateinfo(applyInfo);
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


