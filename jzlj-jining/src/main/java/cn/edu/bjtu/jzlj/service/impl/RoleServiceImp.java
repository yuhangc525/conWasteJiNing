package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.Role;
import cn.edu.bjtu.jzlj.dao.SysOrganizationUser;
import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.dao.SysUserRole;
import cn.edu.bjtu.jzlj.mapper.RoleMapper;
import cn.edu.bjtu.jzlj.mapper.SysOrganizationUserMapper;
import cn.edu.bjtu.jzlj.mapper.SysUserRoleMapper;
import cn.edu.bjtu.jzlj.service.RoleService;
import cn.edu.bjtu.jzlj.util.CommonUtil;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.SortUtil;
import cn.edu.bjtu.jzlj.vo.RoleSearchVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.dao.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: RoleServiceImp
 * @Description:
 * @Author sjyzj
 * @Date 2020/11/13 11:03
 */
@Service("RoleService")
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImp extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysOrganizationUserMapper sysOrganizationUserMapper;

//    @Override
//    public List<Role> getUserPageByRoleId(String roleId) {
//        List<Role> roles = roleMapper.getUserPageByRoleId(roleId);
//        return roles;
//    }

    @Override
    public Role findUserRoleByRoleId(String roleId) throws Exception {
        if (roleId == null || "".equals(roleId)) {
            throw new Exception("用户信息获取失败");
        }
        Role role = roleMapper.selectById(roleId);

        return role;
    }

    @Override
    public void batchDelete(List<String> roleIds){

        roleMapper.deleteBatchIds(roleIds);
    }

    @Override
    public int saveData(Role role){
//        return roleMapper.insert(role);
        return roleMapper.addRole(role);
    }

//    @Override
//    public IPage<Role> getListByPage(QueryRequest queryRequest, Role role) {
//        return null;
//    }
//
//    @Override
//    public List<Role> getAllList(QueryRequest queryRequest, Role role) {
//        return null;
//    }

    /*分页查询数据*/
    @Override
    public IPage<Role> getListByPage(QueryRequest queryRequest, Role role)
    {
        Page<Role> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "id", CommonUtil.ORDER_ASC, false);
        IPage<Role> list = roleMapper.getListByPage(page, role);
        return list;
    }

    /*查询全部数据，不分页*/
    @Override
    public List<Role> getAllList(QueryRequest queryRequest, Role role)
    {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "id", CommonUtil.ORDER_ASC, true);
        return roleMapper.getAllList(queryWrapper, role);
    }

//    @Override
//    public int addRole(Role role){
//        return roleMapper.addRole(role);
//    }

//    @Override
//    public IPage<Role> getRoleList(Integer pageNo, Integer pageSize, RoleSearchVo vo) throws DataAccessException {
//
//        Page pageBean = new Page<>(pageNo, pageSize);
////        List<Role> roles = roleMapper.selectRoleList(pageBean, vo);
////        pageBean.setRecords(roles);
//        return pageBean;
//    }


    @Override
    public void updateRoleByRoleId(Role role)throws Exception {
        if (role.getId() == null) {
            throw new Exception("角色信息获取失败");
        }
        try {
            roleMapper.updateById(role);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("更改角色异常");
        }
    }
//    @Override
//    public String getUseridBYUP(String username, String password){
//        String userId = roleMapper.getUseridBYUP(username, password);
//        return userId;
//    };
//    @Override
//    public String getRoleIdBYUI(String roleName){
//        String roleId = roleMapper.getRoleIdBYUI(roleName);
//        return roleId;
//    };
//    @Override
//    public int updateUserRole(String roleName,String roleId){
//        int message = roleMapper.updateUserRole(roleName,roleId);
//        return message;
//    };



    @Override
    public void deleteRole(String rname) throws Exception {
        if (null == rname) {
            throw new Exception("用户主键为空");
        }
        Role role = roleMapper.findUserRoleByRoleId(rname);
//        roleMapper.deleteRole(id, updateRole, updateTime);
        roleMapper.delRoleByRoleName(rname);
    }

    @Override
    public void deleteMrole(List<String> roleIds) throws Exception {
        if (null == roleIds) {
            throw new Exception("用户主键为空");
        }
        try {
//            roleMapper.deleteMRoleById(id);
            roleMapper.deleteBatchIds(roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("删除角色失败,请确认角色与其他业务脱离关联");
        }
    }

    @Override
    public Role findRoleByrname(String rname) throws Exception {
        if (rname == null || "".equals(rname)) {
            throw new Exception("用户信息获取失败");
        }
        Role role = roleMapper.findRoleByRname(rname);
        // 当前用户名无效
        if (role == null) {
            throw new Exception("该用户不存在");
        }
        return role;
    }

    /**
     * @Author: sjy
     * @Description: 根据id删除组织。逻辑删除
     * @Date 2020/11/16 21:06
     * @Param  * @param null
     * @return
     * @throws:
     **/
    @Override
    public void deleteRoleById(String id) throws Exception {
        if (null == id) {
            throw new Exception("组织主键为空！");
        }
        roleMapper.deleteRoleById(id);
    }

    /**
     * @Author: sjy
     * @Description: 根据id删除组织,使用get方式
     * @Date 2020/11/16 21:06
     * @Param  * @param null
     * @return
     * @throws:
     **/
    @Override
    public void deleteRoleById_get(String id) throws Exception {
        if (null == id) {
            throw new Exception("组织主键为空！");
        }
        roleMapper.deleteRoleById_get(id);
    }

    @Override
    public void updateUserRoleByRoleId(String roleId, String userIds)throws DuplicateKeyException, Exception {
        try {
            roleMapper.updateUserRoleByRoleId(roleId, userIds);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
        }
    }
}
