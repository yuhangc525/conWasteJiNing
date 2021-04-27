package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.CarCompany;
import cn.edu.bjtu.jzlj.dao.CarTrail;
import cn.edu.bjtu.jzlj.mapper.CarTrailMapper;
import cn.edu.bjtu.jzlj.service.CarTrailService;
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

/** @Author: wzj
 * @Description:
 * @Date 2020/11/12 16:46
 **/

@Service
public class CarTrailServiceImpl extends ServiceImpl<CarTrailMapper, CarTrail> implements CarTrailService {

    @Autowired
    CarTrailMapper carTrailMapper;

    @Override
    public IPage<CarTrail> getListByPage(QueryRequest queryRequest,String sTime, String eTime, CarTrail carTrail) {
        Page<CarTrail> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "inputTime", CommonUtil.ORDER_ASC, true);
        IPage<CarTrail> list = carTrailMapper.getListByPage(page, sTime,eTime, carTrail);
        return list;
    }

    @Override
    public List<CarTrail> getAllList(QueryRequest queryRequest, String sTime, String eTime, CarTrail carTrail) {
        QueryWrapper<CarTrail> queryWrapper = new QueryWrapper<>();
//        queryWrapper.ge("INPUT_TIME",sTime).le("INPUT_TIME",eTime);
        // 这个地方Wrapper的使用和前面的where有冲突，故时间限制使用了xml的if判断。
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "inputTime", CommonUtil.ORDER_ASC, true);
        return carTrailMapper.getAllList(queryWrapper,sTime,eTime, carTrail);
    }

    @Override
    public IPage<CarTrail> getDynamicTrail(String carId, int id) {
        Page<CarTrail> page = new Page<>(id, 1);
        QueryWrapper<CarTrail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("CAR_ID",carId);
        IPage<CarTrail> list = carTrailMapper.selectPage(page, queryWrapper);
        return list;
    }


}
