package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.PageSource;
import cn.edu.bjtu.jzlj.dao.SysRolePageSource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysRolePageSourceService extends IService<SysRolePageSource> {
//    int insertPsource(SysRolePageSource sysRolePageSource);
    /**   指定roleId与psourceIds集合建立关联  */
    void addByRoleIdJoinPsourceIdList(String roleId, List<String> pSourceIdList) throws Exception;

    /**  批量删除by 两个主键  */
    void delByRoleIdJoinPsourceIdList(String roleId, List<String> pSourceIdList) throws Exception;

    /** 删除  */
    void delByRoleId(String roleId) throws Exception;
}
