package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.CarRoad;
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
public interface CarRoadService extends IService<CarRoad> {

    List<CarRoad> getRoadListByTerminalId(String terminalId);
}
