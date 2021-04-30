package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @ClassName:
 * @Description:产生源表
 * @Author:wangchunxia
 * @Date 2020/11/8 19:04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("source_info")
public class SourceInfo implements Serializable{
    private static final long serialVersionUID=1L;
    @TableId(value = "source_id", type = IdType.UUID)
    private String sourceId;
    private String sourceName;
    private String sourceAddress;
    private String sourceAttrbute;
    private String qmDeptNo;
    private String sourceType;
    private Float wasteTotal;
    private Date startDate;
    private String adminUnit;
    private String adminLegalName;
    private String adminPhoneNo;
    private String  constructionUnit;
    private String constructionLegalName;
    private String constructionPhoneNo;
    private Double lng;
    private Double lat;
    private String inputName;
    @JsonFormat( timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date inputTime;
    private Integer reviewStatus;
}
