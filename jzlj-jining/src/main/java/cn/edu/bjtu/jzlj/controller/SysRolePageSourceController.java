package cn.edu.bjtu.jzlj.controller;

import cn.edu.bjtu.jzlj.dao.SysRolePageSource;
import cn.edu.bjtu.jzlj.service.SysRolePageSourceService;
import cn.edu.bjtu.jzlj.util.results.Resp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: SysRolePageSource
 * @Description:
 * @Author sjyzj
 * @Date 2021/5/9 10:10
 */
@RestController
@RequestMapping("/SysRolePagesource")
@Api(description = "角色-页面关联表")
public class SysRolePageSourceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysRolePageSourceController.class);
    @Autowired
    private SysRolePageSourceService sysRolePageSourceService;


    @GetMapping("/delByRoleIdJoinPsourceIdList")
    @ApiOperation(value = "根据角色id与页面id取消关联关系",httpMethod = "GET")
    public Resp delByRoleIdJoinPsourceIdList(@RequestParam("roleId") String roleId,
                                             @RequestParam("pSourceIdList") List<String> pSourceIdList){
        long startTime = System.currentTimeMillis();
        try {
//            rolePagesourceService.delByRoleIdJoinPsourceId(roleId,pSourceIdList);
            sysRolePageSourceService.delByRoleIdJoinPsourceIdList(roleId,pSourceIdList);
            long endTime = System.currentTimeMillis();
            LOGGER.info("roleId为"+ roleId + ",psourceId为" + pSourceIdList.toString() + "的权限删除成功，用时："
                    + (endTime - startTime)+ "ms");
            return Resp.getInstantiationSuccess("删除权限成功",null,null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("roleId为" + roleId + ",psourceId为" + pSourceIdList.toString() + "的权限删除失败，原因："+ e.getMessage()
                    + ",用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("删除权限异常,原因：" + e.getMessage(),null,null);
        }
    }

    @GetMapping("/delByRoleId/{roleId}")
    @ApiOperation(value = "根据角色id取消对应的关联权限" ,httpMethod = "GET")
    public Resp delByRoleId(@PathVariable String roleId){

        long startTime = System.currentTimeMillis();
        try {
            sysRolePageSourceService.delByRoleId(roleId);
            long endTime = System.currentTimeMillis();
            LOGGER.info("roleId为"+ roleId + "的删除权限成功，用时：" + (endTime-startTime) + "ms" );
            return Resp.getInstantiationSuccess("删除权限成功",null,null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("roleId为" + roleId + "的权限删除失败，原因："+ e.getMessage() + ",用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("删除权限异常,原因：" + e.getMessage(),null,null);
        }
    }

    @GetMapping("/addByRoleIdJoinPsourceIdList")
    @ApiOperation(value = "根据角色Id,页面IdList添加关联关系",httpMethod = "GET")
    public Resp addByRoleIdJoinPsourceIdList(@RequestParam("roleId") String roleId, @RequestParam("pSourceIdList") List<String> pSourceIdList){
        long startTime = System.currentTimeMillis();
        try {
            //todo  唯一性判断
            sysRolePageSourceService.addByRoleIdJoinPsourceIdList(roleId,pSourceIdList);
            long endTime = System.currentTimeMillis();
            LOGGER.info("roleId为"+ roleId + ",psourceId为" + pSourceIdList.toString() + "的权限添加成功，用时："
                    + (endTime - startTime)+ "ms");
            return Resp.getInstantiationSuccess("添加权限成功",null,null);
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            LOGGER.error("roleId为" + roleId + ",psourceId为" + pSourceIdList.toString() + "的权限添加失败，原因："
                    + e.getMessage() + ",用时：" + (endTime - startTime) + "ms");
            return Resp.getInstantiationSuccess("添加权限异常,原因：" + e.getMessage(),null,null);
        }
    }
}
