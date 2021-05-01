package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.controller.IntakePlantInfoController;
import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.mapper.IntakePlantInfoMapper;
import cn.edu.bjtu.jzlj.service.IntakePlantInfoService;
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

import java.util.*;

/**
 * @ClassName: IntakePlantInfoService
 * @Description:
 * @Author 55057
 * @Date 2020/12/8 9:52
 */
@Service
public class IntakePlantInfoServiceImpl extends ServiceImpl<IntakePlantInfoMapper, IntakePlantInfo> implements IntakePlantInfoService {
    @Autowired
    private IntakePlantInfoMapper intakePlantInfoMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(IntakePlantInfoController.class);

    /*分页查询数据*/
    @Override
    public IPage<IntakePlantInfo> getListByPage(QueryRequest queryRequest, IntakePlantInfo intakePlantInfo)
    {
        Page<IntakePlantInfo> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "id", CommonUtil.ORDER_ASC, true);
        IPage<IntakePlantInfo> list = intakePlantInfoMapper.getListByPage(page, intakePlantInfo);
        return list;
    }

    /*查询全部数据，不分页*/
    @Override
    public List<IntakePlantInfo> getAllList(QueryRequest queryRequest, IntakePlantInfo intakePlantInfo)
    {
        QueryWrapper<IntakePlantInfo> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "id", CommonUtil.ORDER_ASC, true);
        return intakePlantInfoMapper.getAllList(queryWrapper, intakePlantInfo);
    }


    @Override
    public List<IntakePlantInfo> getIntakePlantInfoByInputName(String inputName){
        return intakePlantInfoMapper.getIntakePlantInfoByInputName(inputName);
    };

    @Override
    public int insertinfo(IntakePlantInfo intakePlantInfo){
        return intakePlantInfoMapper.insertinfo(intakePlantInfo);
    }

    @Override
    public int updateinfo(IntakePlantInfo intakePlantInfo){
    return intakePlantInfoMapper.updateinfo(intakePlantInfo);
}

    @Override
    public int deleteByINTAKE_PLANT_ID(String intakePlantId){
        return intakePlantInfoMapper.deleteByINTAKE_PLANT_ID(intakePlantId);
    };

    @Override
    public int updateReviewStatus(Integer reviewStatus, String intakePlantId){
        return intakePlantInfoMapper.updateReviewStatus(reviewStatus,intakePlantId);
    }

    @Override
    public List<IntakePlantInfo> getIntakePlantInfoByINTakePlantID(String intakePlantId){
        return intakePlantInfoMapper.getIntakePlantInfoByINTakePlantID(intakePlantId);
    }

    @Override
    public List<IntakePlantInfo> getInfoByIntakePlantName(String intakePlantName){
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("intake_plant_name", intakePlantName);
        return intakePlantInfoMapper.selectByMap(columnMap);
    }

    @Override
    public String updateOrInsertIntake(IntakePlantInfo intakePlantInfo) {
        List<IntakePlantInfo> list;
        if(!"".equals(intakePlantInfo.getIntakePlantName())){
            list = getInfoByIntakePlantName(intakePlantInfo.getIntakePlantName());
        }else{
            LOGGER.error("intakePlantName为空！Error！");
            return null;
        }
        String id; // 记录的id
        if(!list.isEmpty()){
            // 若数据库中存在记录
            id = list.get(0).getIntakePlantId();
            intakePlantInfo.setIntakePlantId(id);
            int row = intakePlantInfoMapper.updateById(intakePlantInfo);
        }else{
            // 若数据库中不存在记录
            id = UUID.randomUUID().toString().replaceAll("-","");
            intakePlantInfo.setIntakePlantId(id);
            int row = intakePlantInfoMapper.insert(intakePlantInfo);
        }
        return id;
    }
}
