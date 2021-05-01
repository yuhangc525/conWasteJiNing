package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.DateTimeException;
import java.util.Date;

/**
 * @ClassName: IntakePlantInfo
 * @Description:
 * @Author 55057
 * @Date 2020/12/8 9:43
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("intake_plant_info")
public class IntakePlantInfo {
    private static final long serialVersionUID=1L;

    @TableId(value = "intake_plant_id")
    private String intakePlantId;
    private String id;
    private String intakePlantName;
    private String intakePlantAddress;
    private Double intakePlantLong;
    private Double intakePlantLat;
    private String intakeLinkman;
    private String intakePhoneNo;
    private String inputName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inputTime;
    private String content;
    private Integer reviewStatus;

}
