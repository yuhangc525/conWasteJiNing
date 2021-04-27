package cn.edu.bjtu.jzlj.util;

public class GetDistance {
	private static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * * 通过经纬度获取距离(单位：米) * * @param lat1 * @param lng1 * @param lat2 * @param
	 * lng2 * @return 距离
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000d) / 10000d;
		s = s * 1000;
		return s;
	}
    
//	public static void main(String[] args) {
////		double distance = getDistance(34.2675560000, 108.9534750000, 34.2464320000, 108.9534750000);
//		double distance = getDistance(0, 0, 34.2464320000, 108.9534750000);
//		System.out.println(distance);
//
//		//    116.3861    39.9118
//		//    116.3856    39.9211
//		//    116.3956    39.9215
//		//    116.3960    39.9121
//	}
}
