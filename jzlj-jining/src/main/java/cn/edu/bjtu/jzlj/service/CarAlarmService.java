package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.CarAlarm;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

public interface CarAlarmService extends IService<CarAlarm> {


    Boolean insertCarAlarmInfo(CarAlarm carAlarm);

    IPage<CarAlarm> getAllUnHandle(Integer pageNo, Integer pageSize);

    IPage<CarAlarm> getAllHandled(Integer pageNo, Integer pageSize);

    void handleCarAlarm(Integer id, String updateUser, Date updateTime,
                        Integer status, String remarks) throws Exception;

    void handleMCarAlarm(List<Integer> id, String updateUser, Date updateTime,Integer status, String remarks) throws Exception;
}
