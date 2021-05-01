package cn.edu.bjtu.jzlj.controller;

import java.util.Date;

import java.util.List;



import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.config.shiro.ShiroUtils;
import cn.edu.bjtu.jzlj.dao.PageSource;
import cn.edu.bjtu.jzlj.dao.SysOrganizationUser;
import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.dao.SysUserRole;
import cn.edu.bjtu.jzlj.mapper.SysOrganizationUserMapper;
import cn.edu.bjtu.jzlj.mapper.SysUserMapper;
import cn.edu.bjtu.jzlj.mapper.SysUserRoleMapper;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.UuidTool;
import cn.edu.bjtu.jzlj.util.results.Resp;
import cn.edu.bjtu.jzlj.util.RegexUtils;

import io.swagger.annotations.Api;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;;
import cn.edu.bjtu.jzlj.service.SysUserService;
import cn.edu.bjtu.jzlj.service.PageSourceService;


/**
 * @program: user-service
 * @description:
 * @author ld
 * @date ${2020-11-08}
 */
@Api(description = "用户信息接口")
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PageSourceService pageSourceService;

    @Autowired
    private SysOrganizationUserMapper sysOrganizationUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * @Author: 田英杰
     * @Description: 设置用户信息
     * @Date 2020/12/8 11:07
     * @Param  * @param null
     * @return
     * @throws:
     **/
    private void setUpdateUserInfo(SysUser sysUserInfo) {
        SysUser sysUser = ShiroUtils.getCurrentUser();
        sysUserInfo.setUpdateUser(sysUser.getId());
//        sysUserInfo.setUpdateUser("tianyingjie");
//        userInfo.setCreateUser("654321");
        sysUserInfo.setUpdateTime(new Date());
    }

    /**
     * @method  getListByPage
     * @description 列表查询
     * @date: ${2020-11-08}
     * @author: ld
     * @param queryRequest
     * @param sysUser
     * @return Resp
     */
    @ApiOperation(value = "用户列表查询", httpMethod = "GET")
    @GetMapping("/listUserByPage")
    @ControllerEndpoint(operation = "用户列表查询", exceptionMessage = "用户列表查询失败")
    public Resp getListByPage(QueryRequest queryRequest, SysUser sysUser){
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
                IPage<SysUser> sysUserList = sysUserService.getListByPage(queryRequest, sysUser);
                long endTime = System.currentTimeMillis();
                LOGGER.info("分页列举成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("分页查看", Resp.LIST, sysUserList);
            }
            else
            {
                List<SysUser> sysUserList = sysUserService.getAllList(queryRequest,sysUser);
                long endTime = System.currentTimeMillis();
                LOGGER.info("用户列表查询成功，用时" + (endTime - startTime) + "ms");
                return Resp.getInstantiationSuccess("用户列表查询查看", Resp.LIST, sysUserList);
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
     * @method  save
     * @description 新增用户
     * @date: 2020-11-08
     * @author: ld
     * @param sysUser
     * @return Resp
     */
//    @ApiOperation(value = "新增用户", httpMethod = "POST")
//    @PostMapping("/createSysUser")
//    @ControllerEndpoint(operation = "新增", exceptionMessage = "新增失败")
//    public Resp creatUser(@RequestBody SysUser sysUser){
//        long startTime = System.currentTimeMillis();
//        if (null == sysUser) {
//            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
//        }
//        try {
//                sysUserService.creatUser(sysUser);
//                long endTime = System.currentTimeMillis();
//                LOGGER.info("创建成功，用时" + (endTime - startTime) + "ms");
//                return Resp.getInstantiationSuccess("创建成功", Resp.SINGLE, null);
//        } catch (Exception e) {
//            long endTime = System.currentTimeMillis();
//            LOGGER.error("创建失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
//            return Resp.getInstantiationError("创建异常，原因：" + e.getMessage(), Resp.SINGLE, sysUser);
//        }
//    }




     /**
      * @Author: 田英杰
      * @Description: 正经的新增用户
      * @Date 2020/11/17 16:36
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "添加用户", httpMethod = "POST")
    @PostMapping("/createUser")
    public Resp createUser(@RequestBody SysUser sysUser) {
        long time = System.currentTimeMillis();
        if (StringUtils.isEmpty(sysUser.getUsername()) || StringUtils.isEmpty(sysUser.getPassword())) {
            LOGGER.error("用户信息不能为空" + ",用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationSuccess("用户信息不能为空", Resp.STRING, null);
        }
        String passwd = sysUser.getPassword();
        String md5Pwd = ShiroUtils.getMd5Pwd(passwd);
        sysUser.setPassword(md5Pwd);
        sysUser.setUsername(sysUser.getUsername().trim());
//        setCreatUserInfo(sysUser);
        if (!StringUtils.isEmpty(sysUser.getMobile())) {
            try {
                RegexUtils.isChinaPhoneLegal(sysUser.getMobile());
                sysUser.setMobile(sysUser.getMobile().trim());
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("手机号不合法，异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - time));
                return Resp.getInstantiationError("手机号不合法", Resp.STRING, null);
            }
        }
        try {
            if (!sysUserService.isExistUserName(sysUser.getUsername())) {
                //通用setUser
                saveSetUser(sysUser);
                Boolean bl = sysUserService.save(sysUser);
//                Boolean bl = sysUserService.creatUser(sysUser);

                if (bl) {
                    if (!StringUtils.isEmpty(sysUser.getRoleId())) {
//                        // 插入角色关联表
                        sysUserRoleMapper.insert(new SysUserRole(sysUser.getId(), sysUser.getRoleId()));
                        System.out.println("用户角色关系插入成功！");
                    }
                    if (!StringUtils.isEmpty(sysUser.getOrgId())) {
                        sysOrganizationUserMapper.insert(new SysOrganizationUser(sysUser.getId(), sysUser.getOrgId()));
                    }
                    LOGGER.info("添加用户成功，用时：" + (System.currentTimeMillis() - time));
                    return Resp.getInstantiationSuccess("添加用户成功", Resp.SINGLE, null);
                } else {
                    LOGGER.error("添加用户失败" + ",用时：" + (System.currentTimeMillis() - time));
                    return Resp.getInstantiationError("添加用户失败", Resp.SINGLE, null);
                }

            } else {
                LOGGER.error("用户名已存在" + ",用时：" + (System.currentTimeMillis() - time));
                return Resp.getInstantiationError("用户名已存在", Resp.SINGLE, null);
            }
        } catch (DuplicateKeyException e) {
            LOGGER.error("手机号已被占用请更换，原因：" + e.getMessage() + "，用时" + (System.currentTimeMillis() - time) + "ms");
            return Resp.getInstantiationError("手机号已被占用请更换", null, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加用户失败，异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("添加用户失败" + e.getMessage(), Resp.SINGLE, null);
        }
    }


    /**
     * @method  update
     * @description 编辑用户
     * @date: 2020-11-08
     * @author: ld
     * @param sysUser
     * @return Resp
     */
//    @ApiOperation(value = "编辑用户", httpMethod = "PUT")
//    @PutMapping("/putUser")
    @ControllerEndpoint(operation = "编辑用户", exceptionMessage = "编辑失败")
    public Resp update(@RequestBody  SysUser sysUser){
        long startTime =  System.currentTimeMillis();
        try {
            //更新
                if(sysUserService.getById(sysUser.getId()) == null){
                    long endTime = System.currentTimeMillis();
                    LOGGER.error("修改失败，原因：修改不存在，用时" + (endTime - startTime) + "ms");
                    return Resp.getInstantiationError("修改失败，原因：修改的id不存在，", null, null);
                }
                sysUserService.updateData(sysUser);
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
      * @Author: 田英杰
      * @Description: 根据用户id更新用户信息-高级的
      * @Date 2020/12/8 10:49
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "正统的更新用户", httpMethod = "PUT")
    @PutMapping("/updateUser")
    @ControllerEndpoint(operation = "编辑用户", exceptionMessage = "编辑失败")
//    @ResponseBody
    public Resp updateUser(@RequestBody SysUser sysUser) {
        long time = System.currentTimeMillis();
        //判断当前用户是否为null
        if (sysUser.getId() == null) {
            LOGGER.error("用户账号获取失败" + ",用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("用户账号获取失败", Resp.STRING, null);
        }
        String passwd = sysUser.getPassword();
        String md5Pwd = ShiroUtils.getMd5Pwd(passwd);
        sysUser.setPassword(md5Pwd);
        if (!StringUtils.isEmpty(sysUser.getMobile())) {
            try {
                RegexUtils.isPhoneLegal(sysUser.getMobile());
                sysUser.setMobile(sysUser.getMobile());
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("手机号不合法" + ",用时：" + (System.currentTimeMillis() - time));
                return Resp.getInstantiationError("手机号不合法", Resp.STRING, null);
            }
        }
//        setUpdateUserInfo(sysUser);
        try {
            sysUserService.updateUserByUserId(sysUser);
            LOGGER.info("更新用户成功，用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationSuccess("更新用户成功", Resp.LIST, null);
        } catch (DuplicateKeyException e) {
            LOGGER.error("手机号已被占用请更换，原因：" + e.getMessage() + "，用时" + (System.currentTimeMillis() - time) + "ms");
            return Resp.getInstantiationError("手机号已被占用请更换", null, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("更新用户失败，异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("更新用户失败", Resp.SINGLE, null);
        }
    }




    /**
     * @method  batchDelete
     * @description 根据id批量删除
     * @date: 2020-11-08
     * @author: ld
     * @param  ids
     * @return Resp
     */
    @ApiOperation(value = "根据用户id批量删除",httpMethod = "DELETE")
    @DeleteMapping("/batchDelete/{userIds}")
    @ControllerEndpoint(operation = "根据id批量删除", exceptionMessage = "根据id批量删除失败")
    public Resp batchDelete(@RequestParam("ids") List<String> ids){
        long startTime = System.currentTimeMillis();
        try {
                sysUserService.batchDelete(ids);
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
      * @Author: 田英杰
      * @Description: 根据id查询用户
      * @Date 2020/11/12 14:34
      * @Param  * @param null
      * @return 
      * @throws:
      **/
    @ApiOperation(value = "根据id查询用户信息", httpMethod = "GET")
    @GetMapping("/getUserById")
    @ControllerEndpoint(operation = "根据id查询用户信息", exceptionMessage = "查询失败")
    public Resp getUserById(SysUser sysUser) {
        long startTime = System.currentTimeMillis();
        try {
            SysUser sysuser = sysUserService.getUserById(sysUser.getId());
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据id查询用户成功，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("查询成功", Resp.STRING, sysuser);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("查询用户失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("查询用户失败："+ e.getMessage(),Resp.LIST,null );
        }
//
    }
    
     /**
      * @Author: 田英杰
      * @Description: 根据用户名查询用户信息
      * @Date 2020/11/12 14:34
      * @Param  * @param null
      * @return 
      * @throws:
      **/
    @ApiOperation(value = "根据用户名查询用户信息", httpMethod = "GET")
    @GetMapping("/getUserByUname")
    @ControllerEndpoint(operation = "根据用户名查询用户信息", exceptionMessage = "查询失败")
    public Resp getUserByUname(SysUser sysUser) {
        long startTime = System.currentTimeMillis();
        try {
            SysUser sysuser = sysUserService.getUserByUname(sysUser.getUsername());
            long endTime = System.currentTimeMillis();
            LOGGER.info("根据用户名查询用户成功，用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("查询成功", Resp.STRING, sysuser);
        }catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("查询用户失败，原因：", e.getMessage()+ "，用时" +(endTime - startTime) + "ms");
            return Resp.getInstantiationError("查询用户失败："+ e.getMessage(),Resp.LIST,null );
        }
        
    }


     /**
      * @Author: 田英杰
      * @Description: 根据账号密码获得用户全部信息
      * @Date 2020/11/13 21:56
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "通过账号密码获得用户全部信息", httpMethod = "GET")
    @GetMapping("/getUserByunameupasswd")
    public Resp getUserByunameupasswd(String uname, String upasswd) {
        long time = System.currentTimeMillis();
        if (uname == null && upasswd == null) {
            LOGGER.error("请输入账号密码，用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("请输入账号密码", Resp.STRING, null);
        }
        try {
            String md5Pwd = ShiroUtils.getMd5Pwd(upasswd);
            SysUser sysUser = sysUserService.findUserByNameAndPassword(uname, md5Pwd);
//            SysUser sysUser = sysUserService.findUserByNameAndPassword(uname, upasswd);
            if (sysUser == null) {
                LOGGER.error("用户名或密码错误，用时：" + (System.currentTimeMillis() - time));
                return Resp.getInstantiationError("用户名或密码错误", Resp.STRING, null);
            }
            if (0 == sysUser.getStatus()) {
                LOGGER.error("用户被禁用，用时：" + (System.currentTimeMillis() - time));
                return Resp.getInstantiationError("用户被禁用", Resp.STRING, null);
//                throw new UnknownAccountException("用户被禁用");
            } else if (2 == sysUser.getStatus()) {
                LOGGER.error("用户已被删除，用时：" + (System.currentTimeMillis() - time));
                return Resp.getInstantiationError("用户已被删除", Resp.STRING, null);
            }
            List<PageSource> list;
            String roleID;
            if (null == sysUser.getRoleId()) {
                throw new UnauthorizedException("用户无角色");
            } else {
                list = pageSourceService.getPSourceListByRoleId(sysUser.getRoleId());
                roleID = sysUser.getRoleId();
                System.out.println(roleID);
                System.out.println(list);
                sysUser.setPageSources(list);
            }
            LOGGER.info("通过账号密码获得用户全部信息成功，用时：" + (System.currentTimeMillis() - time) + "ms");
            return Resp.getInstantiationSuccess("通过账号密码获得用户全部信息成功", Resp.STRING, sysUser);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("通过账号密码获得用户全部信息失败，异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - time));
            return Resp.getInstantiationError("通过账号密码获得用户全部信息失败" + e.getMessage(), Resp.STRING, null);
        }
    }



     /**
      * @Author: 田英杰
      * @Description: 根据用户id禁用用户
      * @Date 2020/11/13 12:50
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "禁用用户", httpMethod = "GET")
    @GetMapping("/disabledById/{userId}")
    public Resp disabledById(@PathVariable String userId) {
        long startTime = System.currentTimeMillis();
//        判断当前用户id是否有效
        if (userId == null || "".equals(userId)) {
            LOGGER.error("用户信息获取失败，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("用户信息获取失败", Resp.STRING, null);
        }
        try {
//            查询当前用户
            SysUser sysuser = sysUserService.getUserById(userId);
            if (sysuser == null) {
                LOGGER.error("用户不存在，用时：" + (System.currentTimeMillis() - startTime) + "ms");
                return Resp.getInstantiationError("用户不存在", Resp.STRING, null);
            }
//            判断当前用户的状态
            if (sysuser.getStatus() == 0) {
                LOGGER.error("当前用户是禁用状态，用时：" + (System.currentTimeMillis() - startTime) + "ms");
                return Resp.getInstantiationError("当前用户是禁用状态", Resp.STRING, null);
            }
            if (sysuser.getStatus() == 2) {
                LOGGER.error("当前用户被删除，用时：" + (System.currentTimeMillis() - startTime) + "ms");
                return Resp.getInstantiationError("当前用户被删除", Resp.STRING, null);
            }
//            ！！！注意这里的修改者信息是写死的，应该getUpdateUserInfo（）
            sysUserService.disabledById(sysuser, getUpdateUserInfo(), new Date());
            LOGGER.info("用户禁用成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("用户禁用成功", Resp.STRING, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("用户禁用失败，异常：" + e.getMessage() + "，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("用户禁用失败", Resp.STRING, null);
        }
    }


     /**
      * @Author: 田英杰
      * @Description: 根据id解禁用户
      * @Date 2020/11/13 13:31
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "解禁用户", httpMethod = "GET")
    @GetMapping("/enableById/{userId}")
    public Resp enableById (@PathVariable String userId) {
        long startTime = System.currentTimeMillis();
//        判断当前用户id是否有效
        if (userId == null || "".equals(userId)) {
            LOGGER.error("用户信息获取失败，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("用户信息获取失败", Resp.STRING, null);
        }
        try {
            SysUser sysUser = sysUserService.getUserById(userId);
            if (sysUser == null) {
                LOGGER.error("用户不存在，用时：" + (System.currentTimeMillis() - startTime) + "ms");
                return Resp.getInstantiationError("用户不存在", Resp.STRING, null);
            }
            if (sysUser.getStatus() == 1) {
                LOGGER.error("用户未被禁用，用时：" + (System.currentTimeMillis() - startTime) + "ms");
                return Resp.getInstantiationError("用户未被禁用", Resp.STRING, null);
            }
            if (sysUser.getStatus() == 2) {
                LOGGER.error("当前用户被删除，用时：" + (System.currentTimeMillis() - startTime) + "ms");
                return Resp.getInstantiationError("当前用户被删除", Resp.STRING, null);
            }
            sysUserService.enableById(sysUser,getUpdateUserInfo(), new Date());
            LOGGER.info("用户解禁成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("用户解禁成功", Resp.STRING, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("用户解禁失败，异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("用户解禁失败", Resp.STRING, null);
        }

    }


     /**
      * @Author: 田英杰
      * @Description: 根据用户名删除用户，逻辑删除
      * @Date 2020/11/13 15:46
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "根据用户名删除用户-逻辑删除", httpMethod = "DELETE")
    @DeleteMapping("/deleteUser/{username}")
//    根据service的根据id删除用户
    public Resp deleteByUname (@PathVariable String username) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(username)) {
            LOGGER.error("用户信息不能为空！用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("用户信息不能为空", Resp.STRING, null);
        }
        try {
            sysUserService.deleteUserById(sysUserService.getUserByUname(username).getId(), getUpdateUserInfo(), new Date());
            LOGGER.info("根据用户名删除用户成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("根据用户名删除用户成功", Resp.STRING, null);
        }catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("根据用户名删除用户失败，异常：" + e.getMessage() + "，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("根据用户名删除用户失败", Resp.STRING, null);
        }
    }



     /**
      * @Author: 田英杰
      * @Description: 根据多个id删除多个用户，逻辑删除
      * @Date 2020/11/13 15:50
      * @Param  * @param null
      * @return
      * @throws:
      **/
     @ApiOperation(value = "根据多个id删除多个用户-逻辑删除", httpMethod = "DELETE")
     @DeleteMapping("/deleteMUserById")
    public Resp deleteMUserById(@RequestParam(value = "userid") List<String> id) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(id)) {
            LOGGER.error("用户信息不能为空！用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("用户信息不能为空", Resp.STRING, null);
        }
        try {
            sysUserService.deleteMUserById(id, getUpdateUserInfo(), new Date());
            LOGGER.info("根据多个id删除多个用户成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("根据多个id删除多个用户成功", Resp.STRING, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("根据多个id删除多个用户失败，异常：" + e.getMessage() + "，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("根据多个id删除多个用户失败", Resp.STRING, null);
        }
    }

    /**
     * @Author: sjy
     * @Description: 根据多个id删除多个用户，get方式
     * @Date 2021/1/25 19:50
     * @Param  * @param null
     * @return
     * @throws:
     **/
    @ApiOperation(value = "根据多个id删除多个用户-逻辑删除", httpMethod = "GET")
    @GetMapping("/deleteMUserById_get/{userid}")
    public Resp deleteMUserById_get(@PathVariable(value = "userid") List<String> id) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(id)) {
            LOGGER.error("用户信息不能为空！用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("用户信息不能为空", Resp.STRING, null);
        }
        try {
            sysUserService.deleteMUserById_get(id);
            LOGGER.info("根据多个id删除多个用户成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("根据多个id删除多个用户成功", Resp.STRING, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("根据多个id删除多个用户失败，异常：" + e.getMessage() + "，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("根据多个id删除多个用户失败", Resp.STRING, null);
        }
    }

     /**
      * @Author: 田英杰
      * @Description: 根据id重置密码
      * @Date 2020/12/9 15:53
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "根据id重置密码", httpMethod = "PUT")
    @PutMapping("/resetPW/{userId}/{password}")
    public Resp resetPW(@PathVariable String userId, @PathVariable String password) {
        long startTime = System.currentTimeMillis();
//        判断当前用户信息是否有效
        if (userId == null || "".equals(userId)) {
            LOGGER.error("用户信息获取失败，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("用户信息获取失败", Resp.STRING, null);
        }
        try {
//            查询当前用户
            SysUser sysUser = sysUserService.getUserById(userId);

//            当前用户是否存在
            if (sysUser == null) {
                LOGGER.error("当前用户不存在，用时：" + (System.currentTimeMillis() - startTime) + "ms");
                return Resp.getInstantiationError("当前用户不存在", Resp.STRING, null);
            }
//            查看当前用户是否被删除
            if (sysUser.getStatus() == 2) {
                LOGGER.error("当前用户被删除，用时：" + (System.currentTimeMillis() - startTime) + "ms");
                return Resp.getInstantiationError("当前用户被删除", Resp.STRING, null);
            }
//            ？？？重置密码是否检验旧密码？？？
            sysUserService.resetPW(sysUser.getId(), password, getUpdateUserInfo(), new Date());
            LOGGER.info("密码重置成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("密码重置成功", Resp.STRING, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("密码重置失败，异常：" + e.getMessage() + "，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("密码重置失败", Resp.STRING, null);
        }
    }



    /**
     * @Author: sjy
     * @Description: 根据角色ID删除组织-get方式
     * @Date 2020/11/17 16:08
     * @Param  * @param null
     * @return
     * @throws:
     **/
    @ApiOperation(value = "根据ID删除一个用户-get方式", httpMethod = "GET")
    @GetMapping("/deleteUserById_get/{userid}")
    @ControllerEndpoint(operation = "删除组织信息", exceptionMessage = "删除失败")
    public Resp deleteUserById_get (@PathVariable(value="userid") String id) {
        long startTime = System.currentTimeMillis();
        if (org.springframework.util.StringUtils.isEmpty(id)) {
            LOGGER.error("用户不能为空！用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("组织信息不能为空", Resp.STRING, null);
        }
        try {
            sysUserService.deleteUserById_get(id);
            LOGGER.info("根据id删除用户成功，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationSuccess("根据id删除用户成功", Resp.STRING, null);
        }catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("根据id删除用户失败，异常：" + e.getMessage() + "，用时：" + (System.currentTimeMillis() - startTime) + "ms");
            return Resp.getInstantiationError("根据id删除用户失败", Resp.STRING, null);
        }

    }





     /**
      * @Author: 田英杰
      * @Description: 设置创建者信息
      * @Date 2020/11/17 16:39
      * @Param  * @param null
      * @return
      * @throws:
      **/
    private void setCreatUserInfo(SysUser userInfo) {
        SysUser sysUser = ShiroUtils.getCurrentUser();
        userInfo.setCreateUser(sysUser.getId());
//        userInfo.setCreateUser("tianyingjie");
        userInfo.setCreateTime(new Date());
    }



     /**
      * @Author: 田英杰
      * @Description:
      * @Date 2020/11/17 16:47
      * @Param  * @param null
      * @return
      * @throws:
      **/
    private SysUser saveSetUser(SysUser sysUser) {
        sysUser.setId(UuidTool.getUUID());
        sysUser.setUsername(sysUser.getUsername().trim());
        sysUser.setMobile(sysUser.getMobile().trim());
        sysUser.setStatus(1);
//        user.setCreateUser(user.getId());
//        user.setCreateTime(new Date());
        return sysUser;
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
