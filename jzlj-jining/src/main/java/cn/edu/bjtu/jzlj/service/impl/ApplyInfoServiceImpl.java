package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.ApplyInfo;
import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.mapper.ApplyInfoMapper;
import cn.edu.bjtu.jzlj.mapper.IntakePlantInfoMapper;
import cn.edu.bjtu.jzlj.service.ApplyInfoService;
import cn.edu.bjtu.jzlj.service.IntakePlantInfoService;
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
 * @ClassName: ApplyInfoServiceImpl
 * @Description:
 * @Author sjyzj
 * @Date 2021/4/13 11:15
 */
@Service
public class ApplyInfoServiceImpl extends ServiceImpl<ApplyInfoMapper, ApplyInfo> implements ApplyInfoService {
    @Autowired
    private ApplyInfoMapper applyInfoMapper;

    /*分页查询数据*/
    @Override
    public IPage<ApplyInfo> getListByPage(QueryRequest queryRequest, ApplyInfo applyInfo)
    {
        Page<ApplyInfo> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "applyId", CommonUtil.ORDER_ASC, true);
        IPage<ApplyInfo> list = applyInfoMapper.getListByPage(page, applyInfo);
        return list;
    }

    /*查询全部数据，不分页*/
    @Override
    public List<ApplyInfo> getAllList(QueryRequest queryRequest, ApplyInfo applyInfo)
    {
        QueryWrapper<ApplyInfo> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "applyId", CommonUtil.ORDER_ASC, true);
        return applyInfoMapper.getAllList(queryWrapper, applyInfo);
    }

    @Override
    public int insertinfo(ApplyInfo applyInfo){

        return applyInfoMapper.insertinfo(applyInfo);
    }

    @Override
    public int updateinfo(ApplyInfo applyInfo){

        return applyInfoMapper.updateinfo(applyInfo);
    }

    @Override
    public int deleteByApply_id(String applyId){
        return applyInfoMapper.deleteByApply_id(applyId);
    };

    @Override
    public List<ApplyInfo> getApplyInfoByApplyId(String applyId){
        return applyInfoMapper.getApplyInfoByApplyId(applyId);
    };
}
