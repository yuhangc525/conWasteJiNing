package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.PageSource;
import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.mapper.PageSourceMapper;
import cn.edu.bjtu.jzlj.mapper.SysUserMapper;
import cn.edu.bjtu.jzlj.service.PageSourceService;
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
}
