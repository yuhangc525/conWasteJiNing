package cn.edu.bjtu.jzlj.controller;


import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;

import cn.edu.bjtu.jzlj.dao.CarCompany;
import cn.edu.bjtu.jzlj.dao.PageSource;

import cn.edu.bjtu.jzlj.dao.SysOrganization;
import cn.edu.bjtu.jzlj.dao.SysRolePageSource;
import cn.edu.bjtu.jzlj.mapper.PageSourceMapper;
import cn.edu.bjtu.jzlj.mapper.SysRolePageSourceMapper;
import cn.edu.bjtu.jzlj.service.SysRolePageSourceService;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.UuidTool;
import cn.edu.bjtu.jzlj.util.results.Resp;
import org.springframework.util.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;;

import cn.edu.bjtu.jzlj.service.PageSourceService;

import java.util.List;

/**
 * @program: user-service
 * @description:
 * @author 田英杰
 * @date ${2020-11-20}
 */
@Api(description = "资源页面信息接口")
@RestController
@RequestMapping("/pageSource")
public class PageSourceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageSourceController.class);

    @Autowired
    private PageSourceService pageSourceService;
    @Autowired
    private SysRolePageSourceMapper sysRolePageSourceMapper;
//    @Autowired
//    private SysRolePageSourceService sysRolePageSourceService;


     /**
      * @Author: 田英杰
      * @Description: 新增页面权限信息
      * @Date 2020/11/20 12:45
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "新增页面权限信息", httpMethod = "POST")
    @PostMapping("/addPageSource")
    @ControllerEndpoint(operation = "新增页面权限信息", exceptionMessage = "新增失败")
    public Resp addPageSource(@RequestBody PageSource pageSource){
        long startTime = System.currentTimeMillis();
        if (null == pageSource) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
            PageSource currentPageSource = pageSourceService.getById(pageSource.getId());
            if(currentPageSource != null){
                LOGGER.error("新增资源主键(id)重复，用时：" + (System.currentTimeMillis() - startTime) + "ms");
                return Resp.getInstantiationError("新增页面权限信息异常", Resp.STRING, null);
            }
            pageSource.setId(UuidTool.getUUID());
            System.out.println("====" + pageSource.getId());
            pageSourceService.save(pageSource);
            long endTime = System.currentTimeMillis();
            LOGGER.info("新增页面权限信息成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("新增页面权限信息成功", Resp.SINGLE, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("新增页面权限信息失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("新增页面权限信息异常，原因：" + e.getMessage(), Resp.SINGLE, pageSource);
        }
    }


    @ApiOperation(value = "编辑页面资源", httpMethod = "PUT")
    @PutMapping("/updatepageSource")
//    @RequiresPermissions(value = ShiroPsPageSourceManager.pagesourceManager_edit)
    @ControllerEndpoint(operation = "修改信息成功", exceptionMessage = "修改失败")
    public Resp updatepageSource(PageSource pageSource) {
        long startTime = System.currentTimeMillis();
        try {
            pageSourceService.updatepageSource(pageSource);
            long endTime = System.currentTimeMillis();
            LOGGER.info("页面编辑成功，用时:" + (endTime - startTime));
            return Resp.getInstantiationSuccess("页面编辑成功",Resp.SINGLE,null);
        } catch (Exception e) {
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            LOGGER.error("页面编辑失败，原因：" + e.getMessage() +",用时:" + (endTime - startTime));
            return Resp.getInstantiationSuccess("页面编辑失败",Resp.SINGLE,null);
        }
    }


    @ApiOperation(value = "根据页面Id删除页面", httpMethod = "DELETE")
    @DeleteMapping("/deletepageSource/{id}")
    public Resp deletepageSource(@PathVariable String id) {
        long startTime = System.currentTimeMillis();
        try {
            pageSourceService.deletePageSourceById(id);
            long endTime = System.currentTimeMillis();
            LOGGER.info("页面删除成功，用时:" + (endTime - startTime));
            return Resp.getInstantiationSuccess("页面删除成功",Resp.SINGLE,null);
        } catch (Exception e) {
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            LOGGER.error("页面删除失败，原因：" + e.getMessage() +",用时:" + (endTime - startTime));
            return Resp.getInstantiationError("页面删除失败",Resp.SINGLE,null);
        }
    }


    @ApiOperation(value = "根据角色id查询拥有的页面资源（sys_pagesource)分页接口", httpMethod = "GET")
    @GetMapping("/getPagesourceListByRoleId")
    @ResponseBody
    public Resp getPageSourceListByRoleId(String roleid,@RequestParam(defaultValue = "1") Integer pageNo,
                                          @RequestParam(defaultValue = "10")Integer pageSize) {
        long startTime = System.currentTimeMillis();
        try {
            IPage<PageSource> pageSourceIPage = pageSourceService.getPageSourceListByRoleId(roleid,pageNo,pageSize);
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据角色id查询拥有的页面资源（sys_pagesource)分页接口成功，用时:" + (endTime - startTime));
            return Resp.getInstantiationSuccess("根据角色id查询拥有的页面资源（sys_pagesource)分页接口成功",Resp.LIST,pageSourceIPage);
        } catch (Exception e) {
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据角色id查询拥有的页面资源（sys_pagesource)分页接口失败，原因：" + e.getMessage() +",用时:" + (endTime - startTime));
            return Resp.getInstantiationSuccess("根据角色id查询拥有的页面资源（sys_pagesource)分页接口失败",Resp.SINGLE,null);
        }
    }



    @ApiOperation(value = "根据角色id查询未拥有的页面资源（sys_pagesource)分页接口", httpMethod = "GET")
    @GetMapping("/getNoPagesourceListByRoleId")
    @ResponseBody
    public Resp getNoPagesourceListByRoleId(String roleid,@RequestParam(defaultValue = "1") Integer pageNo,
                                            @RequestParam(defaultValue = "10")Integer pageSize) {
        long startTime = System.currentTimeMillis();
        try {
            IPage<PageSource> pageSourceIPage = pageSourceService.getNoPageSourceListByRoleId(roleid,pageNo,pageSize);
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据角色id查询未拥有的页面资源（sys_pagesource)分页接口成功，用时:" + (endTime - startTime));
            return Resp.getInstantiationSuccess("根据角色id查询未拥有的页面资源（sys_pagesource)分页接口成功",Resp.LIST,pageSourceIPage);
        } catch (Exception e) {
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据角色id查询未拥有的页面资源（sys_pagesource)分页接口失败，原因：" + e.getMessage() +",用时:" + (endTime - startTime));
            return Resp.getInstantiationError("根据角色id查询未拥有的页面资源（sys_pagesource)分页接口失败",Resp.SINGLE,null);
        }
    }


    @ApiOperation(value = "根据多个id删除多个页面权限", httpMethod = "GET")
    @GetMapping("/deleteMpagesource")
    @ResponseBody
    public Resp deleteMpagesource(@RequestParam(value = "pagesourceid") List<String> id) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(id)) {
            LOGGER.error("前端错误,请正确传递删除页面信息" + ",用时：" + (System.currentTimeMillis() - startTime));
            return Resp.getInstantiationSuccess("前端错误,请正确传递删除页面信息",Resp.STRING,null);
        }
        try {
            pageSourceService.deleteMpagesource(id);
            long endTime =System.currentTimeMillis();
            LOGGER.info("根据多个id删除多个页面权限成功，用时:" + (endTime - startTime));
            return Resp.getInstantiationSuccess("根据多个id删除多个页面权限成功",Resp.STRING,null);
        } catch (Exception e) {
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            LOGGER.error("根据多个id删除多个页面权限失败，原因：" + e.getMessage() +",用时:" + (endTime - startTime));
            return Resp.getInstantiationError("根据多个id删除多个页面权限失败",Resp.STRING,null);
        }
    }

    @ApiOperation(value = "权限查询", httpMethod = "GET")
    @GetMapping("/listPageSourceByPage")
    @ControllerEndpoint(operation = "权限查询", exceptionMessage = "权限列表查询失败")
    public Resp getListByPage(QueryRequest queryRequest, PageSource pageSource){


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

                IPage<PageSource> pageSourceList = pageSourceService.getListByPage(queryRequest, pageSource);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, pageSourceList);
            }
            else
            {
                List<PageSource> pageSourceList = pageSourceService.getAllList(queryRequest, pageSource);
                long endTime = System.currentTimeMillis();
                LOGGER.info("车辆公司查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("车辆公司查询查看", Resp.LIST, pageSourceList);
            }
        }
        catch (Exception e)
        {
            long endTime = System.currentTimeMillis();
            LOGGER.error("分页列举失败，原因："+ e.getMessage()+"，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("分页查看失败" + e.getMessage(), Resp.LIST, null);
        }

    }

    @ApiOperation(value = "Psource插入数据", httpMethod = "POST")
    @PostMapping("/insertPsource")
    @ControllerEndpoint(operation = "新增", exceptionMessage = "新增失败")
    public Resp insertPsource(@RequestBody PageSource pageSource){
        long startTime = System.currentTimeMillis();
        if (null == pageSource) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
            sysRolePageSourceMapper.insertPsource(new SysRolePageSource(pageSource.getRoleId(), pageSource.getId()));
//            sysRolePageSourceService.insertPsource(new SysRolePageSource(pageSource.getRoleId(), pageSource.getId()));
//            sysRolePageSourceMapper.insert(new SysRolePageSource(pageSource.getRoleId(), pageSource.getId()));
            long endTime = System.currentTimeMillis();
            LOGGER.info("创建成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("创建失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("创建异常，原因：" + e.getMessage(), Resp.SINGLE, pageSource);
        }
    }


    @ApiOperation(value = "根据页面Id删除页面", httpMethod = "DELETE")
    @DeleteMapping("/deleteRolePageSource")
    public Resp deleteRolePageSource(@RequestBody PageSource pageSource) {
        long startTime = System.currentTimeMillis();
        try {
            sysRolePageSourceMapper.deleteByPageSource(new SysRolePageSource(pageSource.getRoleId(), pageSource.getId()));
            long endTime = System.currentTimeMillis();
            LOGGER.info("删除成功，用时:" + (endTime - startTime));
            return Resp.getInstantiationSuccess("删除成功",Resp.SINGLE,null);
        } catch (Exception e) {
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            LOGGER.error("删除失败，原因：" + e.getMessage() +",用时:" + (endTime - startTime));
            return Resp.getInstantiationError("删除失败",Resp.SINGLE,null);
        }
    }

    @ApiOperation(value = "根据id修改信息", httpMethod = "PUT")
    @PutMapping("/updateSP")
    @ControllerEndpoint(operation = "修改信息", exceptionMessage = "修改失败")
    public Resp updateOrgById(@RequestBody PageSource pageSource) {
        long startTime = System.currentTimeMillis();
        try {
            pageSourceService.updateSP(pageSource);
//            SysOrganization sysOrganization1 = sysOrganizationService.updateOrgById(sysOrganization);
            LOGGER.info("修改组织成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("修改组织成功", Resp.STRING, pageSource);
        } catch (Exception e) {
            LOGGER.error("修改组织失败，原因：", e.getMessage()+ "，用时" +(System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("修改失败："+ e.getMessage(),Resp.LIST,null );
        }
    }

}
