package cn.edu.bjtu.jzlj.service.impl;


import cn.edu.bjtu.jzlj.dao.CarAlarm;
import cn.edu.bjtu.jzlj.dao.PageSource;
import cn.edu.bjtu.jzlj.mapper.CarAlarmMapper;
import cn.edu.bjtu.jzlj.service.CarAlarmService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarAlarmServiceImpl extends ServiceImpl<CarAlarmMapper, CarAlarm> implements CarAlarmService {

    @Autowired
    private CarAlarmMapper carAlarmMapper;


    @Override
    public Boolean insertCarAlarmInfo(CarAlarm carAlarm){
        Boolean res = carAlarmMapper.insertCarAlarmInfo(carAlarm);
        return res;
    }

    @Override
    public IPage<CarAlarm> getAllUnHandle(Integer pageNo, Integer pageSize){
        Page<CarAlarm> pageBean = new Page<>(pageNo, pageSize);
        List<CarAlarm> carAlarmList = carAlarmMapper.getAllUnHandle(pageBean);
        pageBean.setRecords(carAlarmList);
        return pageBean;
    }

    @Override
    public IPage<CarAlarm> getAllHandled(Integer pageNo, Integer pageSize){
        Page<CarAlarm> pageBean = new Page<>(pageNo, pageSize);
        List<CarAlarm> carAlarmList = carAlarmMapper.getAllHandled(pageBean);
        pageBean.setRecords(carAlarmList);
        return pageBean;
    }


    @Override
    public void handleCarAlarm(Integer id, String updateUser, Date updateTime,
                               Integer status, String remarks) throws Exception {
        if (null == id){
            throw new Exception("报警信息主键为空");
        }
        carAlarmMapper.handleCarAlarm(id, updateUser, updateTime, status,remarks);

    }

    @Override
    public void handleMCarAlarm(List<Integer> id, String updateUser, Date updateTime,
                                Integer status, String remarks) throws Exception {
        if (null == id){
            throw new Exception("报警信息主键为空");
        }
        carAlarmMapper.handleMCarAlarm(id, updateUser, updateTime, status,remarks);
    }
}
