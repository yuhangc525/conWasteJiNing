package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.controller.IntakePlantInfoController;
import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.dao.SourceInfo;
import cn.edu.bjtu.jzlj.mapper.SourceInfoMapper;
import cn.edu.bjtu.jzlj.service.SourceInfoService;
import cn.edu.bjtu.jzlj.util.CommonUtil;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.SortUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(IntakePlantInfoController.class);

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
        if(sourceInfo.getSourceId().equals("")){
            sourceInfo.setSourceId(UUID.randomUUID().toString().replaceAll("-",""));
        }
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
    }

    @Override
    public List<SourceInfo> getInfoBySourceName(String sourceName) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("source_name", sourceName);
        return sourceInfoMapper.selectByMap(columnMap);
    }

    @Override
    public String updateOrInsertSource(SourceInfo sourceInfo) {
        List<SourceInfo> list;
        if(!"".equals(sourceInfo.getSourceName())){
            list = getInfoBySourceName(sourceInfo.getSourceName());
        }else{
            LOGGER.error("intakePlantName为空！Error！");
            return null;
        }
        String id; // 记录的id
        if(!list.isEmpty()){
            // 若数据库中存在记录
            id = list.get(0).getSourceId();
            sourceInfo.setSourceId(id);
            int row = sourceInfoMapper.updateById(sourceInfo);
        }else{
            // 若数据库中不存在记录
            id = UUID.randomUUID().toString().replaceAll("-","");
            sourceInfo.setSourceId(id);
            int row = sourceInfoMapper.insert(sourceInfo);
        }
        return id;
    }

    ;

}
