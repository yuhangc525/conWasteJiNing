package cn.edu.bjtu.jzlj.util;

/**
 * @ClassName: Gps
 * @Description: TODO
 * @author: Max dou
 * @date: 2017年4月10日 下午4:19:13
 */
public class Gps {
    private double wgLat;
    private double wgLon;
    public Gps(double wgLat, double wgLon) {
        setWgLat(wgLat);
        setWgLon(wgLon);
    }
    public double getWgLat() {
        return wgLat;
    }
    public void setWgLat(double wgLat) {
        this.wgLat = wgLat;
    }
    public double getWgLon() {
        return wgLon;
    }
    public void setWgLon(double wgLon) {
        this.wgLon = wgLon;
    }
    @Override
    public String toString() {
        return wgLat + "," + wgLon;
    }
}
