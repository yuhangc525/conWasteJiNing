package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.CarAlarm;
import cn.edu.bjtu.jzlj.dao.PageSource;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
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

    List<CarAlarm> getAllUnHandle (Page<CarAlarm> pageBean);

    List<CarAlarm> getAllHandled (Page<CarAlarm> pageBean);

    void handleCarAlarm(@Param("id") Integer id,
                        @Param("updateUser") String updateUser,
                        @Param("updateTime") Date updateTime,
                        @Param("status") Integer status,
                        @Param("remarks") String remarks);

    void handleMCarAlarm(@Param("id") List<Integer> id,
                         @Param("updateUser") String updateUser,
                         @Param("updateTime") Date updateTime,
                         @Param("status") Integer status,
                         @Param("remarks") String remarks);


}
