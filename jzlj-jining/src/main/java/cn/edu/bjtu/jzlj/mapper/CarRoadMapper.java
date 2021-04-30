package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.CarRoad;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRoadMapper extends BaseMapper<CarRoad>{


     /**
      * @Author: 田英杰
      * @Description: 根据terminal_id查询路线列表
      * @Date 2021/4/24 15:21
      * @Param  * @param null
      * @return
      * @throws:
      **/
    List<CarRoad> getRoadListByTerminalId(String terminalId);

     /**
      * @Author: 田英杰
      * @Description: 插入车辆和路线的对应关系
      * @Date 2021/4/29 19:29
      * @Param  * @param null
      * @return
      * @throws:
      **/
    Boolean insertCarRoadInfo(CarRoad carRoad);
}
