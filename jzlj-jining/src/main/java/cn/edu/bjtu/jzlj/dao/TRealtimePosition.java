package cn.edu.bjtu.jzlj.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_realtime_position")
public class TRealtimePosition implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * COLUMN_COMMENT
     */
    @TableId(value = "terminal_id")
    private String terminalId;

    /**
     * 车牌号
     */
    private String carno;

    /**
     * 经度,单位为度，保留小数点后6位
     */
    private BigDecimal lon;

    /**
     * 纬度,单位为度，保留小数点后六位
     */
    private BigDecimal lat;

    /**
     * 速度，单位KM/H，保留小数点后一位
     */
    private BigDecimal speed;

    /**
     * 方向,0-360，正北为0,
     */
    private BigDecimal direction;

    /**
     * 海拔高度，单位为米
     */
    private Integer height;

    /**
     * 里程，单位km，保留小数点后一位
     */
    private BigDecimal mile;

    /**
     * 单位升L，保留小数点后一位
     */
    private BigDecimal oil;

    /**
     * 状态位，二进制表示
     */
    private String status;

    /**
     * 报警标记位
     */
    private String alarmflag;

    /**
     * acc开关标记
     */
    private Byte acc;

    /**
     * 定位标记
     */
    private Byte position;

    /**
     * 国家测绘局坐标经度
     */
    private BigDecimal lonGcj02;

    /**
     * 国家测绘局坐标系纬度
     */
    private BigDecimal latGcj02;

    /**
     * 上报时间
     */
    private Date uptime;


}