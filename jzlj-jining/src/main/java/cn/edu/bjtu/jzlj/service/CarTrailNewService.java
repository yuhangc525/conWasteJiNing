package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.CarTrailNew;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/** @Author: wzj
 * @Description: 
 * @Date 2020/12/23 15:58
 **/



public interface CarTrailNewService extends IService<CarTrailNew> {
    /* 分页查询*/
    IPage<CarTrailNew> getListByPage(QueryRequest queryRequest, String sTime, String eTime, CarTrailNew carTrail);

    /*查询全部数据，不分页*/
    List<CarTrailNew> getAllList(QueryRequest queryRequest, String sTime, String eTime, CarTrailNew carTrail);

    /* 查询实时数据-轮询：每次一条 */
    IPage<CarTrailNew> getDynamicTrail(String carId, int id);
}
