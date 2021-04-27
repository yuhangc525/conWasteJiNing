package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.CarRoad;
import cn.edu.bjtu.jzlj.mapper.CarRoadMapper;
import cn.edu.bjtu.jzlj.service.CarRoadService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarRoadServiceImpl extends ServiceImpl<CarRoadMapper, CarRoad> implements CarRoadService {

    @Autowired
    private CarRoadMapper carRoadMapper;

     /**
      * @Author: 田英杰
      * @Description: 根据terminal_id查询路线列表
      * @Date 2021/4/24 15:21
      * @Param  * @param null
      * @return
      * @throws:
      **/
     @Override
    public List<CarRoad> getRoadListByTerminalId(String terminalId){
        List<CarRoad> roadList = carRoadMapper.getRoadListByTerminalId(terminalId);
        return roadList;
    }

}
