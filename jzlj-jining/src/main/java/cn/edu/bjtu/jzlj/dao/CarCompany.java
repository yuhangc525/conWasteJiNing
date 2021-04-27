package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car_company")
public class CarCompany extends Model<CarCompany> {
    /* 继承Model<T> 或者 实现 Serializable */
    @TableId(value = "car_company_id")
    private String carCompanyID;
    private String carCompanyName;
    private String contact;
    private String contactNumber;
    private String inputName;
    private Date inputTime;
    private Integer reviewStatus;
}
