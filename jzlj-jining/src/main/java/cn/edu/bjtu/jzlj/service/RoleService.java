package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.Role;
import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.vo.RoleSearchVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.dao.DuplicateKeyException;

import java.util.Date;
import java.util.List;

public interface RoleService extends IService<Role> {

    /** @Author: sjyzj
     * @Description: 角色条件分页查询对应的用户
     * @Date 2020/11/13 10:32
     * @Param roleId
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.main.model.User>
     * @throws:
     **/
//    List<User> getUserPageByRoleId(String roleId);


    /** @Author: sjyzj
     * @Description: 角色条件查询无角色用户
     * @Date 2020/11/13 10:32
     * @Param roleId
     * @return java.util.List<com.main.dao.User>
     * @throws:
     **/
//    List<User> getUserNoRoleByRoleId(String roleId);

    /** @Author: sjyzj
     * @Description: 用户角色设置
     * @Date 2020/11/13 10:32
     * @Param roleId
     * @param userIds
     * @return void
     * @throws:
     **/
    void updateUserRoleByRoleId(String roleId, String userIds) throws Exception;

    /** @Author: sjyzj
     * @Description: 根据角色ID查询角色
     * @Date 2020/11/13 10:32
     * @Param userId
     * @return com.main.model.User
     * @throws:
     **/
    Role findUserRoleByRoleId(String userId) throws Exception;

    /*新增*/
    int saveData(Role role);

    /**
     * 获取用户list
     * @param pageNo
     * @param pageSize
     * @param vo
     * @return
     */
//    IPage<Role> getRoleList(Integer pageNo, Integer pageSize, RoleSearchVo vo);
    IPage<Role> getListByPage(QueryRequest queryRequest, Role role);
    List<Role> getAllList(QueryRequest queryRequest, Role role);

    /** @Author: sjyzj
     * @Description: 修改用户
     * @Date 2020/11/13 10:32
     * @Param user
     * @return void
     * @throws:
     **/
    void updateRoleByRoleId(Role role)throws DuplicateKeyException, Exception;

    /** @Author: sjyzj
     * @Description: 根据角色名查询角色
     * @Date 2020/11/13 10:32
     * @Param rname 角色名
     * @return com.main.model.User
     * @throws:
     **/
    Role findRoleByrname(String rname) throws Exception;

    /** @Author: sjyzj
     * @Description: 根据id删除用户（逻辑删除）
     * @Date 2020/11/13 10:32
     * @Param id
     * @return void
     * @throws:
     **/
//    void deleteRole(String rname, String updateRole, Date updateTime) throws Exception;
    void deleteRole(String rname) throws Exception;


    /** @Author: sjyzj
     * @Description: 根据多个id删除多个用户
     * @Date 2020/11/13 10:32
     * @Param id
     * @return void
     * @throws:
     **/
    void deleteMrole(List<String> ids) throws Exception;

    /*批量删除*/
    void batchDelete(List<String> roleIds)throws Exception;

    /**
     * @Author: sjy
     * @Description: 根据id删除角色。逻辑删除
     * @Date 2020/11/16 21:08
     * @Param  * @param null
     * @return
     * @throws:
     **/
    void deleteRoleById(String id) throws Exception;

    /**
     * @Author: sjy
     * @Description: 根据id删除角色,get方式
     * @Date 2020/11/16 21:08
     * @Param  * @param null
     * @return
     * @throws:
     **/
    void deleteRoleById_get(String id) throws Exception;


//    String getUseridBYUP(String username, String password);
//    String getRoleIdBYUI(String roleName);
//    int updateUserRole(String roleName,String roleId);
}
