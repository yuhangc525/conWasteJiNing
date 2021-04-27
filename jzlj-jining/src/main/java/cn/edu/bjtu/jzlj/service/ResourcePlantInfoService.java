package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.ResourcePlantInfo;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ResourcePlantInfoService extends IService<ResourcePlantInfo> {
    /* 分页查询*/
    IPage<ResourcePlantInfo> getListByPage(QueryRequest queryRequest, ResourcePlantInfo resourcePlantInfo);

    /*查询全部数据，不分页*/
    List<ResourcePlantInfo> getAllList(QueryRequest queryRequest, ResourcePlantInfo resourcePlantInfo);

    int insertinfo(ResourcePlantInfo resourcePlantInfo);

    int updateinfo(ResourcePlantInfo resourcePlantInfo);

    int deleteByRESOURCEId(Integer resourcePlantId);

    List<ResourcePlantInfo> getResourcePlantInfoByInputName(String inputName);

    int updateReviewStatus(Integer reviewStatus, Integer resourcePlantId);

    List<ResourcePlantInfo> getRESOURCEInfoByRESOURCEId(Integer resourcePlantId);

}
