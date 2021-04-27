package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.RoadInfo;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.springframework.stereotype.Repository;



 /**
  * @Author: 田英杰
  * @Description: 
  * @Date 2021/4/14 14:43
  * @Param  * @param null
  * @return 
  * @throws:
  **/
@Repository
public interface RoadInfoMapper extends BaseMapper<RoadInfo>{

  /**
   * @Author: 田英杰
   * @Description: 插入路段信息
   * @Date 2021/4/14 15:27
   * @Param  * @param null
   * @return
   * @throws:
   **/
  Boolean insertRoadInfo(RoadInfo roadInfo);

   /**
    * @Author: 田英杰
    * @Description: 根据id查询路段信息
    * @Date 2021/4/16 11:11
    * @Param  * @param null
    * @return
    * @throws:
    **/
  RoadInfo getRoadInfo(Integer roadId);
}
