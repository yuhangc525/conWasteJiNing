package cn.edu.bjtu.jzlj.util;

import org.springframework.data.geo.Point;

import java.util.List;

/**
 * @Author:
 * @Date: 2019/12/2416:05/**
 * @program:tsinghuabus
 * @description:是否在范围内的算法
 * @author:
 * @creat:2019-12-24 :05
 */
public class IsWithInUtil {
    /**
     * @param list，多边形区域的节点集合
     * @return java.lang.Boolean，返回为是否在里面，True 落在范围内 False 不在范围内
     * @Author: ld
     * @Description: 判断坐标点是否落在指定的多边形区域内
     * @Date 2019/10/31 13:23
     * @Param point, 指定的坐标点
     * @throws:
     **/
    public static Boolean IsWithIn(Point point, List<Point> list)
    {
        double x = point.getX();
        double y = point.getY();

        int isum, icount, index;
        double dLon1 = 0, dLon2 = 0, dLat1 = 0, dLat2 = 0, dLon;
        if (list.size() < 3)
        {
            return false;
        }
        isum = 0;
        icount = list.size();
        try
        {
            for (index = 0; index < icount - 1; index++)
            {
                if (index == icount - 1)
                {
                    dLon1 = list.get(index).getX();
                    dLat1 = list.get(index).getY();
                    dLon2 = list.get(0).getX();
                    dLat2 = list.get(0).getY();
                }
                else
                {
                    dLon1 = list.get(index).getX();
                    dLat1 = list.get(index).getY();
                    dLon2 = list.get(index + 1).getX();
                    dLat2 = list.get(index + 1).getY();
                }
                if (((y >= dLat1) && (y < dLat2)) || ((y >= dLat2) && (y < dLat1)))
                {
                    if (Math.abs(dLat1 - dLat2) > 0)
                    {
                        dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - y)) / (dLat1 - dLat2);
                        if (dLon < x)
                        {
                            isum++;
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }

        if ((isum % 2) != 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
