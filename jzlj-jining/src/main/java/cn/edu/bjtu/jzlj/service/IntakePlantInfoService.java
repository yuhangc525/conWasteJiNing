package cn.edu.bjtu.jzlj.service;


import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/* * @Author: 55057
 * @Description: 
 * @Date 2020/12/8 9:53
 * @Param null
 * @return 
 * @throws:
 **/
public interface IntakePlantInfoService extends IService<IntakePlantInfo> {
    /* 分页查询*/
    IPage<IntakePlantInfo> getListByPage(QueryRequest queryRequest, IntakePlantInfo intakePlantInfo);

    /*查询全部数据，不分页*/
    List<IntakePlantInfo> getAllList(QueryRequest queryRequest, IntakePlantInfo intakePlantInfo);

    List<IntakePlantInfo> getIntakePlantInfoByInputName(String inputName);

    int insertinfo(IntakePlantInfo intakePlantInfo);

    int updateinfo(IntakePlantInfo intakePlantInfo);

    int deleteByINTAKE_PLANT_ID(String intakePlantId);

    int updateReviewStatus(Integer reviewStatus, String intakePlantId);

    List<IntakePlantInfo> getIntakePlantInfoByINTakePlantID(String intakePlantId);

    // 根据IntakePlantName查询数据
    List<IntakePlantInfo> getInfoByIntakePlantName(String intakePlantName);

    // 根据IntakePlantName插入或者更新数据，并返回id
    String updateOrInsertIntake(IntakePlantInfo intakePlantInfo);

    int updateInfoByApplyInfo(IntakePlantInfo intakePlantInfo);
}
