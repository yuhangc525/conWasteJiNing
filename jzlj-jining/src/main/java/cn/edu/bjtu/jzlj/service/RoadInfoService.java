package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.vo.RoadInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * @Author: 田英杰
 * @Description:
 * @Date 2021/4/14 14:47
 * @Param * @param null
 * @return
 * @throws:
 **/
public interface RoadInfoService extends IService<RoadInfo> {
    /**
     * @return
     * @Author: 田英杰
     * @Description: 新增路段信息
     * @Date 2021/4/14 15:36
     * @Param * @param null
     * @throws:
     **/
    Boolean insertRoadInfo(RoadInfoVo roadInfoVo);

     /**
      * @Author: 田英杰
      * @Description: 根据id查询路段信息
      * @Date 2021/4/16 11:14
      * @Param  * @param null
      * @return
      * @throws:
      **/
    RoadInfo getRoadInfo(Integer roadId);

    // 根据IntakePlantName查询数据
    List<RoadInfo> getInfoByRoadName(String roadName);

    // 根据IntakePlantName插入或者更新数据，并返回id
    Integer updateOrInsertRoad(RoadInfo roadInfo);

    List<RoadInfo> getRoadInfoByRoadId(String roadId);
}
