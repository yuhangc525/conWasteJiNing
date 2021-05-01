package cn.edu.bjtu.jzlj.dao;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/** @Author: zjwang
 * @Description:
 * @Date 2021/04/22 19:47
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("road_info")
public class RoadInfo implements Serializable {
    /**
     * 路段表序号
     */
    @TableId(value = "road_id", type= IdType.AUTO)
    private Integer roadId;

    /**
     * 路段名称
     */
    private String roadName;

    /**
     * 路段经纬度-以geojson形式保存
     */
    private String roadAddress;

    /**
     * 录入人
     */
    private String inputName;

    /**
     * 录入时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inputTime;

    /**
     * 备注
     */
    private String content;

    private static final long serialVersionUID = 1L;
}
