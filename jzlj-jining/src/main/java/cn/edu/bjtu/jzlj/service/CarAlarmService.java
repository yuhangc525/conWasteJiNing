package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.CarAlarm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CarAlarmService extends IService<CarAlarm> {


    Boolean insertCarAlarmInfo(CarAlarm carAlarm);

    List<CarAlarm> getAllUnHandle();

    int handleCarAlarm(CarAlarm carAlarm);
}
