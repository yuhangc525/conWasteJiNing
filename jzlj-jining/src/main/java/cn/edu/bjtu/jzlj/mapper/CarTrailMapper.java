package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.CarCompany;
import cn.edu.bjtu.jzlj.dao.CarTrail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CarTrailMapper extends BaseMapper<CarTrail> {


    List<CarTrail> getDynamicTrail(@Param("CAR_ID") String CAR_ID, @Param("limit") int limit);

    /**列表查询分页**/
    IPage<CarTrail> getListByPage(Page<CarTrail> page,String TIME_STA, String TIME_END, @Param("carTrail") CarTrail carTrail);

    /*不分页查询*/
    List<CarTrail> getAllList(@Param("ew") QueryWrapper<CarTrail> queryWrapper,String TIME_STA, String TIME_END, @Param("carTrail") CarTrail carTrail);


}
