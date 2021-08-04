package cn.edu.bjtu.jzlj.service;
import cn.edu.bjtu.jzlj.dao.THistoryPosition;
import java.util.List;

import cn.edu.bjtu.jzlj.vo.Point;
import cn.edu.bjtu.jzlj.vo.RegionalVehicleSelectionVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

public interface THistoryPositionService extends IService<THistoryPosition> {

    List<THistoryPosition> getHistoryPoint(@RequestParam("START_TIME") String sTime, @RequestParam("END_TIME") String eTime, String terminalId,String tableName);

    // 矩形区域 - 有时间范围限制查询车辆
    List<RegionalVehicleSelectionVo> getRectangleRegionalVehiclesWithTimeLimit(double startLat, double startLong,
                                                                               double endLat, double endLong, String startTime, String endTime);

    // 圆形区域 - 有时间范围限制查询车辆
    List<RegionalVehicleSelectionVo> getCircleRegionalVehiclesWithTimeLimit(double centerLat, double centerLong, double semidiameter,
                                                                            String startTime, String endTime);
    // 多边形 - 有时间范围限制查询车辆
    List<RegionalVehicleSelectionVo> getPolygonRegionalVehiclesWithTimeLimit(List<Point> points, String startTime, String endTime);
}
