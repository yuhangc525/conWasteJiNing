package cn.edu.bjtu.jzlj.mapper;

import cn.edu.bjtu.jzlj.dao.CarInfo;
import cn.edu.bjtu.jzlj.vo.graph.proportionOfVehicleReviewVo;
import cn.edu.bjtu.jzlj.vo.graph.proportionOfVehicleStatusVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CarInfoMapper extends BaseMapper<CarInfo> {

    /**列表查询分页**/
    IPage<CarInfo> getListByPage(Page<CarInfo> page, @Param("inputName") String inputName, @Param("company") String company);
    IPage<CarInfo> getListByPage(Page<CarInfo> page, String terminalId);


    /*不分页查询*/
    List<CarInfo> getAllList(@Param("ew") QueryWrapper<CarInfo> queryWrapper );

    /* 改变审核状态 */
    int changeReview(@Param("carId") String carId, @Param("reviewStatus") Integer reviewStatus);

    /* 根据InputName查询数据条数 */
    int getNum(@Param("inputName") String inputName);


    String getTerminalIdByCarNo(String carNo);

    String getCarNoByTerminalId(String terminalId);

    List<proportionOfVehicleReviewVo> proportionOfVehicleReview();

    List<proportionOfVehicleStatusVo> proportionOfVehicleStatus();

    List<CarInfo> getAllDataWithUptime();
}
