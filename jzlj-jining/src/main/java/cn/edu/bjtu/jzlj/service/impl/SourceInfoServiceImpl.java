package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.SourceInfo;
//import cn.edu.bjtu.jzlj.dao.SourceInfoNew;
import cn.edu.bjtu.jzlj.mapper.SourceInfoMapper;
import cn.edu.bjtu.jzlj.service.SourceInfoService;
import cn.edu.bjtu.jzlj.util.CommonUtil;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.SortUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2020/11/8 18:13
 */
@Service
public class SourceInfoServiceImpl extends ServiceImpl<SourceInfoMapper, SourceInfo> implements SourceInfoService {
    @Autowired

    private SourceInfoMapper sourceInfoMapper;

    /*分页查询数据*/
    @Override
    public IPage<SourceInfo> getListByPage(QueryRequest queryRequest, SourceInfo sourceInfo)
    {
        Page<SourceInfo> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "sourceId", CommonUtil.ORDER_ASC, true);
        IPage<SourceInfo> list = sourceInfoMapper.getListByPage(page, sourceInfo);
        return list;
    }

    /*查询全部数据，不分页*/
    @Override
    public List<SourceInfo> getAllList(QueryRequest queryRequest, SourceInfo sourceInfo)
    {
        QueryWrapper<SourceInfo> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "sourceId", CommonUtil.ORDER_ASC, true);
        return sourceInfoMapper.getAllList(queryWrapper, sourceInfo);
    }
    @Override
    public int saveData(SourceInfo sourceInfo){
        return sourceInfoMapper.insert(sourceInfo);
    }

    @Override
    public int updateData(SourceInfo sourceInfo){
        return sourceInfoMapper.updateById(sourceInfo);
    }

    @Override
    public int batchDelete(List<String> ids){
        return sourceInfoMapper.deleteBatchIds(ids);
    }

    @Override
    public int updateReviewStatus(Integer reviewStatus, String sourceId){
        return sourceInfoMapper.updateReviewStatus(reviewStatus,sourceId);
    }
    @Override
    public List<SourceInfo> getSourceInfoByInputName(String inputName){
        return sourceInfoMapper.getSourceInfoByInputName(inputName);
    };
    @Override
    public List<SourceInfo> getSourceInfoBySourceId(String sourceId){
        return sourceInfoMapper.getSourceInfoBySourceId(sourceId);
    };

}
