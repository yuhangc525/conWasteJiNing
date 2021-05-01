package cn.edu.bjtu.jzlj.service;
import cn.edu.bjtu.jzlj.dao.TRealtimePosition;
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
}
