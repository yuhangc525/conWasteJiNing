package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ClassName: ResourcePlantInfo
 * @Description:
 * @Author 55057
 * @Date 2020/12/8 19:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("resource_plant_info")
public class ResourcePlantInfo {
    private static final long serialVersionUID=1L;

    @TableId(value = "resource_plant_id", type = IdType.AUTO)
    private String resourcePlantId;
    private String resourcePlantName;
    private String resourcePlantAddress;
    private Float resourcePlantLong;
    private Float resourcePlantLat;
    private String legalPerson;
    private String responsiblePerson;
    private String resourcePlantType;
    private String dailyAcceptance;
    private String dailyOutput;
    private String inputName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inputTime;
    private String content;
//    private Integer reviewStatus;

}
