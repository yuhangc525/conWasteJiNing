package cn.edu.bjtu.jzlj.mapper;


import cn.edu.bjtu.jzlj.dao.SysOrganization;
import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.vo.UserSearchVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLTransactionRollbackException;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;
/**
 * @program: user-service
 * @description:  Mapper 接口
 * @author 田英杰
 * @date 2020-11-16
 */
@Repository
public interface SysOrganizationMapper extends BaseMapper<SysOrganization> {



     /**
      * @Author: 田英杰
      * @Description: 分页查询组织
      * @Date 2020/11/17 11:19
      * @Param  * @param null
      * @return
      * @throws:
      **/
    IPage<SysOrganization> getOrgListByPage(Page<SysOrganization> page, @Param("sysOrganization") SysOrganization sysOrganization);

     /**
      * @Author: 田英杰
      * @Description: 不分页查询组织
      * @Date 2020/11/17 11:28
      * @Param  * @param null
      * @return
      * @throws:
      **/
    List<SysOrganization> getAllList(@Param("ew") QueryWrapper<SysUser> queryWrapper, @Param("sysOrganization") SysOrganization sysOrganization);


     /**
      * @Author: 田英杰
      * @Description: 新建组织
      * @Date 2020/11/16 14:02
      * @Param  * @param null
      * @return
      * @throws:
      **/
    Boolean createOrganization (SysOrganization sysOrganization);

     /**
      * @Author: 田英杰
      * @Description: 通过id查询组织
      * @Date 2020/11/16 17:31
      * @Param  * @param null
      * @return
      * @throws:
      **/
    SysOrganization getOrganizationById (@Param("orgId") String id);

     /**
      * @Author: 田英杰
      * @Description: 根据id修改组织信息
      * @Date 2020/11/16 18:46
      * @Param  * @param null
      * @return
      * @throws:
      **/
    int updateOrgById (SysOrganization sysOrganization);


     /**
      * @Author: 田英杰
      * @Description: 根据id删除组织，逻辑删除
      * @Date 2020/11/16 21:06
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void deleteOrgById(@Param("orgId") String id, @Param("update_user") String update_user, @Param("update_time") Date update_time);

    /**
     * @Author: sjy
     * @Description: 根据id删除组织--get方式
     * @Date 2021/1/25 16:08
     * @Param  * @param null
     * @return
     * @throws:
     **/
    void deleteOrgById_get(@Param("orgId") String id);

     /**
      * @Author: 田英杰
      * @Description: 根据id删除多个组织（批量删除），逻辑删除
      * @Date 2020/11/17 16:05
      * @Param  * @param null
      * @return
      * @throws:
      **/
    void deleteMOrgById(@Param("id")List<String> id, @Param("update_user") String update_user, @Param("update_time") Date update_time);

    /**
     * @Author: sjy
     * @Description: 根据id删除多个组织（批量删除），get方式
     * @Date 2020/11/17 16:06
     * @Param  * @param null
     * @return
     * @throws:
     **/
    void deleteMOrgById_get(@Param("id")List<String> id);
}
