package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.CarBelong;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;
import java.util.Map;

/** @Author: wzj
 * @Description:
 * @Date 2020/11/9 9:56
 **/


public interface CarBelongService extends IService<CarBelong> {

    /*List<Map<String, Object>> getAll();*/

    /* 分页查询*/
    IPage<CarBelong> getListByPage(QueryRequest queryRequest, CarBelong carBelong);

    /*查询全部数据，不分页*/
    List<CarBelong> getAllList(QueryRequest queryRequest, CarBelong carBelong);

    /*新增*/
    int saveData(CarBelong carBelong);

    /*更新*/
    int updateData(CarBelong carBelong);

    /*批量删除*/
    int batchDelete(List<String> ids);
}
