package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;
import cn.edu.bjtu.jzlj.service.THistoryPositionService;
import cn.edu.bjtu.jzlj.util.results.Resp;
import cn.edu.bjtu.jzlj.vo.RegionalVehicleSelectionVo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.bjtu.jzlj.dao.THistoryPosition;
import cn.edu.bjtu.jzlj.service.CarInfoService;
import java.util.*;

import cn.edu.bjtu.jzlj.util.TimeUtil;
@Api(description = "车辆历史轨迹的接口接口")
@RestController
@RequestMapping(value = "/tHistoryPosition")
public class THistoryPositionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysOrganizationController.class);
    Calendar calendar=new GregorianCalendar();
    @Autowired
    THistoryPositionService tHistoryPositionService;
    @Autowired
    CarInfoService carInfoService;

    @ApiOperation(value = "车辆历史轨迹查询", httpMethod = "POST")
    @RequestMapping(value = "history")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @ControllerEndpoint(operation = "车辆历史轨迹查询", exceptionMessage = "车辆历史轨迹查询失败")
    public Resp getHistoryPoint( @RequestParam(value="START_TIME",required=false) String sTime, @RequestParam(value="END_TIME",required=false) String eTime, String carNo) {
        long startTime = System.currentTimeMillis();
        try {
            List<THistoryPosition> result=new ArrayList<>();
            List<THistoryPosition> finalResult=new ArrayList<>();
            List<String> tableList=getTableList(sTime,eTime);
            String terminalId=carInfoService.getTerminalIdByCarNo(carNo);
            System.out.println("================================");
            System.out.println(terminalId);
            if (tableList.size()>1){
                for (int i=0;i<tableList.size();i++){
//                    System.out.println("ppppppppppppppppppppp");
                    System.out.println(tableList.get(i));
                    String thisStime="";
                    String thisEtime="";
                    if (i==0){
                        thisStime=sTime;
                        thisEtime=tableList.get(i)+ " 23:59:59";
                    }
                    else if (i+1==tableList.size()){
                        thisStime=tableList.get(i)+" 00:00:00";
                        thisEtime=eTime;
                    }
                    else{
                        thisStime=tableList.get(i)+" 00:00:00";
                        thisEtime=tableList.get(i)+" 23:59:59";

                    }
//                    System.out.println(thisStime);
//                    System.out.println(thisEtime);
                    String tableName="t_history_position_"+TimeUtil.dateToString(TimeUtil.stringToDate(thisEtime,"yyyy-MM-dd"),"yyyyMMdd");
                    List<THistoryPosition> list = tHistoryPositionService.getHistoryPoint(thisStime,thisEtime,terminalId,tableName);
                    result.addAll(list);
                }
                long endTime = System.currentTimeMillis();
                LOGGER.info("车辆实时状态列表查询成功，用时："+ (endTime - startTime) +"ms");
                if (result.size()>50){


                    int index=0;
                    while (index<result.size()){
                        finalResult.add(result.get(index));
                        index+=3;

                    }
                }else{
                    finalResult=result;
                }
                return Resp.getInstantiationSuccess("车辆历史轨迹状态列表查询成功", Resp.LIST,finalResult);


            }else {
                String tableName="t_history_position_"+TimeUtil.dateToString(TimeUtil.stringToDate(sTime,"yyyy-MM-dd"),"yyyyMMdd");
                List<THistoryPosition> list = tHistoryPositionService.getHistoryPoint(sTime,eTime,terminalId,tableName);
                long endTime = System.currentTimeMillis();
                LOGGER.info("车辆实时状态列表查询成功，用时："+ (endTime - startTime) +"ms");
                if (list.size()>50){
                    int index2=0;
                    while (index2<list.size()){
                        finalResult.add(list.get(index2));
                        index2+=3;

                    }

                }
                else {
                    finalResult=list;
                }
                return Resp.getInstantiationSuccess("车辆历史轨迹状态列表查询成功", Resp.LIST,finalResult);
            }
//            return Resp.getInstantiationSuccess("车辆实时状态列表查询成功",null,dirverIPage);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("车辆历史轨迹列表查询失败，原因：" + e.getMessage() + "，用时：" + (endTime - startTime) +"ms");
            return Resp.getInstantiationError("车辆历史轨迹列表查询失败，原因：" + e.getMessage(),null,null);
        }
    }

    @ApiOperation(value = "矩形区域 - 按时段查询出现的车辆，时间是yyyy-MM-dd HH:mm:ss格式字符串", httpMethod = "POST")
    @RequestMapping(value = "getRectangleRegionalVehiclesWithTimeLimit")
    @ControllerEndpoint(operation = "矩形区域 - 按时段查询出现的车辆", exceptionMessage = "矩形区域 - 按时段查询出现的车辆失败")
    public Resp getRectangleRegionalVehiclesWithTimeLimit(double startLat, double startLong, double endLat, double endLong, String startTime, String endTime) {
        long sTime = System.currentTimeMillis();
        try {
            List<RegionalVehicleSelectionVo> res = tHistoryPositionService.getRectangleRegionalVehiclesWithTimeLimit(startLat, startLong, endLat, endLong, startTime, endTime);
            return Resp.getInstantiationSuccess("矩形区域 - 按时段查询出现的车辆", Resp.LIST, res);
        }catch (Exception e){
            long eTime = System.currentTimeMillis();
            LOGGER.error("矩形区域 - 按时段查询出现的车辆失败，原因：" + e.getMessage() + "，用时：" + (eTime - sTime) +"ms");
            return Resp.getInstantiationError("矩形区域 - 按时段查询出现的车辆，原因：" + e.getMessage(),null,null);
        }
    }

    @ApiOperation(value = "圆形区域 - 按时段查询出现的车辆，时间是yyyy-MM-dd HH:mm:ss格式字符串", httpMethod = "POST")
    @RequestMapping(value = "getCircleRegionalVehiclesWithTimeLimit")
    @ControllerEndpoint(operation = "圆形区域 - 按时段查询出现的车辆", exceptionMessage = "圆形区域 - 按时段查询出现的车辆失败")
    public Resp getCircleRegionalVehiclesWithTimeLimit(double centerLat, double centerLong, double semidiameter,
                                                       String startTime, String endTime) {
        long sTime = System.currentTimeMillis();
        try {
            List<RegionalVehicleSelectionVo> res = tHistoryPositionService.getCircleRegionalVehiclesWithTimeLimit(centerLat, centerLong, semidiameter, startTime, endTime);
            return Resp.getInstantiationSuccess("圆形区域 - 按时段查询出现的车辆", Resp.LIST, res);
        }catch (Exception e){
            long eTime = System.currentTimeMillis();
            LOGGER.error("圆形区域 - 按时段查询出现的车辆失败，原因：" + e.getMessage() + "，用时：" + (eTime - sTime) +"ms");
            return Resp.getInstantiationError("圆形区域 - 按时段查询出现的车辆，原因：" + e.getMessage(),null,null);
        }
    }

    public List<String> getTableList(String stime,String etime)
    {
        List<String> dateList=new ArrayList();
        try{
            Date d1 = TimeUtil.stringToDate(stime, "yyyy-MM-dd");
            Date d2 = TimeUtil.stringToDate(etime, "yyyy-MM-dd");
            calendar.setTime(d1);
            while (!calendar.getTime().after(d2)){
                String day1= TimeUtil.dateToString(calendar.getTime(),"yyyy-MM-dd");
//                System.out.println(day1);
                dateList.add(day1);
                calendar.add(calendar.DATE,1);
            }

        }catch(Exception e)
        {

        }
        return dateList;
    }
}

