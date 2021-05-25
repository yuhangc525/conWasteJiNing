package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

import java.util.Date;

/**
 * @program: jzlj
 * @description:
 * @author: wzj
 * @create: 2020-12-13 17:22
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

@TableName("car_info")

public class CarInfo {

    /* 继承Model<T> 或者 实现 Serializable */
    @TableId(value = "car_id")
    private String carId;
    private String terminalId;
    private Float loadCapacity;
    private Float carLong;
    private Float carHigh;
    private Float carWidth;
    private String carType;
    private String driverName;
    private String MotorcadeId;
    private String terminalCompany;
    private String company;
    private String carNo;
    private String fromIp;
    private Integer status;
    private String linkman;
    private String phoneNo;
    private String inputName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inputTime;
    private Integer reviewStatus;

    @Transient
    @TableField(exist = false)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uptime;

    @Transient
    @TableField(exist = false)
    private boolean isOnline;

}
