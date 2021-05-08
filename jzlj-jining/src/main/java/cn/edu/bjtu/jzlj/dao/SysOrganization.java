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
import javax.xml.soap.SAAJResult;

/**
  * @Author: 田英杰
  * @Description: 组织实体表
  * @Date 2020/11/16 10:53
  * @Param  * @param null
  * @return
  * @throws:
  **/
 @Data
 @EqualsAndHashCode(callSuper = false)
 @Accessors(chain = true)
 @TableName("sys_organization")
public class SysOrganization implements Serializable{

//     serialVersionUID 用来表明实现序列化类的不同版本间的兼容性
     private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
      * id
      */
     @TableId(value = "id", type = IdType.AUTO)
     private String id;


    /**
     * 组织名称
     */
    private String organizationName;

    /**
     * 上级单位
     */
     private String parentAccount;

    /**
     * 统一社会信用代码
     */
     private String socialCreditCode;

    /**
     * 联系电话
     */
     private String mobile;

    /**
     * 地址
     */
    private String address;

    /**
     * 状态，1：正常，0：删除
     */
     private Integer status;

    /**
     * 创建人
     */
    private String createUser;


    /**
     * 创建时间
     */
    @JsonFormat( timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改人
     */
     private String updateUser;

    /**
     * 修改时间
     */
    @JsonFormat( timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;


}
