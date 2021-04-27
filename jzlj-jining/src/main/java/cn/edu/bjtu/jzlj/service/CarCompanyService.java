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

    /*查询全部数据，不分页*/
    List<CarCompany> getAllList(QueryRequest queryRequest);
    List<CarCompany> getAllList(QueryRequest queryRequest,String inputName);

    /*新增*/
    int saveData(CarCompany carCompany);

    /*更新*/
    int updateData(CarCompany carCompany);

    /*批量删除*/
    int batchDelete(List<String> ids);

    /*根据车辆公司ID修改审核状态*/
    int changeReview(Integer carCompanyID, Integer reviewStatus);

}
