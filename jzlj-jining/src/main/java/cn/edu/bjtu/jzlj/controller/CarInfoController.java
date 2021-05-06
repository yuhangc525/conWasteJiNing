package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.CarCompany;
import cn.edu.bjtu.jzlj.dao.CarInfo;
import cn.edu.bjtu.jzlj.service.CarInfoService;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.UuidTool;
import cn.edu.bjtu.jzlj.util.results.Resp;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: jzlj
 * @description:
 * @author: wzj
 * @create: 2020-12-13 18:20
 **/


@Api(description = "车辆信息接口")
@RestController
@RequestMapping("/carInfo")
public class CarInfoController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CarTrailController.class);

    @Autowired
    CarInfoService carInfoService;

    /**
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @Author: wzj
     * @Description:
     * @Date 2020/12/13 19:20
     * @Param queryRequest
     * @throws:
     **/

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

    /**
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @Author: wzj
     * @Description:
     * @Date 2020/12/13 22:10
     * @Param
     * @throws:
     **/

    @ApiOperation(value = "车辆信息数目查询", httpMethod = "GET")
    @GetMapping("/getNumAll")
    @ControllerEndpoint(operation = "车辆信息总数查询", exceptionMessage = "车辆信息总数查询失败")
    public Resp getNumALl() {

        //查询列表数据
        long startTime = System.currentTimeMillis();
        try {
            long endTime = System.currentTimeMillis();
            LOGGER.info("查询车辆信息数据条数成功，用时" + (endTime - startTime) + "ms");

            return Resp.getInstantiationSuccess("车辆公司查询成功", Resp.SINGLE, carInfoService.getNum(null));
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("查询车辆信息数据条数失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("车辆公司查询失败" + e.getMessage(), Resp.LIST, null);
        }
    }


    @ApiOperation(value = "车辆信息数目查询（限制InputName）", httpMethod = "GET")
    @GetMapping("/getNumByInputName")
    @ControllerEndpoint(operation = "车辆信息总数查询", exceptionMessage = "车辆信息总数查询失败")
    public Resp getNumByInputName(String inputName) {

        //查询列表数据
        long startTime = System.currentTimeMillis();
        try {
            long endTime = System.currentTimeMillis();
            LOGGER.info("查询车辆信息数据条数成功，用时" + (endTime - startTime) + "ms");

            return Resp.getInstantiationSuccess("车辆公司查询成功", Resp.SINGLE, carInfoService.getNum(inputName));
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("查询车辆信息数据条数失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("车辆公司查询失败" + e.getMessage(), Resp.LIST, null);
        }
    }


    /**
     * @param inputName
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @Author: wzj
     * @Description:
     * @Date 2020/12/13 19:22
     * @Param queryRequest
     * @throws:
     **/

    @ApiOperation(value = "车辆信息根据输入人姓名查询", httpMethod = "GET")
    @GetMapping("/selectByInputName")
    @ControllerEndpoint(operation = "车辆信息根据输入人姓名查询", exceptionMessage = "车辆信息根据输入人姓名查询失败")
    public Resp selectByInputName(QueryRequest queryRequest, String inputName) {


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
                IPage<CarInfo> carInfoList = carInfoService.getListByPage(queryRequest, inputName, null);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, carInfoList);
            } else {
                List<CarInfo> carInfoList = carInfoService.getAllList(queryRequest, inputName, null);
                long endTime = System.currentTimeMillis();
                LOGGER.info("车辆信息查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("车辆信息查询查看", Resp.LIST, carInfoList);
            }
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("分页列举失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }
    }

    @ApiOperation(value = "车辆信息根据公司查询", httpMethod = "GET")
    @GetMapping("/selectByCompany")
    @ControllerEndpoint(operation = "车辆信息根据公司查询", exceptionMessage = "车辆信息根据公司查询失败")
    public Resp selectByCompany(QueryRequest queryRequest, String company) {


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
                IPage<CarInfo> carInfoList = carInfoService.getListByPage(queryRequest, null, company);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, carInfoList);
            } else {
                List<CarInfo> carInfoList = carInfoService.getAllList(queryRequest, null, company);
                long endTime = System.currentTimeMillis();
                LOGGER.info("车辆信息查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("车辆信息查询查看", Resp.LIST, carInfoList);
            }
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("分页列举失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }
    }

    /**
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @Author: wzj
     * @Description:
     * @Date 2020/12/13 19:23
     * @Param carInfo
     * @throws:
     **/
    @ApiOperation(value = "新增车辆信息", httpMethod = "POST")
    @PostMapping("/createCarInfo")
    @ControllerEndpoint(operation = "新增", exceptionMessage = "新增失败")
    public Resp save(@RequestBody CarInfo carInfo) {
        long startTime = System.currentTimeMillis();
        if (null == carInfo) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
            carInfo.setCarId(UuidTool.getUUID());
            carInfoService.saveData(carInfo);
            long endTime = System.currentTimeMillis();
            LOGGER.info("创建成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("创建失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("创建异常，原因：" + e.getMessage(), Resp.SINGLE, carInfo);
        }
    }


    /**
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @Author: wzj
     * @Description:
     * @Date 2020/12/13 19:25
     * @Param carInfo
     * @throws:
     **/

    @ApiOperation(value = "车辆信息更改", httpMethod = "PUT")
    @PutMapping("/putCarInfo")
    @ControllerEndpoint(operation = "编辑", exceptionMessage = "编辑失败")
    public Resp update(@RequestBody CarInfo carInfo) {
        long startTime = System.currentTimeMillis();
        try {
            //更新
            if (carInfoService.getById(carInfo.getCarId()) == null) {
                long endTime = System.currentTimeMillis();
                LOGGER.error("修改失败，原因：修改不存在，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationError("修改失败，原因：修改的id不存在，", null, null);
            }
            carInfoService.updateData(carInfo);
            long endTime = System.currentTimeMillis();
            LOGGER.info("修改成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("修改成功", null, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("修改失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("修改失败：" + e.getMessage(), null, null);
        }
    }

    /**
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @Author: wzj
     * @Description:
     * @Date 2020/12/13 19:26
     * @Param data
     * @throws:
     **/

    @ApiOperation(value = "车辆信息审核状态", httpMethod = "PUT")
    @PutMapping("/changeReview")
    @ControllerEndpoint(operation = "修改车辆信息审核状态", exceptionMessage = "修改车辆信息审核状态失败")
    public Resp changeReview(@RequestParam("carId") String carId, @RequestParam("reviewStatus")Integer reviewStatus) {
        long startTime = System.currentTimeMillis();
        try {
            //更新
            if (carInfoService.getById(carId) == null) {
                long endTime = System.currentTimeMillis();
                LOGGER.error("修改失败，原因：修改不存在，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationError("修改失败，原因：修改的id不存在，", null, null);
            }
            carInfoService.changeReview(carId, reviewStatus);
            long endTime = System.currentTimeMillis();
            LOGGER.info("修改成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("修改成功", null, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("修改失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("修改失败：" + e.getMessage(), null, null);
        }
    }

    /**
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @Author: wzj
     * @Description:
     * @Date 2020/12/13 19:37
     * @Param ids
     * @throws:
     **/

    @ApiOperation(value = "根据车辆信息id批量删除", httpMethod = "DELETE")
    @DeleteMapping("/{carInfoIds}")
    @ControllerEndpoint(operation = "根据id批量删除", exceptionMessage = "根据id批量删除失败")
    public Resp batchDelete(@RequestParam("ids") List<String> ids) {
        long startTime = System.currentTimeMillis();
        try {
            carInfoService.batchDelete(ids);
            long endTime = System.currentTimeMillis();
            LOGGER.info("批量删除成功,用时:" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("批量删除成功", Resp.LIST, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("批量删除失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("批量删除失败:" + e.getMessage(), Resp.LIST, null);
        }
    }


    @ApiOperation(value = "车辆car_no根据terminal_id查询", httpMethod = "GET")
    @GetMapping("/selectCarNoByTid")
    @ControllerEndpoint(operation = "车辆car_no根据terminal_id查询", exceptionMessage = "车辆car_no根据terminal_id查询失败")
    public Resp selectCarNoByTid(QueryRequest queryRequest, String terminalId) {

        //查询列表数据
        long startTime = System.currentTimeMillis();
        if (queryRequest.getPageNo() < 1 || queryRequest.getPageNo() < 1) {
            return Resp.getInstantiationError("分页查看失败，分页页数或分页大小不合法", null, null);
        }
        try {
            // 分页查询
            if (queryRequest.isPageFlag()) {
                if (queryRequest.getPageNo() < 1 || queryRequest.getPageNo() < 1) {
                    return Resp.getInstantiationError("分页查看失败，分页页数或分页大小不合法", null, null);
                }
                IPage<CarInfo> carCompanyList = carInfoService.getListByPage(queryRequest, terminalId);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, carCompanyList);
            } else {
                List<CarInfo> carCompanyList = carInfoService.getAllList(queryRequest, terminalId);
                long endTime = System.currentTimeMillis();
                LOGGER.info("车辆公司查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("车辆公司查询查看", Resp.LIST, carCompanyList);
            }
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("分页列举失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }
    }


}

