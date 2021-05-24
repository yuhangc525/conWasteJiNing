package cn.edu.bjtu.jzlj.vo.graph;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.crypto.Data;

@lombok.Data
public class getAlarmCarNumberVo {

//    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private String alarmDate;

    private Integer alarmNum;
}
