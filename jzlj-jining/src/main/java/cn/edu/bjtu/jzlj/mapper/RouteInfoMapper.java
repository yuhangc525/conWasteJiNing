package cn.edu.bjtu.jzlj.mapper;
import cn.edu.bjtu.jzlj.dao.RouteInfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.SQLTransactionRollbackException;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;
 /**
  * @Author: 田英杰
  * @Description:
  * @Date 2021/4/14 14:38
  * @Param  * @param null
  * @return
  * @throws:
  **/
@Repository
public interface RouteInfoMapper extends BaseMapper<RouteInfo>{
}
