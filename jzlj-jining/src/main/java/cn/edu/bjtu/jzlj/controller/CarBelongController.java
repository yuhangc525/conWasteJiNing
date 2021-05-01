package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.CarBelong;
import cn.edu.bjtu.jzlj.service.CarBelongService;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.UuidTool;
import cn.edu.bjtu.jzlj.util.results.Resp;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** @Author: wzj
 * @Description:
 * @Date 2020/11/9 10:18
 **/

@Api(description = "车辆归属信息接口")
@RestController
@RequestMapping("/carBelong")
public class CarBelongController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private CarBelongService carBelongService;

    /** @Author: wzj
     * @Description: 列表查询
     * @Date 2020/11/9 10:25
     * @Param queryRequest
     * @param carBelong
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "车辆归属查询", httpMethod = "GET")
    @GetMapping("/listCarBelongByPage")
    @ControllerEndpoint(operation = "车辆归属列表查询", exceptionMessage = "车辆归属列表查询失败")
    public Resp getListByPage(QueryRequest queryRequest, CarBelong carBelong){


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
                IPage<CarBelong> carBelongList = carBelongService.getListByPage(queryRequest, carBelong);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, carBelongList);
            }
            else
            {
                List<CarBelong> carBelongList = carBelongService.getAllList(queryRequest,carBelong);
                long endTime = System.currentTimeMillis();
                LOGGER.info("车辆归属查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("车辆归属查询查看", Resp.LIST, carBelongList);
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
     * @Description: 新增
     * @Date 2020/11/10 9:06
     * @Param carBelong
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "新增车辆归属", httpMethod = "POST")
    @PostMapping("/createCarBelong")
    @ControllerEndpoint(operation = "新增", exceptionMessage = "新增失败")
    public Resp save(@RequestBody CarBelong carBelong){
        long startTime = System.currentTimeMillis();
        if (null == carBelong) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
            carBelong.setCarBelongId(UuidTool.getUUID());
            carBelongService.saveData(carBelong);
            long endTime = System.currentTimeMillis();
            LOGGER.info("创建成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("创建失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("创建异常，原因：" + e.getMessage(), Resp.SINGLE, carBelong);
        }
    }

    /** @Author: wzj
     * @Description: 编辑
     * @Date 2020/11/10 9:18
     * @Param carBelong
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    
    @ApiOperation(value = "车辆归属更改", httpMethod = "PUT")
    @PutMapping("/putCarBelong")
    @ControllerEndpoint(operation = "编辑", exceptionMessage = "编辑失败")
    public Resp update(@RequestBody  CarBelong carBelong){
        long startTime =  System.currentTimeMillis();
        try {
            //更新
            if(carBelongService.getById(carBelong.getCarBelongId()) == null){
                long endTime = System.currentTimeMillis();
                LOGGER.error("修改失败，原因：修改不存在，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationError("修改失败，原因：修改的id不存在，", null, null);
            }
            carBelongService.updateData(carBelong);
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
     * @Date 2020/11/10 9:20
     * @Param ids
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    
    @ApiOperation(value = "根据车辆id批量删除",httpMethod = "DELETE")
    @DeleteMapping("/{carBelongIds}")
    @ControllerEndpoint(operation = "根据id批量删除", exceptionMessage = "根据id批量删除失败")
    public Resp batchDelete(@RequestParam("ids") List<String> ids){
        long startTime = System.currentTimeMillis();
        try {
            carBelongService.batchDelete(ids);
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
