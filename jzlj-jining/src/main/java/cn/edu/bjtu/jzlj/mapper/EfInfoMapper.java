package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.EFInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EfInfoMapper extends BaseMapper<EFInfo> {

    List<EFInfo> selectByIdAndSOrI(@Param("id") Integer id, @Param("SOrI") String SOrI);

    int insertEfInfo(EFInfo efInfo);

    int deleteByIdAndSOrI(@Param("id") Integer id, @Param("SOrI") String SOrI);

    IPage<EFInfo> getListByPage(Page<EFInfo> page, @Param("efInfo") EFInfo efInfo);

    /*不分页查询*/
    List<EFInfo> getAllList(@Param("ew") QueryWrapper<EFInfo> queryWrapper, @Param("efInfo") EFInfo efInfo);
}
