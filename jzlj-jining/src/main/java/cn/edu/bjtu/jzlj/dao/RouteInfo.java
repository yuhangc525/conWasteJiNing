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
 * @author: zjwang
 * @date: 2021/4/22 23:50
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("route_info")
public class RouteInfo {

    @TableId(value = "route_id", type= IdType.AUTO)
    private Integer routeId;
    private String routeName;
    private String routeDesign;
    @Transient
    @TableField(exist = false)
    private String routeConvertedDesign;
    private String lngLat;
    private String inputName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inputTime;
    private String content;
}
