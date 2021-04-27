package cn.edu.bjtu.jzlj.util;

import java.util.Date;

/**
 * @Description: 提前提醒工具类
 * @Author lenovo
 * @Date 2019/11/26 18:17
 */
public class RemindUtil {
    /**  remindFlag 提醒状态所对应的的所有状态  */
    private static final byte DONE_STATUS = 2;
    private static final byte DOING_STATUS = 1;
    private static final byte UNDO_STATUS = 0;



    /**
    * @Description: 根据当前时间判断提醒状态。
     * 当前时间在结束时间之后的，设置为过期；
     * 提醒时间位于提醒时间之后，且结束时间在当前时间前的，设置为提醒中。
     * 否则，设置为未提醒或不用提醒状态
    * @Author:
    * @Date 2019/11/27 9:09
    * @param endDate 结束日期
    * @param remindDate 提醒日期
    * @return byte
    * @throws:
    **/
    public static byte getRemindFlag(Date endDate, Date remindDate){
        Date currentDate = new Date();
        //失效时间在当前时间前，设置状态为已过期
        if(endDate.before(currentDate)){
            return DONE_STATUS;
        }
        if(null != remindDate){
            if(currentDate.after(remindDate)){
                return DOING_STATUS;
            }
        }
        return UNDO_STATUS;
    }
}
