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
 * @ClassName: EFInfo
 * @Description:
 * @Author 55057
 * @Date 2020/12/8 9:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ef_info")
public class EFInfo {
    private static final long serialVersionUID=1L;

    @TableId(value = "ef_id", type = IdType.AUTO)
    private Integer efId;
    private Integer id;
    private String SOrI;
    private String efName;
    @JsonFormat( timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date disposalStartDate;
    @JsonFormat( timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date disposalEndDate;
    private String adminUnit;
    private String adminLegalName;
    private String adminPhoneNo;
    private String constructionUnit;
    private String constructionLegalName;
    private String constructionPhoneNo;
    private String inputName;
    private Double lng;
    private Double lat;
    private String disposalScale;
    @JsonFormat( timezone = "GMT+8", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date inputTime;

}
