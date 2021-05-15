package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.dao.DuplicateKeyException;

import java.util.Date;
import java.util.List;


/**
 * @program: user-service
 * @description:  service 接口
 * @author ld
 * @date 2020-11-08
 */
public interface SysUserService extends IService<SysUser> {

    /* 分页查询*/
    IPage<SysUser> getListByPage(QueryRequest queryRequest, SysUser sysUser);

    /*查询全部数据，不分页*/
    List<SysUser> getAllList(QueryRequest queryRequest, SysUser sysUser);

    /*新增*/
    int saveData(SysUser sysUser);

    /*更新*/
    int updateData(SysUser sysUser);

    /*批量删除*/
    int batchDelete(List<String> ids);

     /**
      * @Author: 田英杰
      * @Description: 创建用户
      * @Date 2020/11/11 20:37
      * @Param  * @param null
      * @return
      * @throws:
      **/
    Boolean creatUser(SysUser sysUser);

     /**
      * @Author: 田英杰
      * @Description: 根据id查询用户信息
      * @Date 2020/11/13 11:28
      * @Param  * @param null
      * @return
      * @throws:
      **/
     SysUser getUserById(String  id) throws Exception;



      /**
       * @Author: 田英杰
       * @Description: 判断用户名是否存在
       * @Date 2020/11/19 14:33
       * @Param  * @param null
       * @return
       * @throws:
       **/
    Boolean  isExistUserName(String userName);


     /**
      * @Author: 田英杰
      * @Description: 根据用户名查询用户信息
      * @Date 2020/11/12 14:22
      * @Param  * @param null
      * @return
      * @throws:
      **/
     SysUser getUserByUname(String username) throws Exception;


      /**
       * @Author: 田英杰
       * @Description: 根据账号密码获得用户全部信息
       * @Date 2020/11/13 21:58
       * @Param  * @param null
       * @return 
       * @throws:
       **/
    SysUser findUserByNameAndPassword(String uname, String upasswd) throws Exception;


    /**
      * @Author: 田英杰
      * @Description: 根据id禁用用户
      * @Date 2020/11/13 11:18
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void disabledById(SysUser sysUser,  String updateUser, Date updateTime) throws Exception;


     /**
      * @Author: 田英杰
      * @Description: 根据用户id更新用户信息-高级的
      * @Date 2020/12/8 10:48
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void updateUserByUserId(SysUser sysUser)throws Exception;

     /**
      * @Author: 田英杰
      * @Description: 根据id解禁用户
      * @Date 2020/11/13 13:16
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void enableById(SysUser sysUser,  String updateUser, Date updateTime) throws Exception;


     /**
      * @Author: 田英杰
      * @Description: 根据id删除用户，逻辑删除
      * @Date 2020/11/13 13:59
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void deleteUserById (String id, String updateUser, Date updateTime) throws Exception;

     /**
      * @Author: 田英杰
      * @Description: 根据多个id删除多个用户，逻辑删除
      * @Date 2020/11/13 15:44
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void deleteMUserById(List<String> id, String updateUser, Date updateTime) throws Exception;

    /**
     * @Author: sjy
     * @Description: 根据多个id删除多个用户，get方式
     * @Date 2021/1/25 19:50
     * @Param  * @param null
     * @return
     * @throws:
     **/
    void deleteMUserById_get(List<String> id) throws Exception;

    /**
     * @Author: sjy
     * @Description: 根据一个id删除一个用户，get方式
     * @Date 2021/1/25 19:50
     * @Param  * @param null
     * @return
     * @throws:
     **/
    void deleteUserById_get(String id) throws Exception;

     /**
      * @Author: 田英杰
      * @Description: 根据id重置密码
      * @Date 2020/11/13 16:05
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void resetPW(String id, String password, String updateUser, Date updateTime) throws Exception;

    SysUser findUserByUname(String uname) throws Exception;

    void updatePwd(SysUser sysUser, String oldPwd, String newPwd, String updateUser, Date updateTime) throws Exception;
}