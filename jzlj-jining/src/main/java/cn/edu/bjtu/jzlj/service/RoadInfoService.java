package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.vo.RoadInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;


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
}
