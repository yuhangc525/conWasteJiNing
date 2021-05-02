package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.CarRoute;
import cn.edu.bjtu.jzlj.mapper.CarRouteMapper;
import cn.edu.bjtu.jzlj.service.CarRouteService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarRouteServiceImpl extends ServiceImpl<CarRouteMapper, CarRoute> implements CarRouteService {

    @Autowired
    private CarRouteMapper carRouteMapper;

     /**
      * @Author: 田英杰
      * @Description: 根据terminal_id查询路线列表
      * @Date 2021/4/24 15:21
      * @Param  * @param null
      * @return
      * @throws:
      **/
     @Override
    public List<CarRoute> getRouteListByTerminalId(String terminalId){
        List<CarRoute> roadList = carRouteMapper.getRouteListByTerminalId(terminalId);
        return roadList;
    }


    @Override
    public Boolean insertCarRouteInfo(CarRoute carRoute){
         Boolean res = carRouteMapper.insertCarRouteInfo(carRoute);
         return res;
    }

}
