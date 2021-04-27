package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.EFInfo;
import cn.edu.bjtu.jzlj.dao.SourceInfo;
import cn.edu.bjtu.jzlj.service.EfInfoService;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.results.Resp;
import io.swagger.models.auth.In;
import net.sf.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: EfInfoController
 * @Description:
 * @Author 55057
 * @Date 2020/12/8 9:09
 */
@Api(description = "电子围栏信息接口")
@RestController
@RequestMapping("/efInfo")
public class EfInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EfInfoController.class);

    @Autowired
    private EfInfoService efInfoService;

    /* * @Author: 55057
     * @Description:
     * @Date 2020/12/8 11:49
     * @Param queryRequest
     * @param efInfo
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "电子围栏列表查询", httpMethod = "GET")
    @GetMapping("/selectAll")
    @ControllerEndpoint(operation = "电子围栏列表查询", exceptionMessage = "电子围栏列表查询失败")
    public Resp getListByPage(QueryRequest queryRequest, EFInfo efInfo){
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
                IPage<EFInfo> efInfoList = efInfoService.getListByPage(queryRequest, efInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, efInfoList);
            }
            else
            {
                List<EFInfo> efInfoList = efInfoService.getAllList(queryRequest,efInfo);
                long endTime = System.currentTimeMillis();
                LOGGER.info("电子围栏列表查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("电子围栏列表查询查看", Resp.LIST, efInfoList);
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
     * @Date 2020/12/8 12:36
     * @Param ids
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "根据id和s_or_i查询电子围栏信息", httpMethod = "PUT")
    @PutMapping("/selectByIdAndSOrI")
    @ControllerEndpoint(operation = "根据id和s_or_i查询电子围栏信息", exceptionMessage = "查询失败")
    public Resp selectByIdAndSOrI(EFInfo efInfo) {
        long startTime = System.currentTimeMillis();
        try {
            JSONObject jsonObject = JSONObject.fromObject(efInfo);
            List<EFInfo> efInfoList = efInfoService.selectByIdAndSOrI(jsonObject.getInt("id"),
                    jsonObject.getString("SOrI"));
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据id和s_or_i查询电子围栏信息成功，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("查询成功", Resp.STRING, efInfoList);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("查询电子围栏失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("查询电子围栏失败："+ e.getMessage(),Resp.LIST,null );
        }
//
    }


    /* * @Author: 55057
     * @Description:
     * @Date 2020/12/8 12:35
     * @Param efInfo
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "新增电子围栏", httpMethod = "POST")
    @PostMapping("/insert")
    @ControllerEndpoint(operation = "新增", exceptionMessage = "新增失败")
    public Resp insert(@RequestBody List<EFInfo> EFInfoList){
        long startTime = System.currentTimeMillis();
        if (null == EFInfoList) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
//            net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(data);
            int sum = 0;
            for (int i = 0; i < EFInfoList.size(); i++) {
//                net.sf.json.JSONObject jsonObject = (net.sf.json.JSONObject) jsonArray.get(i);
//                System.out.println(jsonObject);
                int row = efInfoService.insertEfInfo(EFInfoList.get(i));
                sum = sum + row;
            }
//            efInfoService.insertEfInfo(efInfo);
            long endTime = System.currentTimeMillis();
            LOGGER.info("创建成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("创建失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("创建异常，原因：" + e.getMessage(), Resp.SINGLE, null);
        }
    }

    /* * @Author: 55057
     * @Description:
     * @Date 2020/12/8 12:35
     * @Param null
     * @return
     * @throws:
     **/

    @ApiOperation(value = "根据id和s_or_i批量删除",httpMethod = "DELETE")
    @DeleteMapping("/{delete}")
    @ControllerEndpoint(operation = "根据id和s_or_i批量删除", exceptionMessage = "根据id和s_or_i批量删除失败")
    public Resp batchDelete(@RequestParam("id") Integer id, @RequestParam("SOrI") String sOrI){
        long startTime = System.currentTimeMillis();
        try {
            efInfoService.deleteByIdAndSOrI(id,sOrI);
            long endTime = System.currentTimeMillis();
            LOGGER.info("批量删除成功,用时:" + (endTime-startTime) + "ms");
            return Resp.getInstantiationSuccess("批量删除成功", Resp.LIST, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("批量删除失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("批量删除失败:"+e.getMessage(), Resp.LIST, null);
        }
    }

}
