package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.ApplyInfo;
import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyInfoMapper extends BaseMapper<ApplyInfo> {

    IPage<ApplyInfo> getListByPage(Page<ApplyInfo> page, @Param("applyInfo") ApplyInfo applyInfo);

    /*不分页查询*/
    List<ApplyInfo> getAllList(@Param("ew") QueryWrapper<ApplyInfo> queryWrapper, @Param("applyInfo") ApplyInfo applyInfo);


    int insertinfo(ApplyInfo applyInfo);

    int updateinfo(ApplyInfo applyInfo);

    int deleteByApply_id(@Param("applyId") String applyId);

    List<ApplyInfo> getApplyInfoByApplyId(@Param("applyId") String applyId);

    /* 多表查询，获取有意义字段 */
    IPage<ApplyInfo> getListByPageMore(Page<ApplyInfo> page, ApplyInfo applyInfo);

    /* 多表查询，获取有意义字段 */
    List<ApplyInfo> getAllListMore(QueryWrapper<ApplyInfo> queryWrapper, ApplyInfo applyInfo);
}
