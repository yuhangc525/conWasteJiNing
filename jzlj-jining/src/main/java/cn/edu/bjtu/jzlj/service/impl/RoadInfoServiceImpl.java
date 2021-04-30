package cn.edu.bjtu.jzlj.service.impl;


import cn.edu.bjtu.jzlj.controller.IntakePlantInfoController;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.mapper.RoadInfoMapper;
import cn.edu.bjtu.jzlj.service.RoadInfoService;
import cn.edu.bjtu.jzlj.vo.RoadInfoVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: 田英杰
 * @Description:
 * @Date 2021/4/14 14:42
 * @Param * @param null
 * @return
 * @throws:
 **/
@Service
public class RoadInfoServiceImpl extends ServiceImpl<RoadInfoMapper, RoadInfo> implements RoadInfoService {
    @Autowired
    private RoadInfoMapper roadInfoMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(IntakePlantInfoController.class);

    /**
     * @return
     * @Author: 田英杰
     * @Description: 新增路段信息
     * @Date 2021/4/14 15:35
     * @Param * @param null
     * @throws:
     **/
    @Override
    public Boolean insertRoadInfo(RoadInfoVo roadInfoVo) {

        String RA = roadInfoVo.getRoadAddress().toString();
        RoadInfo roadInfo = new RoadInfo();

        roadInfo.setRoadAddress(RA);
        roadInfo.setContent(roadInfoVo.getContent());
        roadInfo.setInputTime(roadInfoVo.getInputTime());
        roadInfo.setInputName(roadInfoVo.getInputName());
        roadInfo.setRoadName(roadInfoVo.getRoadName());
        roadInfo.setRoadId(roadInfoVo.getRoadId());

        return roadInfoMapper.insertRoadInfo(roadInfo);
    }


     /**
      * @Author: 田英杰
      * @Description: 根据id查询路段信息
      * @Date 2021/4/16 11:13
      * @Param  * @param null
      * @return
      * @throws:
      **/

     @Override
    public RoadInfo getRoadInfo(Integer roadId) {
        return roadInfoMapper.getRoadInfo(roadId);
    }

    @Override
    public List<RoadInfo> getInfoByRoadName(String roadName) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("road_name", roadName);
        return roadInfoMapper.selectByMap(columnMap);
    }

    @Override
    public Integer updateOrInsertRoad(RoadInfo roadInfo) {
        List<RoadInfo> list;
        if(!"".equals(roadInfo.getRoadName())){
            list = getInfoByRoadName(roadInfo.getRoadName());
        }else{
            LOGGER.error("roadName为空！Error！");
            return null;
        }
        int id; // 记录的id
        if(!list.isEmpty()){
            // 若数据库中存在记录
            id = list.get(0).getRoadId();
            roadInfo.setRoadId(id);
            int row = roadInfoMapper.updateById(roadInfo);
        }else{
            // 若数据库中不存在记录
            int row = roadInfoMapper.insert(roadInfo);
            id = getInfoByRoadName(roadInfo.getRoadName()).get(0).getRoadId();
        }
        return id;
    }

    @Override
    public List<RoadInfo> getRoadInfoByRoadId(String roadId) {
        return roadInfoMapper.getRoadInfoByRoadId(roadId);
    }
}
