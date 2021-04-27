package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.SourceInfo;

//import cn.edu.bjtu.jzlj.dao.SourceInfoNew;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author:wangchunxia
 * @Date 2020/11/8 18:37
 */

public interface SourceInfoService extends IService<SourceInfo> {
    /* 分页查询*/
    IPage<SourceInfo> getListByPage(QueryRequest queryRequest, SourceInfo sourceInfo);

    /*查询全部数据，不分页*/
    List<SourceInfo> getAllList(QueryRequest queryRequest, SourceInfo sourceInfo);
    /*新增*/
    int saveData(SourceInfo sourceInfo);

    /*更新*/
    int updateData(SourceInfo sourceInfo);

    /*批量删除*/
    int batchDelete(List<String> sourceId);

    /*审核状态*/
    int updateReviewStatus(Integer reviewStatus, String sourceId);

    /*录入姓名*/
    List<SourceInfo> getSourceInfoByInputName(String inputName);

    /*Id查询*/
    List<SourceInfo> getSourceInfoBySourceId(String sourceId);

}

