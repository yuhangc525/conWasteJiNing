package cn.edu.bjtu.jzlj.dao;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OffsetState {

    private String terminalId;

    private Long updateTime;

    private Double latitude;

    private Double longitude;

    private Integer isOffset;

    private Integer isAlarm;

}
