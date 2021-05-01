package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.CarCompany;
import cn.edu.bjtu.jzlj.dao.ResourcePlantInfo;
import cn.edu.bjtu.jzlj.mapper.ResourcePlantInfoMapper;
import cn.edu.bjtu.jzlj.service.ResourcePlantInfoService;
import cn.edu.bjtu.jzlj.util.CommonUtil;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.SortUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ResourcePlantInfoServiceImpl
 * @Description:
 * @Author 55057
 * @Date 2020/12/8 20:06
 */
@Service
public class ResourcePlantInfoServiceImpl extends ServiceImpl<ResourcePlantInfoMapper, ResourcePlantInfo> implements ResourcePlantInfoService {

    @Autowired
    private ResourcePlantInfoMapper resourcePlantInfoMapper;

    /*分页查询数据*/
    @Override
    public IPage<ResourcePlantInfo> getListByPage(QueryRequest queryRequest, ResourcePlantInfo resourcePlantInfo)
    {
        Page<ResourcePlantInfo> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "resourcePlantId", CommonUtil.ORDER_ASC, true);
        IPage<ResourcePlantInfo> list = resourcePlantInfoMapper.getListByPage(page, resourcePlantInfo);
        return list;
    }

    /*查询全部数据，不分页*/
    @Override
    public List<ResourcePlantInfo> getAllList(QueryRequest queryRequest, ResourcePlantInfo resourcePlantInfo)
    {
        QueryWrapper<ResourcePlantInfo> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "resourcePlantId", CommonUtil.ORDER_ASC, true);
        return resourcePlantInfoMapper.getAllList(queryWrapper, resourcePlantInfo);
    }

    @Override
    public int insertinfo(ResourcePlantInfo resourcePlantInfo){
        return resourcePlantInfoMapper.insertinfo(resourcePlantInfo);
//        return resourcePlantInfoMapper.insert(resourcePlantInfo);
    }

    @Override
    public int updateinfo(ResourcePlantInfo resourcePlantInfo){
        return resourcePlantInfoMapper.updateinfo(resourcePlantInfo);
    }

    @Override
    public int deleteByRESOURCEId(String resourcePlantId){
        return resourcePlantInfoMapper.deleteByRESOURCEId(resourcePlantId);
    };


    @Override
    public List<ResourcePlantInfo> getResourcePlantInfoByInputName(String inputName){
        return resourcePlantInfoMapper.getResourcePlantInfoByInputName(inputName);
    };

    @Override
    public int updateReviewStatus(Integer reviewStatus, Integer resourcePlantId){
        return resourcePlantInfoMapper.updateReviewStatus(reviewStatus,resourcePlantId);
    }

    @Override
    public List<ResourcePlantInfo> getRESOURCEInfoByRESOURCEId(String  resourcePlantId){
        return resourcePlantInfoMapper.getRESOURCEInfoByRESOURCEId(resourcePlantId);
    };

    @Override
    public int saveData(ResourcePlantInfo resourcePlantInfo) {
        return resourcePlantInfoMapper.insert(resourcePlantInfo);
    }

}
