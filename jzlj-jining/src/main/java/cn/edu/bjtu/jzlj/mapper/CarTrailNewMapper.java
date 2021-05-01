package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.CarTrailNew;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarTrailNewMapper extends BaseMapper<CarTrailNew> {

    List<CarTrailNew> getDynamicTrail(@Param("CAR_ID") String CAR_ID, @Param("limit") int limit);

    /**列表查询分页**/
    IPage<CarTrailNew> getListByPage(Page<CarTrailNew> page, String TIME_STA, String TIME_END, @Param("carTrail") CarTrailNew carTrail);

    /*不分页查询*/
    List<CarTrailNew> getAllList(@Param("ew") QueryWrapper<CarTrailNew> queryWrapper, String TIME_STA, String TIME_END, @Param("carTrail") CarTrailNew carTrail);

}
