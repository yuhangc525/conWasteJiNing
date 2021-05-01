package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.ResourcePlantInfo;
import cn.edu.bjtu.jzlj.service.ResourcePlantInfoService;
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
 * @ClassName: ResourcePlantInfoController
 * @Description:
 * @Author 55057
 * @Date 2020/12/8 20:03
 */
@Api(description = "资源场信息接口")
@RestController
@RequestMapping("/newResourcePlant")
public class ResourcePlantInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcePlantInfoController.class);

    @Autowired
    private ResourcePlantInfoService resourcePlantInfoService;

    /* * @Author: 55057
     * @Description:
     * @Date 2020/12/8 20:54
     * @Param queryRequest
     * @param
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "查询资源场列表", httpMethod = "GET")
    @GetMapping("/selectAll")
    @ControllerEndpoint(operation = "查询资源场列表", exceptionMessage = "资源场列表查询失败")
    public Resp getListByPage(QueryRequest queryRequest, ResourcePlantInfo resourcePlantInfo){
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
                IPage<ResourcePlantInfo> resourcePlantInfoList = resourcePlantInfoService.getListByPage(queryRequest, resourcePlantInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, resourcePlantInfoList);
            }
            else
            {
                List<ResourcePlantInfo> resourcePlantInfoList = resourcePlantInfoService.getAllList(queryRequest,resourcePlantInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("消纳场列表查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("资源场列表查询查看", Resp.LIST, resourcePlantInfoList);
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
     * @Description:
     * @Date 2020/12/8 15:25
     * @Param null
     * @return
     * @throws:
     **/
    @ApiOperation(value = "根据InputName查询数据", httpMethod = "GET")
    @GetMapping("/selectByInputName")
    @ControllerEndpoint(operation = "根据InputName查询数据", exceptionMessage = "查询失败")
    public Resp getResourcePlantInfoByInputName(ResourcePlantInfo resourcePlantInfo) {
        long startTime = System.currentTimeMillis();
        try {
            JSONObject jsonObject = JSONObject.fromObject(resourcePlantInfo);
            List<ResourcePlantInfo> intakePlantInfoList = resourcePlantInfoService.getResourcePlantInfoByInputName(jsonObject.getString("inputName"));
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
    public Resp insertinfo(@RequestBody ResourcePlantInfo resourcePlantInfo){
        long startTime = System.currentTimeMillis();
        if (null == resourcePlantInfo) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
            resourcePlantInfo.setResourcePlantId(UuidTool.getUUID());
//            resourcePlantInfoService.insertinfo(resourcePlantInfo);
            resourcePlantInfoService.saveData(resourcePlantInfo);
            long endTime = System.currentTimeMillis();
            LOGGER.info("创建成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("创建失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("创建异常，原因：" + e.getMessage(), Resp.SINGLE, resourcePlantInfo);
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
    public Resp updateinfo(@RequestBody ResourcePlantInfo resourcePlantInfo) {
        long startTime = System.currentTimeMillis();
        try {
            if(resourcePlantInfo.getResourcePlantId() == null){
                long endTime = System.currentTimeMillis();
                LOGGER.error("修改失败，原因：修改不存在，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationError("修改失败，原因：修改的id不存在，", null, null);
            }
            resourcePlantInfoService.updateinfo(resourcePlantInfo);
            long endTime = System.currentTimeMillis();
            LOGGER.info("普通用户更新数据成功，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("更新成功", Resp.STRING, null);
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
    public Resp deleteByRESOURCEId(@RequestParam("resourcePlantId") String resourcePlantId){
        long startTime = System.currentTimeMillis();
        try {
            resourcePlantInfoService.deleteByRESOURCEId(resourcePlantId);
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
     * @Param
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "管理员更新审核状态", httpMethod = "PUT")
    @PutMapping("/updateReviewStatus")
    @ControllerEndpoint(operation = "管理员更新审核状态", exceptionMessage = "管理员更新审核状态失败")
    public Resp updateReviewStatus(ResourcePlantInfo resourcePlantInfo) {
        long startTime = System.currentTimeMillis();
        try {
            JSONObject jsonObject = JSONObject.fromObject(resourcePlantInfo);
            int resourceplantinfo = resourcePlantInfoService.updateReviewStatus(jsonObject.getInt("reviewStatus"),jsonObject.getInt("resourcePlantId"));
            long endTime = System.currentTimeMillis();
            LOGGER.info("管理员更新审核状态成功，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("管理员更新审核状态成功", Resp.STRING, resourceplantinfo);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("管理员更新审核状态失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("管理员更新审核状态失败："+ e.getMessage(),Resp.LIST,null );
        }
    }


    /* * @Author: 55057
     * @Description: 根据resourcePlantId查询数据
     * @Date 2020/12/8 15:25
     * @Param null
     * @return
     * @throws:
     **/
    @ApiOperation(value = "根据RESOURCE_PLANT_ID查询数据", httpMethod = "GET")
    @GetMapping("/selectByRESOURCE_PLANT_ID")
    @ControllerEndpoint(operation = "根据RESOURCE_PLANT_ID查询数据", exceptionMessage = "查询失败")
    public Resp getRESOURCEInfoByRESOURCEId(ResourcePlantInfo resourcePlantInfo) {
        long startTime = System.currentTimeMillis();
        try {
            JSONObject jsonObject = JSONObject.fromObject(resourcePlantInfo);
            List<ResourcePlantInfo> resourcePlantInfoList = resourcePlantInfoService.getRESOURCEInfoByRESOURCEId(jsonObject.getString("resourcePlantId"));
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据RESOURCE_PLANT_ID查询数据，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("查询成功", Resp.STRING, resourcePlantInfoList);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据RESOURCE_PLANT_ID查询数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("根据RESOURCE_PLANT_ID查询数据失败："+ e.getMessage(),Resp.LIST,null );
        }
//
    }





}
