package cn.edu.bjtu.jzlj.mapper;


import cn.edu.bjtu.jzlj.dao.CarBelong;
import cn.edu.bjtu.jzlj.dao.CarCompany;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Repository
public interface CarCompanyMapper extends BaseMapper<CarCompany> {
    /**列表查询分页**/
    IPage<CarCompany> getListByPage(Page<CarCompany> page, @Param("inputName") String inputName);

    /*不分页查询*/
    List<CarCompany> getAllList(@Param("ew") QueryWrapper<CarCompany> queryWrapper);

    /**更改车辆公司审核状态**/
    int changeReview(@Param("carCompanyID") Integer carCompanyID, @Param("reviewStatus") Integer reviewStatus);
}
