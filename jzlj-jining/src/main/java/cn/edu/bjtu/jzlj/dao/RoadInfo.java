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
  * @Description: 路段基本信息表实体类
  * @Date 2021/4/14 14:14
  * @Param  * @param null
  * @return
  * @throws:
  **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("road_info")
public class RoadInfo implements Serializable{

     private static final long serialVersionUID=1L;

     @TableId(value = "road_id", type = IdType.AUTO)
     private Integer roadId;

     private String roadName;

      /**
       * 路线信息
       */
     private String roadAddress;

     private String inputName;
     @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
     private Date inputTime;

     private String content;

 }
