package cn.edu.bjtu.jzlj.service.impl;

import cn.edu.bjtu.jzlj.dao.PageSource;
import cn.edu.bjtu.jzlj.dao.SysRolePageSource;
import cn.edu.bjtu.jzlj.mapper.PageSourceMapper;
import cn.edu.bjtu.jzlj.mapper.RoleMapper;
import cn.edu.bjtu.jzlj.mapper.SysRolePageSourceMapper;
import cn.edu.bjtu.jzlj.service.PageSourceService;
import cn.edu.bjtu.jzlj.service.SysRolePageSourceService;
import cn.edu.bjtu.jzlj.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: SysRolePageSourceServiceImpl
 * @Description:
 * @Author sjyzj
 * @Date 2021/5/9 10:11
 */
@Service
public class SysRolePageSourceServiceImpl extends ServiceImpl<SysRolePageSourceMapper, SysRolePageSource> implements SysRolePageSourceService {
    @Autowired
    private SysRolePageSourceMapper sysRolePageSourceMapper;

    @Autowired
    private PageSourceMapper pageSourceMapper;
    @Autowired
    private RoleMapper roleMapper;
//    @Override
//    public int insertPsource(SysRolePageSource sysRolePageSource){
////        List list = null;
////        list.add(pageSource.getId());
////        PageSource pageSource1;
////        for(int i=0; i<pageSource.getId().length(); i++){
////            pageSource.getId().
////        }
//        int id = 0;
//        String[] splitId = sysRolePageSource.getPsourceId().split(",");
//        for(int i=0; i<sysRolePageSource.getPsourceId().length(); i++){
//            PageSource ps = new PageSource();
//            ps.setId(splitId[i]);
//            ps.setRoleId(sysRolePageSource.getRoleId());
//            sysRolePageSourceMapper.insertPsource(ps);
//        }
//        return id;
////        return pageSourceMapper.insertPsource(pageSource);
//    }

    @Override
    public void delByRoleIdJoinPsourceIdList(String roleId, List<String> pSourceIdList) throws Exception {
        //1.????????????
//        validatorRoleIdJoinPSourceIdList(roleId,pSourceIdList);
        //2.????????????
        sysRolePageSourceMapper.delByRoleIdJoinPsourceIdList(roleId,pSourceIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addByRoleIdJoinPsourceIdList(String roleId, List<String> pSourceIdList) throws Exception {
        //????????????
//        validatorRoleIdJoinPSourceIdList(roleId,pSourceIdList);
        //????????????????????????id???????????????
        sysRolePageSourceMapper.delByRoleId(roleId);
        //??????????????????????????????
        sysRolePageSourceMapper.addByyRoleIdJoinPsourceIdList(roleId,pSourceIdList);
    }

    @Override
    public void delByRoleId(String roleId) throws Exception {
        //????????????
//        if(roleIdHasError(roleId)){
//            throw new Exception("??????id?????????????????????");
//        }
        sysRolePageSourceMapper.delByRoleId(roleId);
    }

}
