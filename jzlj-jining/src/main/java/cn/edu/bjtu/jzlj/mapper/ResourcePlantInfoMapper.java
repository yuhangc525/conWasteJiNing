package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.ResourcePlantInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourcePlantInfoMapper extends BaseMapper<ResourcePlantInfo> {

    IPage<ResourcePlantInfo> getListByPage(Page<ResourcePlantInfo> page, @Param("resourcePlantInfo") ResourcePlantInfo resourcePlantInfo);

    /*不分页查询*/
    List<ResourcePlantInfo> getAllList(@Param("ew") QueryWrapper<ResourcePlantInfo> queryWrapper, @Param("resourcePlantInfo") ResourcePlantInfo resourcePlantInfo);

    int insertinfo(ResourcePlantInfo resourcePlantInfo);

    int updateinfo(ResourcePlantInfo resourcePlantInfo);

    int deleteByRESOURCEId(@Param("resourcePlantId") Integer resourcePlantId);

    List<ResourcePlantInfo> getResourcePlantInfoByInputName(@Param("inputName") String inputName);

    int updateReviewStatus(@Param("reviewStatus") Integer reviewStatus,
                           @Param("resourcePlantId") Integer resourcePlantId);


    List<ResourcePlantInfo> getRESOURCEInfoByRESOURCEId(@Param("resourcePlantId") Integer resourcePlantId);

}