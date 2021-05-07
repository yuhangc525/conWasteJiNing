package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.dao.PageSource;
import cn.edu.bjtu.jzlj.vo.UserSearchVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.SQLTransactionRollbackException;
import java.util.Date;
import java.util.List;
import java.util.stream.BaseStream;
import java.util.zip.DataFormatException;

/**
 * @program: pageSource-service
 * @description:  Mapper 接口
 * @author 田英杰
 * @date 2020-11-14
 */

@Repository
public interface PageSourceMapper extends BaseMapper<PageSource> {

     /**
      * @Author: 田英杰
      * @Description: 根据角色id获取资源列表
      * @Date 2020/11/14 12:50
      * @Param  * @param null
      * @return
      * @throws:
      **/
    List<PageSource> getPSourceListByRoleId(@Param("id") String id);


    @Select("select count(1) from sys_role_pagesource where psource_id = #{psourceId}")
    public Integer selectCountforRolepage(@Param("psourceId") String psourceId);


    List<PageSource> getPageSourceListByRoleId(@Param("roleid")String roleid, Page<PageSource> pageBean);

    List<PageSource> getNoPageSourceListByRoleId(@Param("roleid")String roleid, Page<PageSource> pageBean);

    Integer selectManypagesource(@Param("id")List<String> id);

    void deleteManypagesource(@Param("id")List<String> id);


}
