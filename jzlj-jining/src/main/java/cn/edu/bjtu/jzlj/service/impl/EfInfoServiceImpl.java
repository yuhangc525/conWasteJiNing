package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.EFInfo;
import cn.edu.bjtu.jzlj.mapper.EfInfoMapper;
import cn.edu.bjtu.jzlj.service.EfInfoService;
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
 * @ClassName: EfInfoServiceImpl
 * @Description:
 * @Author 55057
 * @Date 2020/12/8 9:38
 */
@Service
public class EfInfoServiceImpl extends ServiceImpl<EfInfoMapper, EFInfo> implements EfInfoService {
    @Autowired
    private EfInfoMapper efInfoMapper;

    /*分页查询数据*/
    @Override
    public IPage<EFInfo> getListByPage(QueryRequest queryRequest, EFInfo efInfo)
    {
        Page<EFInfo> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "efId", CommonUtil.ORDER_ASC, true);
        IPage<EFInfo> list = efInfoMapper.getListByPage(page, efInfo);
        return list;
    }

    /*查询全部数据，不分页*/
    @Override
    public List<EFInfo> getAllList(QueryRequest queryRequest, EFInfo efInfo)
    {
        QueryWrapper<EFInfo> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "efId", CommonUtil.ORDER_ASC, true);
        return efInfoMapper.getAllList(queryWrapper, efInfo);
    }


    @Override
    public List<EFInfo> selectByIdAndSOrI(Integer id, String SOrI){
        return efInfoMapper.selectByIdAndSOrI(id,SOrI);
    };

    @Override
    public int insertEfInfo(EFInfo efInfo){
        return efInfoMapper.insertEfInfo(efInfo);
    };

    @Override
    public int deleteByIdAndSOrI(Integer id, String SOrI){
        return efInfoMapper.deleteByIdAndSOrI(id,SOrI);
    };
}
