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

///**
// * @program: user-serice
// * @description: 用户表
// * @author ld
// * @date 2020-11-08
// */
 /**
  * @Author: 田英杰
  * @Description: 用户表实体类
  * @Date 2020/11/11 14:09
  * @Param  * @param null
  * @return
  * @throws:
  **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//@TableName("SYS_USER")
@TableName("sys_user")
public class SysUser implements Serializable{

    private static final long serialVersionUID=1L;
     /**
      * id
      */
//     @TableId(value = "id", type = IdType.AUTO)
    @TableId(value = "id")
    private String id;
     /**
      * 用户名
      */
     @NotBlank(message = "账户名不能为空")
    private String username;
     /**
      * 密码
      */
    private String password;
     /**
      * 手机号
      */
    private String mobile;
     /**
      * 单位名称
      */
    private String organization;
     /**
      * 状态 0:禁用，1:正常，2：删除
      */
    private Integer status;
     /**
      *创建者
      */
    private String createUser;
     /**
      * 创建时间
      */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
     /**
      * 修改者
      */
    private String updateUser;
     /**
      * 修改时间
      */
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
     /**
      * 备注
      */
    private String remark;
//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date regTime;

     /**
      * 角色id
      **/
     @TableField(exist = false)   //roleId这个字段在数据表中不存在
     private String roleId;
     /**角色名称**/
     @TableField(exist = false)
     private String roleName;
     /**组织id**/
     @TableField(exist = false)
     private String orgId;
     /**组织名称**/
     @TableField(exist = false)
     private String organizationName;
     /**页面**/
     @TableField(exist = false)
     private List<PageSource> pageSources;

     public String getRoleId() {
         return roleId;
     }

     public void setRoleId(String roleId) {
         this.roleId = roleId;
     }

     public String getRoleName() {
         return roleName;
     }

     public void setRoleName(String roleName) {
         this.roleName = roleName;
     }

     public String getOrgId() {
         return orgId;
     }

     public void setOrgId(String orgId) {
         this.orgId = orgId;
     }

     public String getOrganizationName() {
         return organizationName;
     }

     public void setOrganizationName(String organizationName) {
         this.organizationName = organizationName;
     }

     public List<PageSource> getPageSources() {
         return pageSources;
     }

     public void setPageSources(List<PageSource> pageSources) {
         this.pageSources = pageSources;
     }
//    public SysUser(Integer id, String username, String password, String mobile, String organization, Integer status, String creat_user,
//                   Date creat_time, String update_user, Date update_time, String remark) {
//        super();
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.mobile = mobile;
//        this.organization = organization;
//        this.status = status;
//        this.creat_user = creat_user;
//        this.creat_time = creat_time;
//        this.update_user = update_user;
//        this.update_time = update_time;
//        this.remark = remark;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

     public String getCreateUser() {
         return createUser;
     }

     public void setCreateUser(String createUser) {
         this.createUser = createUser;
     }

     public Date getCreateTime() {
         return createTime;
     }

     public void setCreateTime(Date createTime) {
         this.createTime = createTime;
     }

     public String getUpdateUser() {
         return updateUser;
     }

     public void setUpdateUser(String updateUser) {
         this.updateUser = updateUser;
     }

     public Date getUpdateTime() {
         return updateTime;
     }

     public void setUpdateTime(Date updateTime) {
         this.updateTime = updateTime;
     }

     public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}