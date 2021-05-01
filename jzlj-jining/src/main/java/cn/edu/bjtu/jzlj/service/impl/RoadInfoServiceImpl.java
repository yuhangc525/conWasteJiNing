package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.controller.IntakePlantInfoController;
import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.dao.RouteInfo;
import cn.edu.bjtu.jzlj.mapper.IntakePlantInfoMapper;
import cn.edu.bjtu.jzlj.mapper.RoadInfoMapper;
import cn.edu.bjtu.jzlj.service.RoadInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zjwang
 * @date: 2021/4/22 23:14
 * @description:
 */
@Service
public class RoadInfoServiceImpl extends ServiceImpl<RoadInfoMapper, RoadInfo> implements RoadInfoService {

    @Autowired
    private RoadInfoMapper roadInfoMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(IntakePlantInfoController.class);

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
