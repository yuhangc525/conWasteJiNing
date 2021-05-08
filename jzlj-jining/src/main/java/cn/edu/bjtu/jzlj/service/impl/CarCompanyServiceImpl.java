package cn.edu.bjtu.jzlj.service.impl;



import cn.edu.bjtu.jzlj.controller.IntakePlantInfoController;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/** @Author: wzj
 * @Description:
 * @Date 2020/11/12 16:46
 **/


@Service
public class CarCompanyServiceImpl extends ServiceImpl<CarCompanyMapper, CarCompany> implements CarCompanyService {

    @Autowired
    CarCompanyMapper carCompanyMapper;


    private static final Logger LOGGER = LoggerFactory.getLogger(IntakePlantInfoController.class);

    @Override
    public IPage<CarCompany> getListByPage(QueryRequest queryRequest) {
        Page<CarCompany> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "carCompanyId", CommonUtil.ORDER_ASC, true);
        IPage<CarCompany> list = carCompanyMapper.getListByPage(page);
        return list;
    }

    public IPage<CarCompany> getListByPage(QueryRequest queryRequest,String inputName) {
        Page<CarCompany> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());

        SortUtil.handlePageSort(queryRequest, page, "carCompanyId", CommonUtil.ORDER_ASC, true);
        IPage<CarCompany> list = carCompanyMapper.getListByPage(page,inputName);
        return list;
    }

    @Override
    public IPage<CarCompany> getListByPage(QueryRequest queryRequest, CarCompany carCompany) {
        Page<CarCompany> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "carCompanyId", CommonUtil.ORDER_ASC, true);
        IPage<CarCompany> list = carCompanyMapper.getListByPage(page, carCompany);
        return list;
    }

    @Override
    public List<CarCompany> getAllList(QueryRequest queryRequest) {
        QueryWrapper<CarCompany> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "carCompanyId", CommonUtil.ORDER_ASC, true);
        return carCompanyMapper.getAllList(queryWrapper, null);
    }


    public List<CarCompany> getAllList(QueryRequest queryRequest,String inputName) {
        QueryWrapper<CarCompany> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("INPUT_NAME",inputName);

        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "carCompanyId", CommonUtil.ORDER_ASC, true);
        return carCompanyMapper.getAllList(queryWrapper, null);
    }

    @Override
    public List<CarCompany> getAllList(QueryRequest queryRequest, CarCompany carCompany) {
        QueryWrapper<CarCompany> queryWrapper = new QueryWrapper<>();
        if(carCompany.getCarCompanyId() != null && !carCompany.getCarCompanyId().equals("")){
            queryWrapper.eq("car_company_id", carCompany.getCarCompanyId());
        }
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "carCompanyId", CommonUtil.ORDER_ASC, true);
        return carCompanyMapper.getAllList(queryWrapper, null);
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
    public int changeReview(Integer carCompanyId, Integer reviewStatus) {
        return carCompanyMapper.changeReview(carCompanyId, reviewStatus);
    }

    @Override
    public List<CarCompany> getInfoByCarCompanyName(String carCompanyName) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("car_company_name", carCompanyName);
        return carCompanyMapper.selectByMap(columnMap);
    }

    @Override
    public String updateOrInsertCarCompany(CarCompany carCompany) {
        List<CarCompany> list;
        if(!"".equals(carCompany.getCarCompanyName())){
            list = getInfoByCarCompanyName(carCompany.getCarCompanyName());
        }else{
            LOGGER.error("intakePlantName为空！Error！");
            return null;
        }
        String id; // 记录的id
        if(!list.isEmpty()){
            // 若数据库中存在记录
            id = list.get(0).getCarCompanyId();
            carCompany.setCarCompanyId(id);
            int row = carCompanyMapper.updateById(carCompany);
        }else{
            // 若数据库中不存在记录
            id = UUID.randomUUID().toString().replaceAll("-","");
            carCompany.setCarCompanyId(id);
            int row = carCompanyMapper.insert(carCompany);
        }
        return id;
    }
}
