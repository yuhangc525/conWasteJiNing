package cn.edu.bjtu.jzlj.service;

import cn.edu.bjtu.jzlj.dao.EFInfo;
import cn.edu.bjtu.jzlj.util.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/* * @Author: 55057
 * @Description: 
 * @Date 2020/12/8 9:43
 * @Param null
 * @return 
 * @throws:
 **/
public interface EfInfoService extends IService<EFInfo> {

    /* 分页查询*/
    IPage<EFInfo> getListByPage(QueryRequest queryRequest, EFInfo efInfo);

    /*查询全部数据，不分页*/
    List<EFInfo> getAllList(QueryRequest queryRequest, EFInfo efInfo);

    List<EFInfo> selectByIdAndSOrI(Integer id, String SOrI);

    int insertEfInfo(EFInfo efInfo);

    int deleteByIdAndSOrI(Integer id,String SOrI);
    
}
