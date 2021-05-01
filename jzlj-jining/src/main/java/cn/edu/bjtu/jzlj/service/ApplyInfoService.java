package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.ApplyInfo;
import cn.edu.bjtu.jzlj.dao.IntakePlantInfo;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: sjyzj
 * @Description:
 * @Date 2021/4/13 11:13
 * @Param null
 * @return
 * @throws:
 **/
public interface ApplyInfoService extends IService<ApplyInfo> {
    /* 分页查询*/
    IPage<ApplyInfo> getListByPage(QueryRequest queryRequest, ApplyInfo applyInfo);

    /*查询全部数据，不分页*/
    List<ApplyInfo> getAllList(QueryRequest queryRequest, ApplyInfo applyInfo);

    int insertinfo(ApplyInfo applyInfo);

    int updateinfo(ApplyInfo applyInfo);

    int deleteByApply_id(String applyID);

    List<ApplyInfo> getApplyInfoByApplyId(String applyID);

}
