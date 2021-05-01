package cn.edu.bjtu.jzlj.service.impl;
import cn.edu.bjtu.jzlj.mapper.TRealtimePositionMapper;
import cn.edu.bjtu.jzlj.dao.TRealtimePosition;
import cn.edu.bjtu.jzlj.service.TRealtimePositionService;
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
public class TRealtimePositionServiceImpl  implements TRealtimePositionService {
    /**
     * @return
     */
    @Autowired
    TRealtimePositionMapper tRealtimePositionMapper;
    @Override
    public List<TRealtimePosition> getAllList() {

//        System.out.println("-----------------------------------------------------------------------------------------------");
        return tRealtimePositionMapper.getAllList();
    }
    @Override
    public List<TRealtimePosition> getPositionByTerminalId(String ternimalId) {
        return tRealtimePositionMapper.getPositionByTerminalId(ternimalId);
    }


    @Override
    public boolean save(TRealtimePosition entity) {
        return false;
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

    public boolean removeById(Serializable id) {
        return false;
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return false;
    }

    @Override
    public boolean remove(Wrapper<TRealtimePosition> queryWrapper) {
        return false;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return false;
    }

    @Override
    public boolean updateById(TRealtimePosition entity) {
        return false;
    }

    @Override
    public boolean update(TRealtimePosition entity, Wrapper<TRealtimePosition> updateWrapper) {
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

    public TRealtimePosition getById(Serializable id) {
        return null;
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

    public int count(Wrapper<TRealtimePosition> queryWrapper) {
        return 0;
    }

    @Override
    public List<TRealtimePosition> list(Wrapper<TRealtimePosition> queryWrapper) {
        return null;
    }


    @Override
    public List<Map<String, Object>> listMaps(Wrapper<TRealtimePosition> queryWrapper) {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Wrapper<TRealtimePosition> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<TRealtimePosition> getBaseMapper() {
        return null;
    }

}
