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
  * @Description: 用户组织表实体类
  * @Date 2020/11/19 14:42
  * @Param  * @param null
  * @return
  * @throws:
  **/

 @Data
 @EqualsAndHashCode(callSuper = false)
 @Accessors(chain = true)
 @TableName("sys_organization_user")
public class SysOrganizationUser implements Serializable{

     private static final long serialVersionUID=1L;


     /**
      * 用户id
      */
     @TableId("userid")
     private String userid;


     /**
      * 组织id
      */
     @TableId("orgid")
     private String orgid;




     public SysOrganizationUser(String userid, String orgid){
         this.userid = userid;
         this.orgid = orgid;

     }


     public static long getSerialVersionUID() {
         return serialVersionUID;
     }

     public String getOrgid() {
         return orgid;
     }

     public void setOrgid(String orgid) {
         this.orgid = orgid;
     }

     public String getUserid() {
         return userid;
     }

     public void setUserid(String userid) {
         this.userid = userid;
     }
 }
