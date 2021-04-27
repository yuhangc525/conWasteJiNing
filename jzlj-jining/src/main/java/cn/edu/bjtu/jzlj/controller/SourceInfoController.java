package cn.edu.bjtu.jzlj.controller;

import java.util.List;


import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.SourceInfo;
//import cn.edu.bjtu.jzlj.dao.SourceInfoNew;
import cn.edu.bjtu.jzlj.service.SourceInfoService;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.results.Resp;
import io.swagger.annotations.Api;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @ClassName:
 * @Description:对应前端产生源页面
 * @Author:wangchunxia
 * @Date 2020/11/8 18:10
 */
@Api(description = "产生源接口")
@RestController
@CrossOrigin
@RequestMapping("/sourceInfo")
public class SourceInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SourceInfoController.class);
    @Autowired
    private SourceInfoService sourceInfoService;
    /* *
     * @Author: wcx
     * @Description: 产生源列表查询
     * @Date 2020/11/8 18:42
     * @Param
     * @return Result :Resp
     * @throws:
     **/
  //@PassToken
//    @GetMapping("/ListSourceInfo")
    @ApiOperation(value = "产生源列表查询", httpMethod = "GET")
    @GetMapping("/listInfoByPage")
    @ControllerEndpoint(operation = "产生源列表查询", exceptionMessage = "产生源列表查询失败")
    public Resp getListByPage(QueryRequest queryRequest, SourceInfo sourceInfo){
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
                IPage<SourceInfo> sourceInfoList = sourceInfoService.getListByPage(queryRequest, sourceInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, sourceInfoList);
            }
            else
            {
                List<SourceInfo> sourceInfoList = sourceInfoService.getAllList(queryRequest,sourceInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("产生源列表查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("产生源列表查询查看", Resp.LIST, sourceInfoList);
            }
        }
        catch (Exception e)
        {
            long endTime = System.currentTimeMillis();
            LOGGER.error("分页列举失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }
    }
    /*
     /* *
     * @Author: wcx
     * @Description: 新增
     * @Date 2020/11/10 19:12
     * @Param
     * @return Result :Resp
     * @throws:
    * */
    @ApiOperation(value = "新增产生源", httpMethod = "POST")
    @PostMapping("/createSource")
    @ControllerEndpoint(operation = "新增", exceptionMessage = "新增失败")
    public Resp save(@RequestBody SourceInfo sourceInfo){
        long startTime = System.currentTimeMillis();
        if (null == sourceInfo) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
            sourceInfoService.saveData(sourceInfo);
            long endTime = System.currentTimeMillis();
            LOGGER.info("创建成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("创建失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("创建异常，原因：" + e.getMessage(), Resp.SINGLE, sourceInfo);
        }
    }

    /**
     * @method  update
     * @description 更新
     * @date: 2020-11-08
     * @author: wcx
     * @param
     * @return Resp
     */
    @ApiOperation(value = "编辑用户", httpMethod = "PUT")
    @PutMapping("/putSource")
    @ControllerEndpoint(operation = "编辑用户", exceptionMessage = "编辑失败")
    public Resp update(@RequestBody  SourceInfo sourceInfo){
        long startTime =  System.currentTimeMillis();
        try {
            //更新
            if(sourceInfoService.getById(sourceInfo.getSourceId()) == null){
                long endTime = System.currentTimeMillis();
                LOGGER.error("修改失败，原因：修改不存在，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationError("修改失败，原因：修改的id不存在，", null, null);
            }
            sourceInfoService.updateData(sourceInfo);
            long endTime =  System.currentTimeMillis();
            LOGGER.info("修改成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("修改成功", null, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("修改失败，原因："+ e.getMessage() +"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("修改失败：" + e.getMessage(), null, null);
        }
    }
    /**
     * @method  batchDelete
     * @description 根据sourceid批量删除
     * @date: 2020-11-10
     * @author: wcx
     * @param
     * @return Resp
     */
    @ApiOperation(value = "根据产生源id批量删除",httpMethod = "DELETE")
    @DeleteMapping("/{sourceId}")
    @ControllerEndpoint(operation = "根据id批量删除", exceptionMessage = "根据id批量删除失败")
    public Resp batchDelete(@RequestParam("sourceId") List<String> sourceId){
        long startTime = System.currentTimeMillis();
        try {
            sourceInfoService.batchDelete(sourceId);
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
     * @method  update
     * @description 审核状态
     * @date: 2020-12-12
     * @author: yanghui
     * @param: id
     * @return Resp
     */
    @ApiOperation(value = "管理员更新审核状态", httpMethod = "PUT")
    @PutMapping("/updateReviewStatus")
    @ControllerEndpoint(operation = "管理员更新审核状态", exceptionMessage = "管理员更新审核状态失败")
    public Resp updateReviewStatus(SourceInfo sourceInfo) {
        long startTime = System.currentTimeMillis();
        try {
            JSONObject jsonObject = JSONObject.fromObject(sourceInfo);
            int sourceinfo = sourceInfoService.updateReviewStatus(jsonObject.getInt("reviewStatus"),
                    jsonObject.getString("sourceId"));
            long endTime = System.currentTimeMillis();
            LOGGER.info("管理员更新审核状态成功，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("管理员更新审核状态成功", Resp.STRING, sourceinfo);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("管理员更新审核状态失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("管理员更新审核状态失败："+ e.getMessage(),Resp.LIST,null );
        }
    }

    /**
     * @method
     * @description 根据InputName查询数据
     * @date: 2020-12-14
     * @author: wcx
     * @param
     * @return Resp
     */
    @ApiOperation(value = "根据InputName查询数据", httpMethod = "GET")
    @GetMapping("/selectByInputName")
    @ControllerEndpoint(operation = "根据InputName查询数据", exceptionMessage = "查询失败")
    public Resp getSourceInfoNewByInputName(SourceInfo sourceInfo) {
        long startTime = System.currentTimeMillis();
        try {
            JSONObject jsonObject = JSONObject.fromObject(sourceInfo);
            List<SourceInfo> intakeSourceInfoList = sourceInfoService.getSourceInfoByInputName(jsonObject.getString("inputName"));
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据InputName查询数据，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("查询成功", Resp.STRING, intakeSourceInfoList);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据InputName查询数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("根据InputName查询数据失败："+ e.getMessage(),Resp.LIST,null );
        }

//
    }
    /**
     * @method
     * @description 根据SOURCE_ID查询数据
     * @date: 2020-12-14
     * @author: yh
     * @param
     * @return Resp
     */
    @ApiOperation(value = "根据SOURCE_ID查询数据", httpMethod = "GET")
    @GetMapping("selectSourceId")
    @ControllerEndpoint(operation = "根据SOURCE_ID查询数据", exceptionMessage = "查询失败")
    public Resp getSourceInfoNewBySourceId(SourceInfo sourceInfo) {
        long startTime = System.currentTimeMillis();
        try {
            JSONObject jsonObject = JSONObject.fromObject(sourceInfo);
            List<SourceInfo> sourceInfoList =
                    sourceInfoService.getSourceInfoBySourceId(jsonObject.getString("sourceId"));
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据SOURCE_ID查询数据，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("查询成功", Resp.STRING, sourceInfoList);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据SOURCE_ID查询数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("根据SOURCE_ID查询数据失败："+ e.getMessage(),Resp.LIST,null );
        }
//

    }
}
