package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.THistoryPosition;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface THistoryPositionMapper extends BaseMapper<THistoryPosition> {

    List<THistoryPosition> getHistoryPoint(String sTime, String eTime, String terminalId, String tableName);
}
