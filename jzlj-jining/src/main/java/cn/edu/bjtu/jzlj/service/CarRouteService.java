package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.CarRoute;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: 田英杰
 * @Description:
 * @Date 2021/4/24 15:18
 * @Param * @param null
 * @return
 * @throws:
 **/
public interface CarRouteService extends IService<CarRoute> {

    List<CarRoute> getRouteListByTerminalId(String terminalId);

    Boolean insertCarRouteInfo(CarRoute carRoute);

    Boolean insertMCarRoute(List<CarRoute> carRouteList) throws Exception;

    List<String> getAllLinkedCarRoute(Integer routeId) throws Exception;
}
