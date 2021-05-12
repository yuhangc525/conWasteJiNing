package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.PageSource;
import cn.edu.bjtu.jzlj.dao.SysRolePageSource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRolePageSourceMapper extends BaseMapper<SysRolePageSource> {

    void deleteByPageSource(SysRolePageSource pageSource);

    int insertPsource(@Param("sysRolePageSource") SysRolePageSource sysRolePageSource);

    /**  批量删除by 两个主键  */
    void delByRoleIdJoinPsourceIdList(@Param("roleId") String roleId,
                                      @Param("pSourceIdList") List<String> pSourceIdList);
    /**  为指定角色批量关联  */
    void addByyRoleIdJoinPsourceIdList(@Param("roleId") String roleId,
                                       @Param("pSourceIdList") List<String> pSourceIdList);
    void delByRoleId(@Param("roleId") String roleId);
}
