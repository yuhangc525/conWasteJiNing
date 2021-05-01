package cn.edu.bjtu.jzlj.controller;


import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.CarCompany;
import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.service.CarCompanyService;

import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.UuidTool;
import cn.edu.bjtu.jzlj.util.results.Resp;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.UUID;

/** @Author: wzj
 * @Description: 
 * @Date 2020/11/12 17:23
 **/


@Api(description = "车辆公司信息接口")
@RestController
@RequestMapping("/carCompany")
public class CarCompanyController {
    
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);
    
    @Autowired
    CarCompanyService carCompanyService;


    /** @Author: wzj
     * @Description: 
     * @Date 2020/11/12 11:30
     * @Param queryRequest
     * @param
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    
    @ApiOperation(value = "车辆公司查询", httpMethod = "GET")
    @GetMapping("/listCarCompanyByPage")
    @ControllerEndpoint(operation = "车辆公司列表查询", exceptionMessage = "车辆公司列表查询失败")
    public Resp getListByPage(QueryRequest queryRequest, CarCompany carCompany){


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
                IPage<CarCompany> carCompanyList = carCompanyService.getListByPage(queryRequest, carCompany);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, carCompanyList);
            }
            else
            {
                List<CarCompany> carCompanyList = carCompanyService.getAllList(queryRequest, carCompany);
                long endTime = System.currentTimeMillis();
                LOGGER.info("车辆公司查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("车辆公司查询查看", Resp.LIST, carCompanyList);
            }
        }
        catch (Exception e)
        {
            long endTime = System.currentTimeMillis();
            LOGGER.error("分页列举失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }
    }

    @ApiOperation(value = "车辆公司根据输入人姓名查询", httpMethod = "GET")
    @GetMapping("/selectByInputName")
    @ControllerEndpoint(operation = "车辆公司根据输入人姓名查询", exceptionMessage = "车辆公司根据输入人姓名查询失败")
    public Resp selectByInputName(QueryRequest queryRequest, String inputName){


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
                IPage<CarCompany> carCompanyList = carCompanyService.getListByPage(queryRequest,inputName);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, carCompanyList);
            }
            else
            {
                List<CarCompany> carCompanyList = carCompanyService.getAllList(queryRequest,inputName);
                long endTime = System.currentTimeMillis();
                LOGGER.info("车辆公司查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("车辆公司查询查看", Resp.LIST, carCompanyList);
            }
        }
        catch (Exception e)
        {
            long endTime = System.currentTimeMillis();
            LOGGER.error("分页列举失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }
    }


    /** @Author: wzj
     * @Description:
     * @Date 2020/12/13 14:34
     * @Param carCompany
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "新增车辆公司", httpMethod = "POST")
    @PostMapping("/createCarCompany")
    @ControllerEndpoint(operation = "新增", exceptionMessage = "新增失败")
    public Resp save(@RequestBody CarCompany carCompany){
        long startTime = System.currentTimeMillis();
        if (null == carCompany) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
            carCompany.setCarCompanyId(UuidTool.getUUID());
            carCompanyService.saveData(carCompany);
            long endTime = System.currentTimeMillis();
            LOGGER.info("创建成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("创建失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("创建异常，原因：" + e.getMessage(), Resp.SINGLE, carCompany);
        }
    }

    
    /** @Author: wzj
     * @Description: 
     * @Date 2020/11/12 11:34
     * @Param carCompany
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "车辆公司更改", httpMethod = "PUT")
    @PutMapping("/putCarCompany")
    @ControllerEndpoint(operation = "编辑", exceptionMessage = "编辑失败")
    public Resp update(@RequestBody  CarCompany carCompany){
        long startTime =  System.currentTimeMillis();
        try {
            //更新
            if(carCompanyService.getById(carCompany.getCarCompanyId()) == null){
                long endTime = System.currentTimeMillis();
                LOGGER.error("修改失败，原因：修改不存在，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationError("修改失败，原因：修改的id不存在，", null, null);
            }
            carCompanyService.updateData(carCompany);
            long endTime =  System.currentTimeMillis();
            LOGGER.info("修改成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("修改成功", null, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("修改失败，原因："+ e.getMessage() +"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("修改失败：" + e.getMessage(), null, null);
        }
    }

    /** @Author: wzj
     * @Description:
     * @Date 2020/12/13 14:34
     * @Param data
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/

    @ApiOperation(value = "车辆公司审核状态", httpMethod = "PUT")
    @PutMapping("/changeReview")
    @ControllerEndpoint(operation = "修改车辆公司审核状态", exceptionMessage = "修改车辆公司审核状态失败")
    public Resp changeReview(@RequestBody Map<String,Integer> data){
        long startTime =  System.currentTimeMillis();
        try {
            //更新
            Integer temp = data.get("CAR_COMPANY_Id");
            if(carCompanyService.getById(data.get("CAR_COMPANY_Id").intValue()) == null){
                long endTime = System.currentTimeMillis();
                LOGGER.error("修改失败，原因：修改不存在，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationError("修改失败，原因：修改的id不存在，", null, null);
            }
            carCompanyService.changeReview(data.get("CAR_COMPANY_Id"), data.get("REVIEW_STATUS"));
            long endTime =  System.currentTimeMillis();
            LOGGER.info("修改成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("修改成功", null, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("修改失败，原因："+ e.getMessage() +"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("修改失败：" + e.getMessage(), null, null);
        }
    }



    /** @Author: wzj
     * @Description: 
     * @Date 2020/11/12 11:35
     * @Param ids
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    
    @ApiOperation(value = "根据车辆公司id批量删除",httpMethod = "DELETE")
    @DeleteMapping("/{carCompanyIds}")
    @ControllerEndpoint(operation = "根据id批量删除", exceptionMessage = "根据id批量删除失败")
    public Resp batchDelete(@RequestParam("ids") List<String> ids){
        long startTime = System.currentTimeMillis();
        try {
            carCompanyService.batchDelete(ids);
            long endTime = System.currentTimeMillis();
            LOGGER.info("批量删除成功,用时:" + (endTime-startTime) + "ms");
            return Resp.getInstantiationSuccess("批量删除成功", Resp.LIST, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("批量删除失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("批量删除失败:"+e.getMessage(), Resp.LIST, null);
        }
    }

    @ApiOperation(value = "根据car_company_name判断插入或者更新数据，并返回car_company_id", httpMethod = "PUT")
    @PutMapping("/updateOrInsertCarCompany")
    @ControllerEndpoint(operation = "更新或插入成功！", exceptionMessage = "操作失败")
    public Resp updateOrInsertCarCompany(@RequestBody  CarCompany carCompany) {
        long startTime = System.currentTimeMillis();
        try {
            // 业务逻辑
            String id = carCompanyService.updateOrInsertCarCompany(carCompany);
            if(id == null){
                throw new Exception("carCompanyName为空！Error！");
            }
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据car_company_name判断插入或者更新数据，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("updateOrInsertCarCompany成功", Resp.STRING, id);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据car_company_name判断插入或者更新数据失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("updateOrInsertCarCompany失败："+ e.getMessage(),Resp.LIST,null );
        }
//
    }
}
