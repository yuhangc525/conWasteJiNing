package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.THistoryPosition;
import cn.edu.bjtu.jzlj.mapper.THistoryPositionMapper;
import cn.edu.bjtu.jzlj.service.THistoryPositionService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
@Service
public class THistoryPositionServiceImpl implements THistoryPositionService {
    @Autowired
    THistoryPositionMapper tHistoryPositionMapper;
    @Override
    public List<THistoryPosition> getHistoryPoint(String sTime, String eTime, String terminalId,String tableName) {
        return tHistoryPositionMapper.getHistoryPoint(sTime,eTime,terminalId,tableName);
    }


    @Override
    public boolean saveBatch(Collection<THistoryPosition> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<THistoryPosition> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<THistoryPosition> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(THistoryPosition entity) {
        return false;
    }

    @Override
    public THistoryPosition getOne(Wrapper<THistoryPosition> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<THistoryPosition> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<THistoryPosition> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<THistoryPosition> getBaseMapper() {
        return null;
    }
}
