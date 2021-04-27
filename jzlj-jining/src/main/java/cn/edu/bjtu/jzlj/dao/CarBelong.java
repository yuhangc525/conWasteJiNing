package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.xml.soap.SAAJResult;
import java.io.Serializable;

/** @Author: wzj
 * @Description:
 * @Date 2020/11/9 9:47
 **/


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car_belong")
public class CarBelong implements Serializable {
    @TableId(value = "car_belong_id")
    private String carBelongId;
    private String carName;
    private String carBelongCompany;
    private String carStatus;
    private String terminalCode;
    private String carTypes;
    private String carContactNumber;
    private String carContact;

}
