package cn.edu.bjtu.jzlj.service.impl;


import cn.edu.bjtu.jzlj.dao.CarCompany;
import cn.edu.bjtu.jzlj.mapper.CarCompanyMapper;
import cn.edu.bjtu.jzlj.service.CarCompanyService;
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
public class CarCompanyServiceImpl extends ServiceImpl<CarCompanyMapper, CarCompany> implements CarCompanyService {

    @Autowired
    CarCompanyMapper carCompanyMapper;

    @Override
    public IPage<CarCompany> getListByPage(QueryRequest queryRequest) {
        Page<CarCompany> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "carCompanyID", CommonUtil.ORDER_ASC, true);
        IPage<CarCompany> list = carCompanyMapper.getListByPage(page,null);
        return list;
    }

    public IPage<CarCompany> getListByPage(QueryRequest queryRequest,String inputName) {
        Page<CarCompany> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "carCompanyID", CommonUtil.ORDER_ASC, true);
        IPage<CarCompany> list = carCompanyMapper.getListByPage(page,inputName);
        return list;
    }

    @Override
    public List<CarCompany> getAllList(QueryRequest queryRequest) {
        QueryWrapper<CarCompany> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "carCompanyID", CommonUtil.ORDER_ASC, true);
        return carCompanyMapper.getAllList(queryWrapper);
    }


    public List<CarCompany> getAllList(QueryRequest queryRequest,String inputName) {
        QueryWrapper<CarCompany> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("INPUT_NAME",inputName);
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "carCompanyID", CommonUtil.ORDER_ASC, true);
        return carCompanyMapper.getAllList(queryWrapper);
    }

    @Override
    public int saveData(CarCompany carCompany) {
        return carCompanyMapper.insert(carCompany);
    }

    @Override
    public int updateData(CarCompany carCompany) {
        return carCompanyMapper.updateById(carCompany);
    }

    @Override
    public int batchDelete(List<String> ids) {
        return carCompanyMapper.deleteBatchIds(ids);
    }

    @Override
    public int changeReview(Integer carCompanyID, Integer reviewStatus) {
        return carCompanyMapper.changeReview(carCompanyID, reviewStatus);
    }
}
