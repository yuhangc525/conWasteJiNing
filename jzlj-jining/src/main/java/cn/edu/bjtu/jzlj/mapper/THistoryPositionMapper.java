package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.THistoryPosition;
import cn.edu.bjtu.jzlj.vo.RegionalVehicleSelectionVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface THistoryPositionMapper extends BaseMapper<THistoryPosition> {

    List<THistoryPosition> getHistoryPoint(String sTime, String eTime, String terminalId, String tableName);

    Integer numberOfVehicleUsed(String tableName);

    // 矩形区域 - 有时间范围限制查询车辆
    List<RegionalVehicleSelectionVo> getRectangleRegionalVehiclesWithTimeLimit(double startLat, double startLong,
            double endLat, double endLong, String startTime, String endTime, String tableName);

    // 圆形区域 - 有时间范围限制查询车辆
    List<RegionalVehicleSelectionVo> getCircleRegionalVehiclesWithTimeLimit(double centerLat, double centerLong, double semidiameter,
            String startTime, String endTime, String tableName);

    // 多边形 - 有时间范围限制查询车辆
    List<RegionalVehicleSelectionVo> getPolygonRegionalVehiclesWithTimeLimit(String polygon, String startTime, String endTime, String tableName);

}
