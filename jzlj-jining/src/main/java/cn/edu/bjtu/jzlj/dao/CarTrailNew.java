package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @program: jzlj
 * @description: part of Car_Trail_Info, faster
 * @author: wzj
 * @create: 2020-12-23 15:33
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
// 最后一次测试是car_trail_info1
@TableName("car_trail_info_new")
public class CarTrailNew {
    private String trailId;
    private String carId;
    private String speed;
    private Float carLong;
    private Float carLat;
    private String airtight;
    private String lift;
    private String videoAddress;
    private Date inputTime;
    private String content;
}
