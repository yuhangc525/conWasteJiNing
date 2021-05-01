package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;

/** @Author: wzj
 * @Description:  车辆轨迹实体类
 * @Date 2020/11/13 16:30
 **/


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car_trail_info")
public class CarTrail extends Model<CarTrail> {

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
