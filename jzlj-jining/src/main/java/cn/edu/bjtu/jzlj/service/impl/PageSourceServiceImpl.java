package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.dao.PageSource;
import cn.edu.bjtu.jzlj.dao.SysOrganization;
import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.mapper.PageSourceMapper;
import cn.edu.bjtu.jzlj.mapper.SysUserMapper;
import cn.edu.bjtu.jzlj.service.PageSourceService;
import cn.edu.bjtu.jzlj.util.CommonUtil;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.SortUtil;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @program: pageSource-service
 * @description:  service 接口实现类
 * @author 田英杰
 * @date 2020-11-14
 */
@Service
public class PageSourceServiceImpl extends ServiceImpl<PageSourceMapper, PageSource> implements PageSourceService{

    @Autowired
    private PageSourceMapper pageSourceMapper;



     /**
      * @Author: 田英杰
      * @Description: 根据角色id获取资源列表
      * @Date 2020/11/14 12:50
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @Override
    public List<PageSource> getPSourceListByRoleId(String id) throws Exception{
        if (id == null || "".equals(id)) {
            throw new Exception("获取用户角色id失败");
        }
        List<PageSource> list = pageSourceMapper.getPSourceListByRoleId(id);
        return list;
    }

    @Override
    public void updatepageSource(PageSource pageSource) throws Exception {
        //入参校验
        if (null == pageSource) {
            throw new Exception("页面对象为空");
        }
        if (StringUtils.isEmpty(pageSource.getId())
                || StringUtils.isEmpty(pageSource.getSource())
                || StringUtils.isEmpty(pageSource.getBygroup())) {
            throw new Exception("页面信息缺失，无法更新页面");
        }
        //指定id页面资源不存在，抛出异常
        if (!pageSourceExistd(pageSource.getId())) {
            throw new Exception("指定页面不存在");
        }
        try {
            pageSourceMapper.updateById(pageSource);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            throw new Exception("页面名称已被占用");
        }
    }



    @Override
    public void deletePageSourceById(String id) throws Exception {
        if (StringUtils.isEmpty(id)) {
            throw new Exception("页面主键为空");
        }
        //指定id页面资源不存在，抛出异常
        if (!pageSourceExistd(id)) {
            throw new Exception("指定页面不存在");
        }
        if (pageSourceMapper.selectCountforRolepage(id) != 0) {
            throw new Exception("删除页面资源失败,请确认页面与其他业务脱离关联");
        }
        pageSourceMapper.deleteById(id);


    }


    @Override
    public IPage<PageSource> getPageSourceListByRoleId(String roleid, Integer pageNo, Integer pageSize) {
        Page<PageSource> pageBean = new Page<>(pageNo, pageSize);
        List<PageSource> pageSourceList = pageSourceMapper.getPageSourceListByRoleId(roleid, pageBean);
        pageBean.setRecords(pageSourceList);
        return pageBean;
    }

    @Override
    public IPage<PageSource> getNoPageSourceListByRoleId(String roleid, Integer pageNo, Integer pageSize) {
        Page<PageSource> pageBean = new Page<>(pageNo, pageSize);
        List<PageSource> pageSourceList = pageSourceMapper.getNoPageSourceListByRoleId(roleid, pageBean);
        pageBean.setRecords(pageSourceList);
        return pageBean;
    }


    @Override
    public void deleteMpagesource(List<String> id) throws Exception {
        if (null == id) {
            throw new Exception("页面主键为空");
        }
        //根据多个id查询页面个数
        Integer list = pageSourceMapper.selectManypagesource(id);
        if (list != 0) {
            throw new Exception("删除角色失败,请确认角色与其他业务脱离关联");
        }
        pageSourceMapper.deleteManypagesource(id);
    }


    /*分页查询数据*/
    @Override
    public IPage<PageSource> getListByPage(QueryRequest queryRequest, PageSource pageSource)
    {
        Page<PageSource> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "id", CommonUtil.ORDER_ASC, true);
        IPage<PageSource> list = pageSourceMapper.getListByPage(page, pageSource);
        return list;
    }

    /*查询全部数据，不分页*/
    @Override
    public List<PageSource> getAllList(QueryRequest queryRequest, PageSource pageSource)
    {
        QueryWrapper<PageSource> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "id", CommonUtil.ORDER_ASC, true);
        return pageSourceMapper.getAllList(queryWrapper, pageSource);
    }




    public boolean pageSourceExistd(String id) {
        if (null == pageSourceMapper.selectById(id)) {
            return false;
        }
        return true;
    }

    @Override
    public int insertPsource(PageSource pageSource){
//        List list = null;
//        list.add(pageSource.getId());
//        PageSource pageSource1;
//        for(int i=0; i<pageSource.getId().length(); i++){
//            pageSource.getId().
//        }
        int id = 0;
        String[] splitId = pageSource.getId().split(",");
        for(int i=0; i<pageSource.getId().length(); i++){
            PageSource ps = new PageSource();
            ps.setId(splitId[i]);
            ps.setRoleId(pageSource.getRoleId());
            pageSourceMapper.insertPsource(ps);
        }
        return id;
//        return pageSourceMapper.insertPsource(pageSource);
    }

    @Override
    public void deletePageSource(PageSource pageSource) throws Exception {

        pageSourceMapper.deleteByPageSource(pageSource);


    }


    @Override
    public int updateSP (PageSource pageSource) {
        return pageSourceMapper.updatePS(pageSource);
    }
}
