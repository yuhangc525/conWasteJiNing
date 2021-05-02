package cn.edu.bjtu.jzlj.service.impl;


import cn.edu.bjtu.jzlj.dao.CarAlarm;
import cn.edu.bjtu.jzlj.mapper.CarAlarmMapper;
import cn.edu.bjtu.jzlj.service.CarAlarmService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<CarAlarm> getAllUnHandle(){
        List<CarAlarm> list = carAlarmMapper.getAllUnHandle();
        return list;
    }


    @Override
    public int handleCarAlarm(CarAlarm carAlarm){
        int a = carAlarmMapper.handleCarAlarm(carAlarm);
        return a;
    }
}
