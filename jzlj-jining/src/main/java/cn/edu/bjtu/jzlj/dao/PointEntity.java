package cn.edu.bjtu.jzlj.dao;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.xml.soap.SAAJResult;
import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PointEntity {
    /**
     * 点经度
     */
    private Double pointLongitude;
    /**
     * 点纬度
     */
    private Double pointLatitude;

    public void setPointLongitude(Double pointLongitude) {
        this.pointLongitude = pointLongitude;
    }

    public Double getPointLongitude() {
        return pointLongitude;
    }

    public void setPointLatitude(Double pointLatitude) {
        this.pointLatitude = pointLatitude;
    }

    public Double getPointLatitude() {
        return pointLatitude;
    }
}
