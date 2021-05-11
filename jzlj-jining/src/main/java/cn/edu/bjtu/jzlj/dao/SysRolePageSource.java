package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName: SysRolePageSource
 * @Description:
 * @Author sjyzj
 * @Date 2021/5/8 19:44
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_pagesource")
public class SysRolePageSource implements Serializable {
    private static final long serialVersionUID=1L;

    @TableId("role_id")
    private String roleId;

    @TableId("psource_id")
    private String PsourceId;


    public SysRolePageSource(String roleId, String PsourceId){
        this.roleId = roleId;
        this.PsourceId = PsourceId;
    }
}
