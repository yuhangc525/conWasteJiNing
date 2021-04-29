package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

 /**
  * @Author: 田英杰
  * @Description:
  * @Date 2021/4/28 19:26
  * @Param  * @param null
  * @return
  * @throws:
  **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car_alarm")
public class CarAlarm implements Serializable{

    private static final long serialVersionUID=1L;

    @TableId(value = "road_id", type = IdType.AUTO)
    private Integer id;

    private String terminalId;

    private Double latitude;

    private Double longitude;

    private Integer handled;

    private String updateUser;

    private Date updateTime;

}


