package cn.edu.bjtu.jzlj.mapper;


import cn.edu.bjtu.jzlj.dao.SysOrganizationUser;
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
import java.util.zip.DataFormatException;

/**
 * @program: user-service
 * @description:  Mapper 接口
 * @author ld
 * @date 2020-11-19
 */
@Repository
public interface SysOrganizationUserMapper extends BaseMapper<SysOrganizationUser>{

    /**插入信息**/
    void insert_org(@Param("sysUserOrganization") SysOrganizationUser sysOrganizationUser);


}
