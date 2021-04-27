package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.CarBelong;
import cn.edu.bjtu.jzlj.dao.SysUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarBelongMapper extends BaseMapper<CarBelong> {
    /**列表查询分页**/
    IPage<CarBelong> getListByPage(Page<CarBelong> page, @Param("carBelong") CarBelong carBelong);

    /*不分页查询*/
    List<CarBelong> getAllList(@Param("ew") QueryWrapper<CarBelong> queryWrapper, @Param("carBelong") CarBelong carBelong);
}
