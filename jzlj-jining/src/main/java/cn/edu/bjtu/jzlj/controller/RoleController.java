package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.dao.Role;

import cn.edu.bjtu.jzlj.mapper.SysOrganizationUserMapper;
import cn.edu.bjtu.jzlj.mapper.SysUserRoleMapper;
import cn.edu.bjtu.jzlj.service.PageSourceService;
import cn.edu.bjtu.jzlj.service.RoleService;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.results.Resp;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

/**
 * @ClassName: RoleController
 * @Description:
 * @Author sjyzj
 * @Date 2020/11/13 10:14
 */
@Api(description = "用户角色接口")
@RestController
@RequestMapping("/user_role")
public class RoleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysOrganizationUserMapper sysOrganizationUserMapper;
    @Autowired
    private PageSourceService pageSourceService;

//    /**
//     * @Author: sjyzj
//     * @Description: 获取角色列表
//     * @Date 2020/12/7 16:47
//     * @Param roleName
//     * @param pageNo
//     * @param pageSize
//     * @return cn.edu.bjtu.jzlj.util.results.Resp
//     * @throws:
//     **/
//    @ApiOperation(value = "获取角色列表", httpMethod = "GET")
//    @GetMapping("/list")
//    public Resp getRoleList(String roleName, @RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize) {
//        long time = System.currentTimeMillis();
//        try {
//            RoleSearchVo vo = new RoleSearchVo();
//            vo.setRoleName(roleName);
//            IPage<Role> roles = roleService.getRoleList(pageNo, pageSize, vo);
//            LOGGER.info("获取用户列表成功，用时：" + (System.currentTimeMillis() - time));
//            return Resp.getInstantiationSuccess("获取用户列表成功", Resp.LIST, roles);
//        } catch (Exception e) {
//            LOGGER.error("获取用户列表失败，异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - time));
//            e.printStackTrace();
//
//            return Resp.getInstantiationError("获取用户列表失败", Resp.SINGLE, null);
//        }
//    }

    /**
     * @method  getListByPage
     * @description 列表查询
     * @date: ${2020-11-08}
     * @author: ld
     * @param queryRequest
     * @param role
     * @return Resp
     */
    @ApiOperation(value = "用户列表查询", httpMethod = "GET")
    @GetMapping("/listRoleByPage")
    @ControllerEndpoint(operation = "角色列表查询", exceptionMessage = "角色列表查询失败")
    public Resp getListByPage(QueryRequest queryRequest, Role role){
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
                IPage<Role> sysRoleList = roleService.getListByPage(queryRequest, role);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, sysRoleList);
            }
            else
            {
                List<Role> sysRoleList = roleService.getAllList(queryRequest,role);
                long endTime = System.currentTimeMillis();
                LOGGER.info("用户列表查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("用户列表查询查看", Resp.LIST, sysRoleList);
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
     * @Author: sjyzj
     * @Description: 用户角色设置
     * @Date 2020/12/7 16:47
     * @Param roleId
     * @param userIds
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "用户角色设置", httpMethod = "GET")
    @GetMapping("/updateUserRoleByRoleId")
    public Resp updateUserRoleByRoleId(String roleId, @RequestParam(value = "userid") String userIds) {
        long time = System.currentTimeMillis();
        if (null == roleId || roleId == "") {
            LOGGER.error("请输入角色id，用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("请输入角色id", Resp.STRING, null);
        }
        try {
            roleService.updateUserRoleByRoleId(roleId, userIds);
            LOGGER.info("用户角色设置成功，用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationSuccess("用户角色设置成功", Resp.STRING, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("用户角色设置失败，异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("用户角色设置失败" + e.getMessage(), Resp.STRING, null);
        }
    }


    /**
     * @Author: sjyzj
     * @Description: 通过id获取角色
     * @Date 2020/12/7 16:48
     * @Param roleid
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "通过id获取角色", httpMethod = "GET")
    @GetMapping("/getUserRoleByRoleId")
    public Resp getUserRoleByRoleId(String roleid) {
        long time = System.currentTimeMillis();
        if (null == roleid  || roleid == "") {
            LOGGER.error("请输入账号id，用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("请输入账号id", Resp.STRING, null);
        }
        try {
            Role role = roleService.findUserRoleByRoleId(roleid);
            if (role == null) {
                LOGGER.error("角色不存在，用时：" + (System.currentTimeMillis() - time));
                return Resp.getInstantiationError("用户不存在", Resp.STRING, null);
            }
            LOGGER.info("根据角色id查询用户成功，用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationSuccess("根据用户id查询用户成功", Resp.STRING, role);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("根据用户id查询用户失败，异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("根据用户id查询用户失败" + e.getMessage(), Resp.STRING, null);
        }
    }

    /**
     * @Author: sjyzj
     * @Description: 新增角色
     * @Date 2020/12/7 16:48
     * @Param role
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "新增角色", httpMethod = "POST")
    @PostMapping("/createRole")
    @ControllerEndpoint(operation = "新增", exceptionMessage = "新增失败")
    public Resp save(@RequestBody Role role){
        long startTime = System.currentTimeMillis();
        if (null == role) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
//            roleService.saveData(role);
            roleService.save(role);
            long endTime = System.currentTimeMillis();
            LOGGER.info("创建成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("创建失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("创建异常，原因：" + e.getMessage(), Resp.SINGLE, role);
        }
    }


    /**
     * @Author: sjyzj
     * @Description: 更新角色
     * @Date 2020/12/7 16:48
     * @Param role
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "更新角色", httpMethod = "PUT")
    @PutMapping("updateRole")
    public Resp updateRole(@RequestBody Role role) {
        long time = System.currentTimeMillis();
        //判断当前用户是否为null
        if (role.getId() == null) {
            LOGGER.error("用户账号获取失败" + ",用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("用户账号获取失败", Resp.STRING, null);
        }
        try {
            roleService.updateRoleByRoleId(role);
            LOGGER.info("更新用户成功，用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationSuccess("更新用户成功", Resp.LIST, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("更新用户失败，异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("更新用户失败", Resp.SINGLE, null);
        }
    }


    /**
     * @Author: sjyzj
     * @Description: 根据角色名删除角色
     * @Date 2020/12/7 16:54
     * @Param rname
     * @return cn.edu.bjtu.jzlj.util.results.Resp
     * @throws:
     **/
    @ApiOperation(value = "根据角色名删除角色", httpMethod = "DELETE")
    @DeleteMapping("/deleteRoleByRoleName/{rname}")
    public Resp deleteRole(@RequestParam("rname") String rname) {
        long time = System.currentTimeMillis();
        if (StringUtils.isEmpty(rname)) {
            LOGGER.error("用户信息不能为空，用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("用户信息不能为空", Resp.STRING, null);
        }
        try {
            roleService.deleteRole(rname);
            LOGGER.info("根据用户名删除用户成功，用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationSuccess("删除用户成功", Resp.STRING, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("根据用户名删除用户失败，异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("根据用户名删除用户失败", Resp.STRING, null);
        }
    }

    /**
     * @method  batchDelete
     * @description 根据id批量删除
     * @date: 2020-11-08
     * @author: ld
     * @param  roleIds
     * @return Resp
     */
    @ApiOperation(value = "根据角色id批量删除",httpMethod = "DELETE")
    @DeleteMapping("/deleteRoleById/{roleIds}")
    @ControllerEndpoint(operation = "根据id批量删除", exceptionMessage = "根据id批量删除失败")
    public Resp batchDelete(@RequestParam(value="roleIds") List<String> roleIds){
        long startTime = System.currentTimeMillis();
        try {
            roleService.batchDelete(roleIds);
//            roleService.deleteMrole(ids);
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
     * @method  batchDelete
     * @description 根据id批量删除--get方式
     * @date: 2020-11-08
     * @author: ld
     * @param  roleIds
     * @return Resp
     */
    @ApiOperation(value = "根据角色id批量删除",httpMethod = "GET")
    @GetMapping("/deleteRoleById_get/{roleIds}")
    @ControllerEndpoint(operation = "根据id批量删除", exceptionMessage = "根据id批量删除失败")
    public Resp batchDelete_get(@PathVariable(value="roleIds") List<String> roleIds){
        long startTime = System.currentTimeMillis();
        try {
            roleService.batchDelete(roleIds);
//            roleService.deleteMrole(ids);
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
     * @Author: sjy
     * @Description: 根据角色ID删除组织-逻辑删除.此方法会报错Required String parameter '×××' is not present
     * @Date 2020/11/17 16:08
     * @Param  * @param null
     * @return
     * @throws:
     **/
    @ApiOperation(value = "根据角色ID删除一个角色-逻辑删除", httpMethod = "DELETE")
    @DeleteMapping("/deleteOneRole/{roleid}")
    @ControllerEndpoint(operation = "删除组织信息", exceptionMessage = "删除失败")
    public Resp deleteRoleById (@RequestParam(value="roleid") String id) {
        long startTime = System.currentTimeMillis();
        if (org.springframework.util.StringUtils.isEmpty(id)) {
            LOGGER.error("角色不能为空！用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("组织信息不能为空", Resp.STRING, null);
        }
        try {
            roleService.deleteRoleById(id);
            LOGGER.info("根据id删除角色成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("根据id删除角色成功", Resp.STRING, null);
        }catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("根据id删除角色失败，异常：" + e.getMessage() + "，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("根据id删除角色失败", Resp.STRING, null);
        }

    }

    /**
     * @Author: sjy
     * @Description: 使用get方式测试根据角色ID删除组织-逻辑删除
     * @Date 2020/11/17 16:08
     * @Param  * @param null
     * @return
     * @throws:
     **/
    @ApiOperation(value = "根据ID删除一个角色-get", httpMethod = "GET")
    @GetMapping("/deleteOneRole_get/{roleid}")
    @ControllerEndpoint(operation = "删除组织信息", exceptionMessage = "删除失败")
    public Resp deleteRoleById_get (@PathVariable(value="roleid") String id) {
        long startTime = System.currentTimeMillis();
        if (org.springframework.util.StringUtils.isEmpty(id)) {
            LOGGER.error("角色不能为空！用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("组织信息不能为空", Resp.STRING, null);
        }
        try {
            roleService.deleteRoleById_get(id);
            LOGGER.info("根据id删除角色成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("根据id删除角色成功", Resp.STRING, null);
        }catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("根据id删除角色失败，异常：" + e.getMessage() + "，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("根据id删除角色失败", Resp.STRING, null);
        }

    }

    private String getUpdateRoleInfo() {
//        Role role = ShiroUtils.getCurrentUser();
//        String updateUser = role.getId();
        String updateRole = "123123";
        return updateRole;
    }
}
