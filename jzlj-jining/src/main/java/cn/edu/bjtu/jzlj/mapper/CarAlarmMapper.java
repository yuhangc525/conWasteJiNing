package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.CarAlarm;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarAlarmMapper extends BaseMapper<CarAlarm> {

     /**
      * @Author: 田英杰
      * @Description: 新增车辆报警信息
      * @Date 2021/4/28 19:45
      * @Param  * @param null
      * @return
      * @throws:
      **/
    Boolean insertCarAlarmInfo(CarAlarm carAlarm);

    List<CarAlarm> getAllUnHandle ();

    int handleCarAlarm(CarAlarm carAlarm);


}
