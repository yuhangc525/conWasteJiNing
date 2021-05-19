package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.dao.SysOrganization;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Date;
import java.util.List;


/**
 * @program: user-service
 * @description:  service 接口
 * @author 田英杰
 * @date 2020-11-16
 */
public interface SysOrganizationService extends IService<SysOrganization> {


     /**
      * @Author: 田英杰
      * @Description: 分页查询组织
      * @Date 2020/11/17 11:22
      * @Param  * @param null
      * @return
      * @throws:
      **/
    IPage<SysOrganization> getOrgListByPage(QueryRequest queryRequest, SysOrganization sysOrganization);



     /**
      * @Author: 田英杰
      * @Description: 不分页查询组织
      * @Date 2020/11/17 11:30
      * @Param  * @param null
      * @return
      * @throws:
      **/
    List<SysOrganization> getAllList(QueryRequest queryRequest, SysOrganization sysOrganization);

     /**
      * @Author: 田英杰
      * @Description: 新建组织
      * @Date 2020/11/16 14:04
      * @Param  * @param null
      * @return
      * @throws:
      **/
    Boolean createOrganization(SysOrganization sysOrganization);


     /**
      * @Author: 田英杰
      * @Description: 根据id获得组织信息
      * @Date 2020/11/16 17:13
      * @Param  * @param null
      * @return
      * @throws:
      **/
    SysOrganization getOrganizationById (String id) throws Exception;


     /**
      * @Author: 田英杰
      * @Description: 根据id修改组织信息
      * @Date 2020/11/16 17:49
      * @Param  * @param null
      * @return
      * @throws:
      **/
    int updateOrgById (SysOrganization sysOrganization);


     /**
      * @Author: 田英杰
      * @Description: 根据id删除组织。逻辑删除
      * @Date 2020/11/16 21:08
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void deleteOrgById(String id, String update_user, Date update_time) throws Exception;

    /**
     * @Author: sjy
     * @Description: 根据id删除组织 --get方式
     * @Date 2021/1/25 16:08
     * @Param  * @param null
     * @return
     * @throws:
     **/
    void deleteOrgById_get(String id) throws Exception;

     /**
      * @Author: 田英杰
      * @Description: 根据id删除多个组织（批量删除），逻辑删除
      * @Date 2020/11/17 16:07
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void deleteMOrgById(List<String> id, String update_user, Date update_time) throws Exception;

    /**
     * @Author: sjy
     * @Description: 根据id删除多个组织（批量删除），get方式
     * @Date 2021/1/25 10:46
     * @Param  * @param null
     * @return
     * @throws:
     **/
    void deleteMOrgById_get(List<String> id) throws Exception;

}
