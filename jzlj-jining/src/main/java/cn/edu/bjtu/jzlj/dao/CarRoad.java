package cn.edu.bjtu.jzlj.dao;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.List;


import java.io.Serializable;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

 /**
  * @Author: 田英杰
  * @Description: 车辆路线对应关系表
  * @Date 2021/4/24 14:05
  * @Param  * @param null
  * @return
  * @throws:
  **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("car_route")
public class CarRoad implements Serializable{

     private static final long serialVersionUID=1L;

     @TableId(value = "id", type = IdType.AUTO)
     private Integer Id;

     /**
      * 车辆终端id
      */
     private String terminalId;

     /**
      * 路线id
      */
     private Integer roadId;
}
