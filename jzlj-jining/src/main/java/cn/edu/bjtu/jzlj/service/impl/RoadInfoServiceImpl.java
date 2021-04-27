package cn.edu.bjtu.jzlj.service.impl;


import cn.edu.bjtu.jzlj.dao.RoadInfo;
import cn.edu.bjtu.jzlj.dao.SysUser;
import cn.edu.bjtu.jzlj.mapper.RoadInfoMapper;
import cn.edu.bjtu.jzlj.service.RoadInfoService;
import cn.edu.bjtu.jzlj.vo.RoadInfoVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
