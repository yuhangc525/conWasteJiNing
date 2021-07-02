package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.CarCompany;
import cn.edu.bjtu.jzlj.dao.CarInfo;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;

public interface CarInfoService extends IService<CarInfo> {

    /* 分页查询*/
    IPage<CarInfo> getListByPage(QueryRequest queryRequest);
    IPage<CarInfo> getListByPage(QueryRequest queryRequest, String inputName, String company);
    IPage<CarInfo> getListByPage(QueryRequest queryRequest, String terminalId);



    /*查询全部数据，不分页*/
    List<CarInfo> getAllList(QueryRequest queryRequest);
    List<CarInfo> getAllList(QueryRequest queryRequest, String inputName, String company);
    List<CarInfo> getAllList(QueryRequest queryRequest, String terminalId);

    /*查询数据条数,可设置参数inputName*/
    int getNum(String inputName);
    /*新增*/
    int saveData(CarInfo carInfo);

    /*批量新增*/
    void saveDataByBatch(List<CarInfo> carInfo);

    /*更新*/
    int updateData(CarInfo carInfo);

    /*批量删除*/
    int batchDelete(List<String> ids);

    /*根据车辆公司ID修改审核状态*/
    int changeReview(String carId, Integer reviewStatus);

    String getTerminalIdByCarNo(String carNo);

    String getCarNoByTerminalId(String TerminalId);

    List<CarInfo> getAllDataWithUptime();
}
