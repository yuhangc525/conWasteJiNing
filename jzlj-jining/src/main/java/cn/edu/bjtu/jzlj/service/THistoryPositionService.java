package cn.edu.bjtu.jzlj.service;
import cn.edu.bjtu.jzlj.dao.THistoryPosition;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

public interface THistoryPositionService extends IService<THistoryPosition> {

    List<THistoryPosition> getHistoryPoint(@RequestParam("START_TIME") String sTime, @RequestParam("END_TIME") String eTime, String terminalId,String tableName);
}
