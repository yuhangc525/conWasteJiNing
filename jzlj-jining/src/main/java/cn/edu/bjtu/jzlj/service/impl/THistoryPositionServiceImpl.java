package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.THistoryPosition;
import cn.edu.bjtu.jzlj.mapper.THistoryPositionMapper;
import cn.edu.bjtu.jzlj.service.THistoryPositionService;
import cn.edu.bjtu.jzlj.util.TimeUtil;
import cn.edu.bjtu.jzlj.vo.Point;
import cn.edu.bjtu.jzlj.vo.RegionalVehicleSelectionVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
@Service
public class THistoryPositionServiceImpl implements THistoryPositionService {
    @Autowired
    THistoryPositionMapper tHistoryPositionMapper;

    private Calendar calendar = new GregorianCalendar();

    @Override
    public List<THistoryPosition> getHistoryPoint(String sTime, String eTime, String terminalId,String tableName) {

//        System.out.println("----------------------1--------------------------------");
//        System.out.println(tHistoryPositionMapper.getAll());
//        System.out.println("----------------------2--------------------------------");

        return tHistoryPositionMapper.getHistoryPoint(sTime,eTime,terminalId,tableName);
    }

    @Override
    public List<RegionalVehicleSelectionVo> getRectangleRegionalVehiclesWithTimeLimit(double startLat, double startLong, double endLat, double endLong, String startTime, String endTime) {
        if(startLat > endLat){
            double temp = startLat;
            startLat = endLat;
            endLat = temp;
        }
        if(startLong > endLong){
            double temp = startLong;
            startLong = endLong;
            endLong = temp;
        }
        List<List<String>> times = getTimes(startTime, endTime);
        List<RegionalVehicleSelectionVo> res = new ArrayList<>();
        for(int i = times.size() - 1; i >= 0; i--) {
            List<String> temp = times.get(i);
            String tableName = getTableName(temp.get(0), temp.get(1));
            List<RegionalVehicleSelectionVo> ans = tHistoryPositionMapper.getRectangleRegionalVehiclesWithTimeLimit(startLat, endLat, startLong, endLong, temp.get(0), temp.get(1), tableName);
            res.addAll(ans);
        }
        return res;
    }

    @Override
    public List<RegionalVehicleSelectionVo> getCircleRegionalVehiclesWithTimeLimit(double centerLat, double centerLong, double semidiameter, String startTime, String endTime) {
        List<List<String>> times = getTimes(startTime, endTime);
        List<RegionalVehicleSelectionVo> res = new ArrayList<>();
        for(int i = times.size() - 1; i >= 0; i--) {
            List<String> temp = times.get(i);
            String tableName = getTableName(temp.get(0), temp.get(1));
            List<RegionalVehicleSelectionVo> ans = tHistoryPositionMapper.getCircleRegionalVehiclesWithTimeLimit(centerLat, centerLong, semidiameter, temp.get(0), temp.get(1), tableName);
            res.addAll(ans);
        }
        return res;
    }

    @Override
    public List<RegionalVehicleSelectionVo> getPolygonRegionalVehiclesWithTimeLimit(List<Point> points, String startTime, String endTime) {
        if(points == null) return null;
        // 获取 polygon
        StringBuilder polygon = new StringBuilder("");
        for(Point point : points) {
            String ans = point.getLng() + " " + point.getLat() + ",";
            polygon.append(ans);
        }
        String last = points.get(0).getLng() + " " + points.get(0).getLat();
        polygon.append(last);
        // 获取读取时间列表
        List<List<String>> times = getTimes(startTime, endTime);
        List<RegionalVehicleSelectionVo> res = new ArrayList<>();
        for(int i = times.size() - 1; i >= 0; i--) {
            List<String> temp = times.get(i);
            String tableName = getTableName(temp.get(0), temp.get(1));
            List<RegionalVehicleSelectionVo> ans = tHistoryPositionMapper.getPolygonRegionalVehiclesWithTimeLimit(polygon.toString(), temp.get(0), temp.get(1), tableName);
            res.addAll(ans);
        }
        return res;
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
    public List<THistoryPosition> listByIds(Collection<? extends Serializable> idList) {
        return null;
    }

    @Override
    public List<THistoryPosition> listByMap(Map<String, Object> columnMap) {
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
    public <V> V getObj(Wrapper<THistoryPosition> queryWrapper, Function<? super Object, V> mapper) {
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
    public List<Map<String, Object>> listMaps(Wrapper<THistoryPosition> queryWrapper) {
        return null;
    }

    @Override
    public <V> List<V> listObjs(Wrapper<THistoryPosition> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }



    @Override
    public BaseMapper<THistoryPosition> getBaseMapper() {
        return null;
    }

    private List<List<String>> getTimes(String startTime, String endTime) {

        List<List<String>> dateList = new ArrayList();
        Date d1 = TimeUtil.stringToDate(startTime, "yyyy-MM-dd HH:mm:ss");
        Date d2 = TimeUtil.stringToDate(endTime, "yyyy-MM-dd HH:mm:ss");
        String endDay = TimeUtil.dateToString(d2, "yyyyMMdd");
        calendar.setTime(d1);
        while(!calendar.getTime().after(d2)) {
            if(TimeUtil.dateToString(calendar.getTime(),"yyyyMMdd").equals(endDay)) {
                List<String> ans = new ArrayList<>();
                ans.add(TimeUtil.dateToString(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
                ans.add(endTime);
                dateList.add(ans);
                calendar.add(calendar.DATE,1);
            } else {
                StringBuilder currentDay = new StringBuilder(TimeUtil.dateToString(calendar.getTime(),"yyyy-MM-dd"));
                List<String> ans = new ArrayList<>();
                ans.add(TimeUtil.dateToString(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"));
                ans.add(currentDay.append(" 23:59:59").toString());
                dateList.add(ans);
                calendar.add(calendar.DATE,1);
                StringBuilder tomorrowDay = new StringBuilder(TimeUtil.dateToString(calendar.getTime(), "yyyy-MM-dd"));
                tomorrowDay.append(" 00:00:00");
                calendar.setTime(TimeUtil.stringToDate(tomorrowDay.toString(), "yyyy-MM-dd HH:mm:ss"));
            }
        }
        return dateList;
    }

    private String getTableName(String startTime, String endTime){
        Date d1 = TimeUtil.stringToDate(startTime, "yyyy-MM-dd HH:mm:ss");
        Date d2 = TimeUtil.stringToDate(endTime, "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        if(!fmt.format(d1).equals(fmt.format(d2))) return null;
        return "t_history_position_" + fmt.format(d1);
    }

}
