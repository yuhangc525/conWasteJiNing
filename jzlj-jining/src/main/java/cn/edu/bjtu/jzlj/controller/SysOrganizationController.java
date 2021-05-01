package cn.edu.bjtu.jzlj.controller;


import java.util.Date;

import java.util.List;



import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.config.shiro.ShiroUtils;

import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.dao.SysOrganization;
import cn.edu.bjtu.jzlj.service.SysOrganizationService;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.results.Resp;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;;


/**
 * @author 田英杰
 * @description
 * @date 2020-11-16
 */
@Api(description = "组织信息接口")
@RestController
@RequestMapping("/sysOrganization")
public class SysOrganizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysOrganizationController.class);

    @Autowired
    private SysOrganizationService sysOrganizationService;


    @ApiOperation(value = "组织分页查询", httpMethod = "GET")
    @GetMapping("/getOrgListByPage")
    @ControllerEndpoint(operation = "组织分页查询", exceptionMessage = "组织分页查询失败")
    public Resp getOrgListByPage(QueryRequest queryRequest, SysOrganization sysOrganization){
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
                IPage<SysOrganization> SysOrganizationList = sysOrganizationService.getOrgListByPage(queryRequest, sysOrganization);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, SysOrganizationList);
            }
            else
            {
                List<SysOrganization> sysOrganizationList = sysOrganizationService.getAllList(queryRequest,sysOrganization);
                long endTime = System.currentTimeMillis();
                LOGGER.info("组织列表查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("组织列表查询查看", Resp.LIST, sysOrganizationList);
            }
        }
        catch (Exception e)
        {
            long endTime = System.currentTimeMillis();
            LOGGER.error("分页列举失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }
    }




     /**
      * @Author: 田英杰
      * @Description: 新建组织
      * @Date 2020/11/16 17:02
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "新增组织", httpMethod = "POST")
    @PostMapping("/createOrganization")
    @ControllerEndpoint(operation = "新增成功", exceptionMessage = "新增失败")
    public Resp createOrganization (@RequestBody SysOrganization sysOrganization) {
        long startTime = System.currentTimeMillis();
        if (null == sysOrganization) {
            return Resp.getInstantiationError("前端返回参数错误", Resp.STRING, null);
        }
        try {
            sysOrganizationService.createOrganization(sysOrganization);
            long endTime = System.currentTimeMillis();
            LOGGER.info("创建组织成功，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("创建组织成功", Resp.STRING, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("创建组织失败，原因：" + e.getMessage() + "， 用时：" +(endTime = startTime) + "ms");
            return Resp.getInstantiationError("创建异常", Resp.STRING, sysOrganization);
        }

    }



     /**
      * @Author: 田英杰
      * @Description: 根据id查询组织信息
      * @Date 2020/11/16 17:19
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "根据id查询组织信息", httpMethod = "GET")
    @GetMapping("/getOrganizationById")
    @ControllerEndpoint(operation = "根据id查询组织信息", exceptionMessage = "查询失败")
    public Resp getOrganizationById(String id) {
        long startTime = System.currentTimeMillis();
        try {
            SysOrganization sysOrganization = sysOrganizationService.getOrganizationById(id);
            LOGGER.info("查询组织成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("查询组织成功", Resp.STRING, sysOrganization);
        } catch (Exception e) {
            LOGGER.error("查询组织失败，原因：", e.getMessage()+ "，用时" +(System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("查询用户失败："+ e.getMessage(),Resp.LIST,null );
        }
    }


     /**
      * @Author: 田英杰
      * @Description: 根据id修改组织信息
      * @Date 2020/11/16 18:46
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "根据id修改组织信息", httpMethod = "PUT")
    @PutMapping("/updateOrgById")
    @ControllerEndpoint(operation = "修改组织信息", exceptionMessage = "修改失败")
    public Resp updateOrgById(SysOrganization sysOrganization) {
        long startTime = System.currentTimeMillis();
        try {
            if (sysOrganizationService.getOrganizationById(sysOrganization.getId()) == null) {
                LOGGER.error("修改失败，原因：修改不存在，用时" + (System.currentTimeMillis() - startTime) + "ms");
                return Resp.getInstantiationError("要修改的id不存在", Resp.STRING, null);
            }
            sysOrganizationService.updateOrgById(sysOrganization);
//            SysOrganization sysOrganization1 = sysOrganizationService.updateOrgById(sysOrganization);
            LOGGER.info("修改组织成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("修改组织成功", Resp.STRING, sysOrganization);
        } catch (Exception e) {
            LOGGER.error("修改组织失败，原因：", e.getMessage()+ "，用时" +(System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("修改失败："+ e.getMessage(),Resp.LIST,null );
        }
    }


     /**
      * @Author: 田英杰
      * @Description: 根据组织ID删除组织-逻辑删除
      * @Date 2020/11/17 16:08
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "根据用户名删除组织-逻辑删除", httpMethod = "DELETE")
    @DeleteMapping("/deleteOrgById/{id}")
    @ControllerEndpoint(operation = "删除组织信息", exceptionMessage = "删除失败")
    public Resp deleteOrgById (@RequestParam String id) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(id)) {
            LOGGER.error("组织信息不能为空！用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("组织信息不能为空", Resp.STRING, null);
        }
        try {
            if (sysOrganizationService.getOrganizationById(id).getStatus() == 0){
                LOGGER.error("组织已被删除，用时："+ (System.currentTimeMillis() - startTime) + "ms");
                return Resp.getInstantiationError("组织已被删除", Resp.STRING, null);
            }
            sysOrganizationService.deleteOrgById(id, getUpdateUserInfo(), new Date());
            LOGGER.info("根据id删除组织成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("根据id删除组织成功", Resp.STRING, null);
        }catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("根据id删除组织失败，异常：" + e.getMessage() + "，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("根据id删除组织失败", Resp.STRING, null);
        }

    }

    /**
     * @Author: sjy
     * @Description: 根据组织ID删除组织-get方式
     * @Date 2021/1/25 16:08
     * @Param  * @param null
     * @return
     * @throws:
     **/
    @ApiOperation(value = "根据用户名删除组织-get方式", httpMethod = "GET")
    @GetMapping("/deleteOrgById_get/{id}")
    @ControllerEndpoint(operation = "删除组织信息", exceptionMessage = "删除失败")
    public Resp deleteOrgById_get (@PathVariable String id) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(id)) {
            LOGGER.error("组织信息不能为空！用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("组织信息不能为空", Resp.STRING, null);
        }
        try {
            if (sysOrganizationService.getOrganizationById(id).getStatus() == 0){
                LOGGER.error("组织已被删除，用时："+ (System.currentTimeMillis() - startTime) + "ms");
                return Resp.getInstantiationError("组织已被删除", Resp.STRING, null);
            }
            sysOrganizationService.deleteOrgById_get(id);
            LOGGER.info("根据id删除组织成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("根据id删除组织成功", Resp.STRING, null);
        }catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("根据id删除组织失败，异常：" + e.getMessage() + "，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("根据id删除组织失败", Resp.STRING, null);
        }

    }



    @ApiOperation(value = "根据多个id删除多个组织（批量删除）-逻辑删除", httpMethod = "POST")
    @PostMapping("/deleteMOrgById")
    @ControllerEndpoint(operation = "删除多个组织信息", exceptionMessage = "删除失败")
    public Resp deleteMOrgById(@RequestParam List<String> id) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(id)) {
            LOGGER.error("组织信息不能为空！用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("组织信息不能为空", Resp.STRING, null);
        }
        try {
            sysOrganizationService.deleteMOrgById(id, getUpdateUserInfo(), new Date());
            LOGGER.info("根据多个id删除多个组织成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("根据多个id删除多个组织成功", Resp.STRING, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("根据多个id删除多个组织失败，异常：" + e.getMessage() + "，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("根据多个id删除多个组织失败", Resp.STRING, null);
        }
    }



    @ApiOperation(value = "根据多个id删除多个组织（批量删除）-get方式", httpMethod = "GET")
    @GetMapping("/deleteMOrgById_get/{id}")
    @ControllerEndpoint(operation = "根据ID删除多个组织信息", exceptionMessage = "删除失败")
    public Resp deleteMOrgById_get(@PathVariable List<String> id) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(id)) {
            LOGGER.error("组织信息不能为空！用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("组织信息不能为空", Resp.STRING, null);
        }
        try {
            sysOrganizationService.deleteMOrgById_get(id);
            LOGGER.info("根据多个id删除多个组织成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("根据多个id删除多个组织成功", Resp.STRING, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("根据多个id删除多个组织失败，异常：" + e.getMessage() + "，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("根据多个id删除多个组织失败", Resp.STRING, null);
        }
    }











    /**
     * @Author: 田英杰
     * @Description: 获取修改者用户信息
     * @Date 2020/11/17 16:23
     * @Param  * @param null
     * @return
     * @throws:
     **/
    private String getUpdateUserInfo() {
        SysUser sysUser = ShiroUtils.getCurrentUser();
//        String updateUser = sysUser.getId();
        String updateUser = "tianyingjie";
        return updateUser;
    }
}
