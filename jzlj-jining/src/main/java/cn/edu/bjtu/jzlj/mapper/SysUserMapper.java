package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.dao.SysUserRole;
import cn.edu.bjtu.jzlj.dao.SysOrganizationUser;
import cn.edu.bjtu.jzlj.vo.UserSearchVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.SQLTransactionRollbackException;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * @program: user-service
 * @description:  Mapper 接口
 * @author ld
 * @date 2020-11-08
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**列表查询分页**/
    IPage<SysUser> getListByPage(Page<SysUser> page, @Param("sysUser") SysUser sysUser);

    /*不分页查询*/
    List<SysUser> getAllList(@Param("ew") QueryWrapper<SysUser> queryWrapper, @Param("sysUser") SysUser sysUser);
    /**
     * 这句看不懂
     */
//    List<SysUser> selectUserList(Page pageBean, @Param("vo") UserSearchVo vo) throws DataAccessException;

     /**
      * @Author: 田英杰
      * @Description: 创建用户
      * @Date 2020/11/11 20:33
      * @Param  * @param null
      * @return
      * @throws:
      **/
    Boolean creatUser(SysUser sysUser);




     /**
      * @Author: 田英杰
      * @Description: 根据用户名查询用户信息
      * @Date 2020/11/19 14:35
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Select("select * from sys_user where username = #{userName}")
    SysUser getByUserName(@Param("userName") String userName);

     /**
      * @Author: 田英杰
      * @Description: 根据id查询用户信息
      * @Date 2020/11/12 12:41
      * @Param  * @param null
      * @return
      * @throws:
      **/
    SysUser getUserById(@Param("id") String id);


     /**
      * @Author: 田英杰
      * @Description:  根据id更新用户信息
      * @Date 2020/11/12 12:32
      * @Param  * @param null
      * @return
      * @throws:
      **/
    int updateUserById(@Param("sysUser") SysUser sysUser);


     /**
      * @Author: 田英杰
      * @Description: 根据用户id更新用户信息-高级的
      * @Date 2020/12/8 10:20
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void updateUserByUserId(SysUser sysUser);


     /**
      * @Author: 田英杰
      * @Description: 更新 UserRole by UserId : userId不能为空，roleId可以为空
      * @Date 2020/12/8 10:32
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void updateUserRoleByUREntity(@Param("sysUserRole")SysUserRole sysUserRole);


     /**
      * @Author: 田英杰
      * @Description: 更新 UserOrganization by UserId : userId不能为空，orgId可以为空
      * @Date 2020/12/8 10:36
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void updateUserOrganizationByUOEntity(@Param("sysOrganizationUser")SysOrganizationUser sysOrganizationUser);


     /**
      * @Author: 田英杰
      * @Description: 根据用户名查询用户信息
      * @Date 2020/11/12 15:17
      * @Param  * @param null
      * @return
      * @throws:
      **/
    SysUser getUserByUname(@Param("username") String username);

     /**
      * @Author: 田英杰
      * @Description: 根据账号密码获得用户全部信息
      * @Date 2020/11/14 11:28
      * @Param  * @param null
      * @return
      * @throws:
      **/
    SysUser findUserByNameAndPassword (@Param("uname") String uname, @Param("upasswd") String upasswd);


     /**
      * @Author: 田英杰
      * @Description: 根据id禁用用户
      * @Date 2020/11/13 11:13
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void disabledById(@Param("id") String id, @Param("updateUser") String update_user, @Param("updateTime") Date update_time);

     /**
      * @Author: 田英杰
      * @Description: 根据id解禁用户
      * @Date 2020/11/13 13:13
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void enableById(@Param("id") String id, @Param("updateUser") String update_user, @Param("updateTime") Date update_time);

     /**
      * @Author: 田英杰
      * @Description: 根据id删除用户, 逻辑删除
      * @Date 2020/11/13 13:52
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void deleteUserById(@Param("id") String id, @Param("updateUser") String update_user, @Param("updateTime") Date update_time);

     /**
      * @Author: 田英杰
      * @Description: 根据多个id删除多个用户，逻辑删除
      * @Date 2021/1/25 19:50
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void deleteMUserById(@Param("id")List<String> id, @Param("updateUser") String update_user, @Param("updateTime") Date update_time);

    /**
     * @Author: sjy
     * @Description: 根据一个id删除一个用户，get方法
     * @Date 2021/1/25 19:50
     * @Param  * @param null
     * @return
     * @throws:
     **/
    void deleteUserById_get(@Param("id") String id);

    /**
     * @Author: 田英杰
     * @Description: 根据多个id删除多个用户，逻辑删除
     * @Date 2020/11/13 15:39
     * @Param  * @param null
     * @return
     * @throws:
     **/
    void deleteMUserById_get(@Param("id")List<String> id);

    /**
      * @Author: 田英杰
      * @Description: 根据id重置密码
      * @Date 2020/11/13 15:57
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void resetPW(@Param("id") String id, @Param("password") String password, @Param("updateUser") String update_user, @Param("updateTime") Date update_time);


}

