package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.PageSource;
import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * @program: user-service
 * @description:  service 接口
 * @author ld
 * @date 2020-11-08
 */
//@Repository
//@Component
public interface PageSourceService extends IService<PageSource> {


     /**
      * @Author: 田英杰
      * @Description: 根据角色id获取资源列表
      * @Date 2020/11/14 12:22
      * @Param  * @param null
      * @return
      * @throws:
      **/
     List<PageSource> getPSourceListByRoleId(String id) throws Exception;

     void updatepageSource(PageSource pageSource) throws Exception;

     void deletePageSourceById(String id) throws Exception;

     IPage<PageSource> getPageSourceListByRoleId(String roleid, Integer pageNo, Integer pageSize);

     IPage<PageSource> getNoPageSourceListByRoleId(String roleid, Integer pageNo, Integer pageSize);

     void deleteMpagesource(List<String> id) throws Exception;


}
