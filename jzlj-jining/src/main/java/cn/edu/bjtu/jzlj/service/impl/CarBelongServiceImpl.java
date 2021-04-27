package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.CarBelong;
import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.mapper.CarBelongMapper;
import cn.edu.bjtu.jzlj.service.CarBelongService;
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
import java.util.Map;

/** @Author: wzj
 * @Description:
 * @Date 2020/11/9 9:57
 **/


@Service
public class CarBelongServiceImpl extends ServiceImpl<CarBelongMapper, CarBelong> implements CarBelongService {

    @Autowired
    CarBelongMapper carBelongMapper;


    /*分页查询数据*/
    @Override
    public IPage<CarBelong> getListByPage(QueryRequest queryRequest, CarBelong carBelong) {
        Page<CarBelong> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "CAR_NAME", CommonUtil.ORDER_ASC, true);
        IPage<CarBelong> list = carBelongMapper.getListByPage(page, carBelong);
        return list;
    }

    /*查询全部数据，不分页*/
    @Override
    public List<CarBelong> getAllList(QueryRequest queryRequest, CarBelong carBelong) {
        QueryWrapper<CarBelong> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "CAR_NAME", CommonUtil.ORDER_ASC, true);
        return carBelongMapper.getAllList(queryWrapper, carBelong);

    }


    @Override
    public int saveData(CarBelong carBelong) {
        return carBelongMapper.insert(carBelong);
    }

    @Override
    public int updateData(CarBelong carBelong) {
        return carBelongMapper.updateById(carBelong);
    }

    @Override
    public int batchDelete(List<String> ids) {
        return carBelongMapper.deleteBatchIds(ids);
    }
}
