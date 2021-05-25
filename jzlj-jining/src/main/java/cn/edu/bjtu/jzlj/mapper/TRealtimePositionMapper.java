package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.TRealtimePosition;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TRealtimePositionMapper extends BaseMapper<TRealtimePosition> {
    List<TRealtimePosition> getAllList();

    TRealtimePosition getOneList();

    List<TRealtimePosition> getPositionByTerminalId( String terninalId);


}
