package cn.edu.bjtu.jzlj.service.impl;
import cn.edu.bjtu.jzlj.mapper.TRealtimePositionMapper;
import cn.edu.bjtu.jzlj.dao.TRealtimePosition;
import cn.edu.bjtu.jzlj.service.TRealtimePositionService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class TRealtimePositionServiceImpl  implements TRealtimePositionService {
    /**
     * @return
     */
    @Autowired
    TRealtimePositionMapper tRealtimePositionMapper;
    @Override
    public List<TRealtimePosition> getAllList() {
        return tRealtimePositionMapper.getAllList();
    }

    @Override
    public boolean saveBatch(Collection<TRealtimePosition> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<TRealtimePosition> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<TRealtimePosition> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(TRealtimePosition entity) {
        return false;
    }

    @Override
    public TRealtimePosition getOne(Wrapper<TRealtimePosition> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<TRealtimePosition> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<TRealtimePosition> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<TRealtimePosition> getBaseMapper() {
        return null;
    }
}
