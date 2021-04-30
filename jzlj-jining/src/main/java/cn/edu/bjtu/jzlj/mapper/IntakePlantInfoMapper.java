package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IntakePlantInfoMapper extends BaseMapper<IntakePlantInfo> {
    List<IntakePlantInfo> getIntakePlantInfoByInputName(@Param("inputName") String inputName);

    int insertinfo(IntakePlantInfo intakePlantInfo);

    int updateinfo(IntakePlantInfo intakePlantInfo);

    int deleteByINTAKE_PLANT_ID(@Param("intakePlantId") String intakePlantId);

    IPage<IntakePlantInfo> getListByPage(Page<IntakePlantInfo> page, @Param("intakePlantInfo") IntakePlantInfo intakePlantInfo);

    /*不分页查询*/
    List<IntakePlantInfo> getAllList(@Param("ew") QueryWrapper<IntakePlantInfo> queryWrapper, @Param("intakePlantInfo") IntakePlantInfo intakePlantInfo);

    int updateReviewStatus(@Param("reviewStatus") Integer reviewStatus,
                           @Param("intakePlantId") String intakePlantId);


    List<IntakePlantInfo> getIntakePlantInfoByINTakePlantID(@Param("intakePlantId") String intakePlantId);

    int updateInfoByApplyInfo(IntakePlantInfo intakePlantInfo);

}
