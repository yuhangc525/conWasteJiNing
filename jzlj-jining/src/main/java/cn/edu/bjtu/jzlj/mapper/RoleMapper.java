package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.Role;
import cn.edu.bjtu.jzlj.dao.SysOrganizationUser;
import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.dao.SysUserRole;
import cn.edu.bjtu.jzlj.vo.RoleSearchVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<Role> {
//    List<Role> selectRoleList(Page pageBean, @Param("vo") RoleSearchVo vo) throws DataAccessException;
    /**
     * 获取所有角色通过分页
     * @author chenzheng
     * @param page
     * @return
     */
    IPage<Role> getRoleListByPage(Page page, RoleSearchVo vo);
    /**列表查询分页**/
    IPage<Role> getListByPage(Page<Role> page, @Param("role") Role role);

    /*不分页查询*/
    List<Role> getAllList(@Param("ew") QueryWrapper<Role> queryWrapper, @Param("role") Role role);
    /**
     * 角色条件查询无角色用户
     * @param roleId
     * @return
     */
    @Select("select * from sys_role su where  su.status!=2 and su.id not in \r\n" +
            "(select sur.user_id from sys_role_role sur where sur.role_id=#{roleId})")
    List<Role> getUserNoRoleByRoleId(@Param("roleId") String roleId);

    /**
     * 角色条件分页查询对应的用户
     * @param roleId
     * @return
     */
    @Select("select * from sys_user su where  su.status != 2 and su.id in \r\n" +
            "(select sur.user_id from sys_user_role sur where sur.role_id=#{roleId})")
    List<Role> getUserPageByRoleId(@Param("roleId")String roleId);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    Role findUserRoleByRoleId(@Param("id") String id);

    /**
     * 更新角色
     * @param role
     */
//    @Select("SELECT `sys_user`.id FROM `sys_user` WHERE `sys_user`.username = #{username} and `sys_user`.`password` = #{password}")
//    String getUseridBYUP(@Param("username") String username,@Param("password") String password);
//    @Select("SELECT `sys_role`.id FROM `sys_role` WHERE `sys_role`.`role_name` = #{roleName}")
//    String getRoleIdBYUI(@Param("roleName") String roleName);
//    @Update("UPDATE `sys_user_role` SET `sys_user_role`.`role_id` = #{roleId} WHERE `sys_user_role`.`user_id` = #{userId}")
//    int updateUserRole(@Param("userId") String userId, @Param("roleId") String roleId);

    /**
     * 根据角色id 删除 角色
     */
    public void delRoleById(@Param("id") String id);
    void deleteMRoleById(@Param("ids") List<String> ids);
    /**
     * 根据角色名 删除 角色
     */
    void delRoleByRoleName(@Param("rname") String rname);

    /**
     * 根据用户名查询用户
     * @param role_name
     * @return
     */
    Role findRoleByRname(@Param("role_name") String role_name);


    /**
	 * 创建角色
	 */
	int addRole(@Param("role") Role role);

    /**
     * 更新 UserRole by UserId : userId不能为空，roleId可以为空
     * @param userRole
     */
    void updateUserRoleByUREntity(@Param("userRole") SysUserRole userRole);

    /**
     * 更新用户
     * @param role
     */
    void updateRoleByRoleId(@Param("role") Role role);

    /**
     * 更新 UserOrganization by UserId : userId不能为空，orgId可以为空
     * @param sysOrganizationUser
     */
//    @Update("UPDATE `sys_organization_user` SET `sys_organization_user`.`orgid` = #{orgId} WHERE `sys_organization_user`.`userid` = #{userId}")
    void updateUserOrganizationByUREntity(@Param("sysOrganizationUser") SysOrganizationUser sysOrganizationUser);

    /**
     * @Author: sjy
     * @Description: 根据id删除角色，逻辑删除
     * @Date 2020/11/16 21:06
     * @Param  * @param null
     * @return
     * @throws:
     **/
    void deleteRoleById(@Param("roleId") String id);

    /**
     * @Author: sjy
     * @Description: 根据id删除角色，使用get方式
     * @Date 2020/11/16 21:06
     * @Param  * @param null
     * @return
     * @throws:
     **/
    void deleteRoleById_get(@Param("roleid") String id);

    void updateUserRoleByRoleId(@Param("roleId") String roleId, @Param("userId") String userId);

    /**
     * @Author: sjy
     * @Description: 根据id删除多个角色（批量删除），逻辑删除
     * @Date 2020/11/17 16:05
     * @Param  * @param null
     * @return
     * @throws:
     **/
    void deleteMOrgById(@Param("id")List<String> id, @Param("update_user") String update_user, @Param("update_time") Date update_time);
}
