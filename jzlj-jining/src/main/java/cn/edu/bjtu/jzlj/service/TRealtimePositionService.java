package cn.edu.bjtu.jzlj.service;
import cn.edu.bjtu.jzlj.dao.TRealtimePosition;
import cn.edu.bjtu.jzlj.vo.RegionalVehicleSelectionVo;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface TRealtimePositionService extends IService<TRealtimePosition> {
    /**
     * @return
     */

    List<TRealtimePosition> getAllList();
//   根据carno查询该车的实时位置


    //   根据terninalid查询该车的实时位置
    List<TRealtimePosition> getPositionByTerminalId( String terninalId);

    // 矩形 - 区域实时查询车辆
    List<RegionalVehicleSelectionVo> getRectangleRegionalVehicles(double startLat, double startLong, double endLat, double endLong, double interval);

    List<RegionalVehicleSelectionVo> getRectangleRegionalVehicles(double startLat, double startLong, double endLat, double endLong);

    // 圆形 - 区域实时查询车辆
    List<RegionalVehicleSelectionVo> getCircleRegionalVehicles(double centerLat, double centerLong, double semidiameter, double interval);

    List<RegionalVehicleSelectionVo> getCircleRegionalVehicles(double centerLat, double centerLong, double semidiameter);

}
