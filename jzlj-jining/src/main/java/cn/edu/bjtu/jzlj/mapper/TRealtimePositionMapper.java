package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.TRealtimePosition;
import cn.edu.bjtu.jzlj.vo.RegionalVehicleSelectionVo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface TRealtimePositionMapper extends BaseMapper<TRealtimePosition> {
    List<TRealtimePosition> getAllList();

    TRealtimePosition getOneList();

    List<TRealtimePosition> getPositionByTerminalId( String terninalId);

    // 矩形 - 区域实时查询车辆
    List<RegionalVehicleSelectionVo> getRectangleRegionalVehicles(double startLat, double startLong, double endLat, double endLong, double interval);

    // 圆形 - 区域实时查询车辆
    List<RegionalVehicleSelectionVo> getCircleRegionalVehicles(double centerLat, double centerLong, double semidiameter, double interval);

    // 多边形 - 区域实时查询车辆
    List<RegionalVehicleSelectionVo> getPolygonRegionalVehicles(String polygon, double interval);
}
