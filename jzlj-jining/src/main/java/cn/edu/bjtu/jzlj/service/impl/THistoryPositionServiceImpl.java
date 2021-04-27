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
//        System.out.println("----------------------1--------------------------------");
//        System.out.println(tHistoryPositionMapper.getAll());
//        System.out.println("----------------------2--------------------------------");

        return tHistoryPositionMapper.getHistoryPoint(sTime,eTime,terminalId,tableName);
    }

    @Override
    public boolean save(THistoryPosition entity) {
        return false;
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
    public boolean removeById(Serializable id) {
        return false;
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return false;
    }

    @Override
    public boolean remove(Wrapper<THistoryPosition> queryWrapper) {
        return false;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return false;
    }

    @Override
    public boolean updateById(THistoryPosition entity) {
        return false;
    }

    @Override
    public boolean update(THistoryPosition entity, Wrapper<THistoryPosition> updateWrapper) {
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
    public THistoryPosition getById(Serializable id) {
        return null;
    }

    @Override
    public Collection<THistoryPosition> listByIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public Collection<THistoryPosition> listByMap(Map<String, Object> columnMap) {
        return null;
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
    public int count(Wrapper<THistoryPosition> queryWrapper) {
        return 0;
    }

    @Override
    public List<THistoryPosition> list(Wrapper<THistoryPosition> queryWrapper) {
        return null;
    }

    @Override
    public IPage<THistoryPosition> page(IPage<THistoryPosition> page, Wrapper<THistoryPosition> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<THistoryPosition> queryWrapper) {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Wrapper<THistoryPosition> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<THistoryPosition> page, Wrapper<THistoryPosition> queryWrapper) {
        return null;
    }

    @Override
    public BaseMapper<THistoryPosition> getBaseMapper() {
        return null;
    }
//    @Autowired
//    THistoryPosition

}
