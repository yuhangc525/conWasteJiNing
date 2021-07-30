package cn.edu.bjtu.jzlj.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @author wangzhijin <wangzhijin@kuaishou.com>
 * Created on 2021-07-30
 */
@Data
public class RegionalVehicleSelectionVo {
    private String terminalId;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uptime;
    private String carNo;
}
