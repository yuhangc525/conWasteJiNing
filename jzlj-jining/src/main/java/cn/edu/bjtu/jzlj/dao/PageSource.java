package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("sys_pagesource")
public class PageSource implements Serializable {
    private static final long serialVersionUID=1L;
    /**
     * 页面id
     */
    @TableId("id")
    private String id;
    /**
     * 页面名称
     */
    private String source;
    /**
     * 页面描述
     */
    private String sourcedesc;
    /**
     * 页面分组
     */
    private String bygroup;

//    public PageSource(){
//        super();
//    }
//    public PageSource(String id, String source, String sourcedesc, String bygroup){
//        super();
//        this.id = id;
//        this.source = source;
//        this.bygroup = bygroup;
//        this.sourcedesc = sourcedesc;
//
//    }


//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getSource() {
//        return source;
//    }
//
//    public void setSource(String source) {
//        this.source = source;
//    }
//
//    public String getSourcedesc() {
//        return sourcedesc;
//    }
//
//    public void setSourcedesc(String sourcedesc) {
//        this.sourcedesc = sourcedesc;
//    }
//
//    public String getBygroup() {
//        return bygroup;
//    }
//
//    public void setBygroup(String bygroup) {
//        this.bygroup = bygroup;
//    }
}
