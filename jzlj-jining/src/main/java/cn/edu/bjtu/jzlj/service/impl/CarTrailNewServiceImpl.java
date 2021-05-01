package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.CarTrailNew;
import cn.edu.bjtu.jzlj.mapper.CarTrailNewMapper;
import cn.edu.bjtu.jzlj.service.CarTrailNewService;
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
 * @program: jzlj
 * @description: part of Car_Trail_Info, faster
 * @author: wzj
 * @create: 2020-12-23 15:34
 **/

@Service
public class CarTrailNewServiceImpl extends ServiceImpl<CarTrailNewMapper, CarTrailNew> implements CarTrailNewService {

    @Autowired
    CarTrailNewMapper carTrailNewMapper;

    @Override
    public IPage<CarTrailNew> getListByPage(QueryRequest queryRequest, String sTime, String eTime, CarTrailNew carTrail) {
        Page<CarTrailNew> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "inputTime", CommonUtil.ORDER_ASC, true);
        IPage<CarTrailNew> list = carTrailNewMapper.getListByPage(page, sTime,eTime, carTrail);
        return list;
    }

    @Override
    public List<CarTrailNew> getAllList(QueryRequest queryRequest, String sTime, String eTime, CarTrailNew carTrail) {
        QueryWrapper<CarTrailNew> queryWrapper = new QueryWrapper<>();
//        queryWrapper.ge("INPUT_TIME",sTime).le("INPUT_TIME",eTime);
        // 这个地方Wrapper的使用和前面的where有冲突，故时间限制使用了xml的if判断。
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "inputTime", CommonUtil.ORDER_ASC, true);
        return carTrailNewMapper.getAllList(queryWrapper,sTime,eTime, carTrail);
    }

    @Override
    public IPage<CarTrailNew> getDynamicTrail(String carId, int id) {
        Page<CarTrailNew> page = new Page<>(id, 1);
        QueryWrapper<CarTrailNew> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CAR_ID",carId);
        IPage<CarTrailNew> list = carTrailNewMapper.selectPage(page, queryWrapper);
        return list;
    }
}
