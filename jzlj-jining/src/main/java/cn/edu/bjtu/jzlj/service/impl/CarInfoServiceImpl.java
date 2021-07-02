package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.CarCompany;
import cn.edu.bjtu.jzlj.dao.CarInfo;
import cn.edu.bjtu.jzlj.mapper.CarInfoMapper;
import cn.edu.bjtu.jzlj.service.CarInfoService;
import cn.edu.bjtu.jzlj.util.CommonUtil;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import cn.edu.bjtu.jzlj.util.SortUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: jzlj
 * @description:
 * @author: wzj
 * @create: 2020-12-13 18:08
 **/

@Service
public class CarInfoServiceImpl extends ServiceImpl<CarInfoMapper, CarInfo> implements CarInfoService {

    @Autowired
    CarInfoMapper carInfoMapper;

    @Override
    public IPage<CarInfo> getListByPage(QueryRequest queryRequest) {
        Page<CarInfo> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "carId", CommonUtil.ORDER_ASC, true);
        IPage<CarInfo> list = carInfoMapper.getListByPage(page,null,null);
        return list;
    }

    @Override
    public IPage<CarInfo> getListByPage(QueryRequest queryRequest, String inputName, String company) {
        Page<CarInfo> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "carId", CommonUtil.ORDER_ASC, true);
        IPage<CarInfo> list = carInfoMapper.getListByPage(page,inputName, company);
        return list;
    }

    @Override
    public IPage<CarInfo> getListByPage(QueryRequest queryRequest, String terminalId) {
        Page<CarInfo> page = new Page<>(queryRequest.getPageNo(), queryRequest.getPageSize());
        SortUtil.handlePageSort(queryRequest, page, "carId", CommonUtil.ORDER_ASC, true);
        IPage<CarInfo> list = carInfoMapper.getListByPage(page, terminalId);
        return list;
    }


    @Override
    public List<CarInfo> getAllList(QueryRequest queryRequest) {
        QueryWrapper<CarInfo> queryWrapper = new QueryWrapper<>();
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "carId", CommonUtil.ORDER_ASC, true);
        return carInfoMapper.getAllList(queryWrapper);
    }

    @Override
    public List<CarInfo> getAllList(QueryRequest queryRequest, String inputName, String company) {
        QueryWrapper<CarInfo> queryWrapper = new QueryWrapper<>();
        if(inputName != null){
            queryWrapper.eq("INPUT_NAME",inputName);
        };
        if(company != null){
            queryWrapper.eq("COMPANY",company);
        }
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "carId", CommonUtil.ORDER_ASC, true);
        return carInfoMapper.getAllList(queryWrapper);
    }

    @Override
    public List<CarInfo> getAllList(QueryRequest queryRequest, String terminalId) {
        QueryWrapper<CarInfo> queryWrapper = new QueryWrapper<>();
        if(terminalId != null){
            queryWrapper.eq("terminal_id", terminalId);
        }
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "carId", CommonUtil.ORDER_ASC, true);
        return carInfoMapper.getAllList(queryWrapper);
    }

    @Override
    public int getNum(String inputName) {
        return carInfoMapper.getNum(inputName);
    }

    @Override
    public int saveData(CarInfo carInfo) {
        return carInfoMapper.insert(carInfo);
    }

    @Override
    public void saveDataByBatch(List<CarInfo> carInfos) {
        for(int i = 0; i < carInfos.size(); i++){
            carInfoMapper.insert(carInfos.get(i));
        }
    }

    @Override
    public int updateData(CarInfo carInfo) {
        return carInfoMapper.updateById(carInfo);
    }

    @Override
    public int batchDelete(List<String> ids) {
        return carInfoMapper.deleteBatchIds(ids);
    }

    @Override
    public int changeReview(String carId, Integer reviewStatus) {
        return carInfoMapper.changeReview(carId, reviewStatus);
    }

    @Override
    public String getTerminalIdByCarNo(String carNo) {
        return carInfoMapper.getTerminalIdByCarNo(carNo);
    }


    @Override
    public String getCarNoByTerminalId(String TerminalId) {
        return carInfoMapper.getCarNoByTerminalId(TerminalId);
    }

    @Override
    public List<CarInfo> getAllDataWithUptime() {
        List<CarInfo> ret = carInfoMapper.getAllDataWithUptime();
        Date now = new Date();
        Date line = new Date(now.getTime() - 5 * 60 * 1000);
        for(int i = 0; i < ret.size(); i++){
            if(ret.get(i).getUptime() != null && ret.get(i).getUptime().after(line)){
                ret.get(i).setOnline(true);
            }else{
                ret.get(i).setOnline(false);
            }

        }
        return ret;
    }


}
