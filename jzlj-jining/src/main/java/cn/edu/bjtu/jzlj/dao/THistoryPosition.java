package cn.edu.bjtu.jzlj.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @author
 *
 */
@Data
public class THistoryPosition  implements Serializable {
    /**
     * COLUMN_COMMENT
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String terminalId;

    private String carno;

    private Float lon;

    private Float lat;

    private Float speed;

    private Integer direction;

    private Float height;

    private Float mile;

    private Float oil;

    private String status;

    private String alarmflag;

    private Byte acc;

    private Byte position;

    private BigDecimal lonGcj02;

    private BigDecimal latGcj02;

    private Date uptime;

    private Integer airtight;
    private Integer loadState;
    private Integer lift;

    private Date savetime;


}