package cn.edu.bjtu.jzlj.controller;





import cn.edu.bjtu.jzlj.aspect.ControllerEndpoint;

import cn.edu.bjtu.jzlj.dao.PageSource;

import cn.edu.bjtu.jzlj.util.UuidTool;
import cn.edu.bjtu.jzlj.util.results.Resp;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;;

import cn.edu.bjtu.jzlj.service.PageSourceService;

/**
 * @program: user-service
 * @description:
 * @author 田英杰
 * @date ${2020-11-20}
 */
@Api(description = "资源页面信息接口")
@RestController
@RequestMapping("/pageSource")
public class PageSourceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageSourceController.class);

    @Autowired
    private PageSourceService pageSourceService;


     /**
      * @Author: 田英杰
      * @Description: 新增页面权限信息
      * @Date 2020/11/20 12:45
      * @Param  * @param null
      * @return
      * @throws:
      **/
    @ApiOperation(value = "新增页面权限信息", httpMethod = "POST")
    @PostMapping("/addPageSource")
    @ControllerEndpoint(operation = "新增页面权限信息", exceptionMessage = "新增失败")
    public Resp addPageSource(@RequestBody PageSource pageSource){
        long startTime = System.currentTimeMillis();
        if (null == pageSource) {
            return Resp.getInstantiationError("前端错误，参数为空", Resp.SINGLE, null);
        }
        try {
//            PageSource currentPageSource = pageSourceService.getById(pageSource.getId());
//            if(currentPageSource != null){
//                LOGGER.error("新增资源主键(id)重复，用时：" + (System.currentTimeMillis() - startTime) + "ms");
//                return Resp.getInstantiationError("新增页面权限信息异常", Resp.STRING, null);
//            }
            pageSource.setId(UuidTool.getUUID());
            pageSourceService.save(pageSource);
            long endTime = System.currentTimeMillis();
            LOGGER.info("新增页面权限信息成功，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("新增页面权限信息成功", Resp.SINGLE, null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("新增页面权限信息失败，原因：" + e.getMessage() + "，用时" + (endTime - startTime) + "ms");
            return Resp.getInstantiationError("新增页面权限信息异常，原因：" + e.getMessage(), Resp.SINGLE, pageSource);
        }
    }

}
