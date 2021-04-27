package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.dao.SysOrganization;
import cn.edu.bjtu.jzlj.mapper.SysOrganizationMapper;
import cn.edu.bjtu.jzlj.mapper.SysUserMapper;
import cn.edu.bjtu.jzlj.service.SysOrganizationService;
import cn.edu.bjtu.jzlj.service.SysUserService;
import cn.edu.bjtu.jzlj.util.CommonUtil;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.SortUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * @program: user-service
 * @description:  service 接口实现类
 * @author 田英杰
 * @date 2020-11-16
 */

@Service
public class SysOrganizationServiceImpl extends ServiceImpl<SysOrganizationMapper, SysOrganization> implements SysOrganizationService {

    @Autowired
    private SysOrganizationMapper sysOrganizationMapper;



     /**
      * @Author: 田英杰
      * @Description: 分页查询组织
      * @Date 2020/11/17 11:19
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Override
    public IPage<SysOrganization> getOrgListByPage(QueryRequest queryRequest, SysOrganization sysOrganization)
    {
        Page<SysOrganization> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "organizationName", CommonUtil.ORDER_ASC, true);
        IPage<SysOrganization> list = sysOrganizationMapper.getOrgListByPage(page, sysOrganization);
        return list;
    }

     /**
      * @Author: 田英杰
      * @Description: 不分页查询组织
      * @Date 2020/11/17 11:29
      * @Param  * @param null
      * @return
      * @throws:
      **/
    /*查询全部数据，不分页*/
    @Override
    public List<SysOrganization> getAllList(QueryRequest queryRequest, SysOrganization sysOrganization)
    {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "organization_name", CommonUtil.ORDER_ASC, true);
        return sysOrganizationMapper.getAllList(queryWrapper, sysOrganization);
    }


     /**
      * @Author: 田英杰
      * @Description: 新建组织
      * @Date 2020/11/16 14:04
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Override
    public Boolean createOrganization(SysOrganization sysOrganization) {
        return sysOrganizationMapper.createOrganization(sysOrganization);
    }


     /**
      * @Author: 田英杰
      * @Description: 根据id获得组织信息
      * @Date 2020/11/16 17:12
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Override
    public SysOrganization getOrganizationById (String id) throws Exception {
        if (id == null || "".equals(id)) {
            throw new Exception("组织信息获取失败");
        }
        SysOrganization sysOrganization = sysOrganizationMapper.getOrganizationById(id);
        if (sysOrganization.getStatus() == 0) {
            throw new Exception("组织被删除");
        }
        return sysOrganization;
    }


     /**
      * @Author: 田英杰
      * @Description: 根据id修改组织信息
      * @Date 2020/11/16 17:48
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Override
    public SysOrganization updateOrgById (SysOrganization sysOrganization) {
//        SysOrganization sysOrganization1 = sysOrganizationMapper.getOrganizationById(sysOrganization.getId());
//        if (sysOrganization1 == null) {
//            throw new Exception("要修改的组织不存在");
//        }
//        if (sysOrganization1.getStatus() == 0) {
//            throw new Exception("要修改的组织被删除");
//        }
        return sysOrganizationMapper.updateOrgById(sysOrganization);
    }

     /**
      * @Author: 田英杰
      * @Description: 根据id删除组织。逻辑删除
      * @Date 2020/11/16 21:06
      * @Param  * @param null
      * @return
      * @throws:
      **/
    public void deleteOrgById(String id, String update_user, Date update_time) throws Exception {
        if (null == id) {
            throw new Exception("组织主键为空！");
        }
        sysOrganizationMapper.deleteOrgById(id, update_user, update_time);
    }

    /**
     * @Author: sjy
     * @Description: 根据id删除组织--get方式
     * @Date 2020/11/16 21:06
     * @Param  * @param null
     * @return
     * @throws:
     **/
    public void deleteOrgById_get(String id) throws Exception {
        if (null == id) {
            throw new Exception("组织主键为空！");
        }
        sysOrganizationMapper.deleteOrgById_get(id);
    }

     /**
      * @Author: 田英杰
      * @Description: 根据id删除多个组织（批量删除），逻辑删除
      * @Date 2020/11/17 16:06
      * @Param  * @param null
      * @return
      * @throws:
      **/
    public void deleteMOrgById(List<String> id, String update_user, Date update_time) throws Exception {
        if (null == id){
            throw new Exception("组织主键为空!");
        }
        try {
            sysOrganizationMapper.deleteMOrgById(id, update_user, update_time);
//                物理删除吧应该是
//                sysUserMapper.deleteBatchIds(id);
        } catch (Exception e) {
            throw new Exception("删除组织失败");
        }

    }

    /**
     * @Author: sjy
     * @Description: 根据id删除多个组织（批量删除），get方式
     * @Date 2020/11/17 16:06
     * @Param  * @param null
     * @return
     * @throws:
     **/
    public void deleteMOrgById_get(List<String> id) throws Exception {
        if (null == id){
            throw new Exception("组织主键为空!");
        }
        try {
            sysOrganizationMapper.deleteMOrgById_get(id);
        } catch (Exception e) {
            throw new Exception("删除组织失败");
        }

    }
}
