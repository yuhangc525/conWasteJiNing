package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
/**
 * @ClassName: Role
 * @Description:
 * @Author sjyzj
 * @Date 2020/11/13 10:32
 */
@TableName("sys_role")
public class Role implements Serializable {
    /**id**/
    @TableId("id")
    private String id;

    /**
     * 角色名
     */
//    @Column(name="role name")
    private String roleName;


    /**
     * 角色描述
     */
    private String roleDesc;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

//    /**组织id**/
//    @TableField(exist = false)
//    private String orgId;

//    /**用户id**/
//    @TableField(exist = false)
//    private String userId;
//    /**
//     * 状态 0:禁用，1:正常，2：删除
//     */
//    private Integer status;
//    public Integer getStatus() {
//        return status;
//    }
}
