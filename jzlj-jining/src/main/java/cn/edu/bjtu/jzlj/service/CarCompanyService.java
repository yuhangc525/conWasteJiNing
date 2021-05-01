package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.CarCompany;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/** @Author: wzj
 * @Description:
 * @Date 2020/11/12 16:47
 **/


public interface CarCompanyService extends IService<CarCompany> {



    /* 分页查询*/
    IPage<CarCompany> getListByPage(QueryRequest queryRequest);
    IPage<CarCompany> getListByPage(QueryRequest queryRequest,String inputName);

    IPage<CarCompany> getListByPage(QueryRequest queryRequest, CarCompany carCompany );

    /*查询全部数据，不分页*/
    List<CarCompany> getAllList(QueryRequest queryRequest);
    List<CarCompany> getAllList(QueryRequest queryRequest,String inputName);

    List<CarCompany> getAllList(QueryRequest queryRequest, CarCompany carCompany);

    /*新增*/
    int saveData(CarCompany carCompany);

    /*更新*/
    int updateData(CarCompany carCompany);

    /*批量删除*/
    int batchDelete(List<String> ids);

    /*根据车辆公司ID修改审核状态*/

    int changeReview(Integer carCompanyId, Integer reviewStatus);

    // 根据IntakePlantName查询数据
    List<CarCompany> getInfoByCarCompanyName(String carCompanyName);

    // 根据IntakePlantName插入或者更新数据，并返回id
    String updateOrInsertCarCompany(CarCompany carCompany);


}
