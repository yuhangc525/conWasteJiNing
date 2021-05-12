package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.CarAlarm;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

public interface CarAlarmService extends IService<CarAlarm> {


    Boolean insertCarAlarmInfo(CarAlarm carAlarm);

    IPage<CarAlarm> getAllUnHandle(Integer pageNo, Integer pageSize);

    int handleCarAlarm(CarAlarm carAlarm);

    void handleMCarAlarm(List<Integer> id, String updateUser, Date updateTime) throws Exception;
}
