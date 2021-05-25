package cn.edu.bjtu.jzlj.vo.graph;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author: zjwang
 * @date: 2021/5/24 18:48
 * @description:
 */
@Data
public class DateAndTotal {
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date myDate;
    private Integer total;
}
