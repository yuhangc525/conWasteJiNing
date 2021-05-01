package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.SourceInfo;
//import cn.edu.bjtu.jzlj.dao.SourceInfoNew;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2020/11/8 18:38
 */
@Repository
public interface SourceInfoMapper extends BaseMapper<SourceInfo> {
    /**列表查询分页**/
    IPage<SourceInfo> getListByPage(Page<SourceInfo> page, @Param("sourceInfo") SourceInfo sourceInfo);

    /*不分页查询*/
    List<SourceInfo> getAllList(@Param("ew") QueryWrapper<SourceInfo> queryWrapper,
                                @Param("sourceInfo") SourceInfo sourceInfo);

    int updateReviewStatus(@Param("reviewStatus") Integer reviewStatus,
                           @Param("sourceId") String sourceId);
    /*根据录入姓名*/
    List<SourceInfo> getSourceInfoByInputName(@Param("inputName") String inputName);
    /*根据ID查询*/
    List<SourceInfo> getSourceInfoBySourceId(@Param("sourceId") String sourceId);
}
