package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car_company")
public class CarCompany extends Model<CarCompany> {
    /* 继承Model<T> 或者 实现 Serializable */
    @TableId(value = "car_company_id")
    private String carCompanyId;
    private String carCompanyName;
    private String contact;
    private String contactNumber;
    private String inputName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inputTime;
    private Integer reviewStatus;
    private String carCompanyUnitcode;
    private String legalName;
    private String legalPhoneNo;
    private String legalCertNo;
    private String agentName;
    private String agentPhoneNo;
    private String agentCertNo;
}
