package cn.edu.bjtu.jzlj.dao;

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
 * @Description: 路线基本信息表实体类
 * @Date 2021/4/14 14:14
 * @Param  * @param null
 * @return
 * @throws:
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("route_info")
public class RouteInfo implements Serializable{

    private static final long serialVersionUID=1L;
    /**
     * 路线表序号
     */
    private Integer route_id;

    private String route_name;

    private String route_design;

    private String input_name;

    private Date input_time;

    private String content;
}
