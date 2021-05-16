package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.config.shiro.ShiroUtils;
import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.dao.SysUserRole;
import cn.edu.bjtu.jzlj.dao.SysOrganizationUser;
import cn.edu.bjtu.jzlj.mapper.SysOrganizationUserMapper;
import cn.edu.bjtu.jzlj.mapper.SysUserMapper;
import cn.edu.bjtu.jzlj.mapper.SysUserRoleMapper;
import cn.edu.bjtu.jzlj.service.SysUserService;
import cn.edu.bjtu.jzlj.util.CommonUtil;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.SortUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.transaction.annotation.Transactional;


/**
 * @program: user-service
 * @description:  service 接口实现类
 * @author ld
 * @date 2020-11-08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

        @Autowired
        private SysUserMapper sysUserMapper;

        @Autowired
        private SysUserRoleMapper sysUserRoleMapper;

        @Autowired
        private SysOrganizationUserMapper sysOrganizationUserMapper;

        /*分页查询数据*/
        @Override
        public IPage<SysUser> getListByPage(QueryRequest queryRequest, SysUser sysUser)
        {
                Page<SysUser> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
                SortUtil.handlePageSort(queryRequest, page, "username", CommonUtil.ORDER_ASC, false);
                IPage<SysUser> list = sysUserMapper.getListByPage(page, sysUser);
                return list;
        }

        /*查询全部数据，不分页*/
        @Override
        public List<SysUser> getAllList(QueryRequest queryRequest, SysUser sysUser)
        {
                QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
                SortUtil.handleWrapperSort(queryRequest, queryWrapper, "username", CommonUtil.ORDER_ASC, true);
                return sysUserMapper.getAllList(queryWrapper, sysUser);
        }

        /*新增用户*/
        @Override
        public int saveData(SysUser sysUser){
            return sysUserMapper.insert(sysUser);
        }
        /*
        编辑用户
         */
        @Override
        public int updateData(SysUser sysUser){
            return sysUserMapper.updateById(sysUser);
        }
        /*
        批量删除
         */
        @Override
        public int batchDelete(List<String> ids){
            return sysUserMapper.deleteBatchIds(ids);
        }
         /**
          * @Author: 田英杰
          * @Description: 新建用户
          * @Date 2020/11/12 11:24
          * @Param  * @param null
          * @return
          * @throws:
          **/
         @Override
        public Boolean creatUser(SysUser sysUser) {
                return sysUserMapper.creatUser(sysUser);
        }


        @Override
        public Boolean isExistUserName(String userName) throws DataAccessException {

            return sysUserMapper.getByUserName(userName) != null ? true : false;
        }


         /**
          * @Author: 田英杰
          * @Description: 根据username查询用户信息
          * @Date 2020/11/12 14:20
          * @Param  * @param null
          * @return
          * @throws:
          **/
         @Override
        public SysUser getUserByUname(String username) throws Exception {
             if (username == null) {
                 throw new Exception("用户信息获取失败");
             }
             SysUser sysUser = sysUserMapper.getUserByUname(username);
             if (sysUser == null){
                 throw new Exception("该用户不存在");
             }
             return sysUser;
         }



         /**
          * @Author: 田英杰
          * @Description: 根据id查询用户信息
          * @Date 2020/11/13 11:30
          * @Param  * @param null
          * @return
          * @throws:
          **/
        public SysUser getUserById(String  id) throws Exception {
            if (id == null || "".equals(id)) {
                throw new Exception("用户信息获取失败");
            }
            SysUser sysUser = sysUserMapper.getUserById(id);
            if (sysUser.getStatus() == 2) {
                throw new Exception("用户被删除");
            }

             return sysUser;
        };


         /**
          * @Author: 田英杰
          * @Description: 根据用户id更新用户信息-高级的
          * @Date 2020/12/8 10:22
          * @Param  * @param null
          * @return
          * @throws:
          **/

        @Override
//        @EnableTransactionManagement
        public void updateUserByUserId(SysUser sysUser)throws Exception {
            if (sysUser.getId() == null) {
                throw new Exception("用户信息获取失败");
            }
            // 原用户信息
            SysUser sysUser_old = sysUserMapper.getUserById(sysUser.getId());
            if (sysUser_old.getStatus() == 2) {
                throw new Exception("用户被删除");
            }
            try {
                sysUserMapper.updateUserByUserId(sysUser);
            } catch (DuplicateKeyException e) {
                e.printStackTrace();
                throw new DuplicateKeyException("用户名或手机号已被占用");
            }
            if (!StringUtils.isEmpty(sysUser.getRoleId())) {
                if (null == sysUserRoleMapper.selectUserRoleByUserId(sysUser.getId())) {
                    sysUserRoleMapper.insert(new SysUserRole(sysUser.getId(), sysUser.getRoleId()));
    //                sysUserRoleMapper.insert(new SysUserRole(sysUser.getId(), sysUser.getRoleId()));
                } else {
                    sysUserMapper.updateUserRoleByUREntity(new SysUserRole(sysUser.getId(), sysUser.getRoleId()));
                }
            }
            if (!StringUtils.isEmpty(sysUser.getOrgId())) {
                if (null == sysOrganizationUserMapper.selectById(sysUser.getId())) {
                    sysOrganizationUserMapper.insert(new SysOrganizationUser(sysUser.getId(), sysUser.getOrgId()));
    //                sysOrganizationUserMapper.insert(new SysOrganizationUser(sysUser.getId(), sysUser.getOrgId()));
                } else {
                    sysUserMapper.updateUserOrganizationByUOEntity(new SysOrganizationUser(sysUser.getId(), sysUser.getOrgId()));

                }
            }
        }




         /**
          * @Author: 田英杰
          * @Description: 根据账号密码获得用户全部信息
          * @Date 2020/11/14 11:26
          * @Param  * @param null
          * @return
          * @throws:
          **/
        public SysUser findUserByNameAndPassword (String uname, String upasswd) throws Exception {
            if (uname == null || "".equals(uname)) {
                throw new Exception("用户信息获取失败");
            }
            if (upasswd == null || "".equals(upasswd)) {
                throw new Exception("用户信息获取失败");
            }
            SysUser sysUser = sysUserMapper.findUserByNameAndPassword(uname, upasswd);
            return sysUser;
        }


         /**
          * @Author: 田英杰
          * @Description: 根据id禁用用户
          * @Date 2020/11/13 11:18
          * @Param  * @param null
          * @return
          * @throws:
          **/
        @Override
        public void disabledById(SysUser sysUser,  String updateUser, Date updateTime) throws Exception {
                 if (sysUser == null) {
                         throw new Exception("用户信息获取失败");
                 }
                 sysUserMapper.disabledById(sysUser.getId(), updateUser, updateTime);
        }

         /**
          * @Author: 田英杰
          * @Description: 根据id解禁用户
          * @Date 2020/11/13 13:16
          * @Param  * @param null
          * @return
          * @throws:
          **/
        @Override
        public void enableById(SysUser sysUser,  String updateUser, Date updateTime) throws Exception {
            if (sysUser == null) {
                throw new Exception("用户信息获取失败");
            }
            sysUserMapper.enableById(sysUser.getId(), updateUser, updateTime);
        }

         /**
          * @Author: 田英杰
          * @Description: 根据id删除用户
          * @Date 2020/11/13 13:58
          * @Param  * @param null
          * @return
          * @throws:
          **/
        @Override
        public void deleteUserById (String id, String updateUser, Date updateTime) throws Exception {
            if (null == id) {
                throw new Exception("用户主键为空！");
            }
            SysUser sysUser = sysUserMapper.getUserById(id);
            if (sysUser.getStatus() == 2) {
                throw new Exception("用户已被删除！");
            }
            sysUserMapper.deleteUserById(id, updateUser, updateTime);
        }

         /**
          * @Author: 田英杰
          * @Description: 根据多个id删除多个用户，逻辑删除
          * @Date 2020/11/13 15:44
          * @Param  * @param null
          * @return
          * @throws:
          **/
         @Override
        public void deleteMUserById(List<String> id, String updateUser, Date updateTime) throws Exception{
            if (null == id){
                throw new Exception("用户主键为空!");
            }
            try {
                sysUserMapper.deleteMUserById(id, updateUser, updateTime);
//                物理删除吧应该是
//                sysUserMapper.deleteBatchIds(id);
            } catch (Exception e) {
                throw new Exception("删除用户失败");
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
    @Override
    public void deleteMUserById_get(List<String> id) throws Exception{
        if (null == id){
            throw new Exception("用户主键为空!");
        }
        try {
            sysUserMapper.deleteMUserById_get(id);
        } catch (Exception e) {
            throw new Exception("删除用户失败");
        }

    }

    /**
     * @Author: sjy
     * @Description: 根据一个id删除一个用户，get方式
     * @Date 2021/1/25 19:50
     * @Param  * @param null
     * @return
     * @throws:
     **/
    @Override
    public void deleteUserById_get(String id) throws Exception{
        if (null == id){
            throw new Exception("用户主键为空!");
        }
        try {
            sysUserMapper.deleteUserById_get(id);
        } catch (Exception e) {
            throw new Exception("删除用户失败");
        }

    }


         /**
          * @Author: 田英杰
          * @Description: 根据id重置密码
          * @Date 2020/11/13 16:05
          * @Param  * @param null
          * @return
          * @throws:
          **/
        public void resetPW(String id, String password, String updateUser, Date updateTime) throws Exception{
            if (null == id){
                throw new Exception("用户主键为空!");
            }
            sysUserMapper.resetPW(id, password, updateUser, updateTime);
        }


    @Override
    public void updatePwd(SysUser user, String oldPwd, String newPwd, String updateUser, Date updateTime) throws Exception {
        if (user == null || oldPwd == null || "".equals(oldPwd) || newPwd == null || "".equals(newPwd)) {
            throw new Exception("用户信息获取失败");
        }

        // 判断原密码是否与数据库中密码相同
        if (!user.getPassword().equals(ShiroUtils.getMd5Pwd(oldPwd))) {
            throw new Exception("旧密码输入有误");
        }

        // 判断修改后的密码时候与原密码相同
        if (user.getPassword().equals(ShiroUtils.getMd5Pwd(newPwd))) {
            throw new Exception("新旧密码一致");
        }

        // 新密码加密后修改密码
        sysUserMapper.updatePwd(ShiroUtils.getMd5Pwd(newPwd), user.getId(), updateUser, updateTime);
    }


    @Override
    public SysUser findUserByUname(String uname) throws Exception {
        if (uname == null || "".equals(uname)) {
            throw new Exception("用户信息获取失败");
        }
        SysUser user = sysUserMapper.findUserByUname(uname);
        // 当前用户名无效
        if (user == null) {
            throw new Exception("该用户不存在");
        }
        return user;
    }





         /**
          * @Author: 田英杰
          * @Description: 用户角色设置，根据用户id和角色id
          * @Date 2020/11/16 10:40
          * @Param  * @param null
          * @return
          * @throws:
          **/
//         @Transactional(rollbackFor = Exception.class)
//         @Override
//         public void updateUserRoleByRoleId(String roleId, List<String> userIds) throws Exception {
////             userRoleMapper.deleteUserRoleByRoleId(roleId);
//             for (String userId : userIds) {
//                 SysUser sysUser = getUserById(userId);
//                 if (null == sysUser) {
//                     throw new Exception("用户不存在，无法绑定角色");
//                 }
////                 UserRole userRole = new UserRole();
////                 userRole.setRoleId(roleId);
////                 userRole.setUserId(userId);
////                 userRoleMapper.insert(userRole);
//             }
//         }

}

