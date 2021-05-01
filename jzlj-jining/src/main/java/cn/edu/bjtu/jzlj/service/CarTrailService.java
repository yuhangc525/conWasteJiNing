package cn.edu.bjtu.jzlj.service;
import cn.edu.bjtu.jzlj.dao.CarTrail;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/** @Author: wzj
 * @Description:
 * @Date 2020/11/12 16:47
 **/


public interface CarTrailService extends IService<CarTrail> {

    /* 分页查询*/
    IPage<CarTrail> getListByPage(QueryRequest queryRequest, String sTime, String eTime, CarTrail carTrail);

    /*查询全部数据，不分页*/
    List<CarTrail> getAllList(QueryRequest queryRequest,String sTime, String eTime, CarTrail carTrail);

    /* 查询实时数据-轮询：每次一条 */
    IPage<CarTrail> getDynamicTrail(String carId, int id);


}
