package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

import java.sql.Time;
import java.util.Date;

/**
 * @ClassName: ApplyInfo
 * @Description:
 * @Author sjyzj
 * @Date 2021/4/13 10:44
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("apply_info")
public class ApplyInfo {
    private static final long serialVersionUID=1L;

    @TableId(value = "apply_id")
    private String applyId;
    private String applyNo;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyDate;
    private String carCompanyId;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tranStartTime1;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tranEndTime1;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tranStartTime2;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tranEndTime2;
    private Integer routeId;
    private String sourceId;
    private String IntakePlantId1;
    private String IntakePlantId2;
    private String IntakePlantId3;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date disposalStartDate;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date disposalEndDate;
    private String disposalScale;
    private String inputName;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inputTime;
    private String content;
    // 新增字段，数据库中无
    @Transient
    @TableField(exist = false)
    private String carCompanyName;
    @Transient
    @TableField(exist = false)
    private String routeName;
    @Transient
    @TableField(exist = false)
    private String sourceName;
    @Transient
    @TableField(exist = false)
    private String intakePlantName1;
}
