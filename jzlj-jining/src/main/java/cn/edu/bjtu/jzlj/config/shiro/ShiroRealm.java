package cn.edu.bjtu.jzlj.config.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.main.controler.CustomerController;

//import com.main.controler.UserController;
import cn.edu.bjtu.jzlj.controller.SysUserController;
//import com.main.dao.Customer;
//import com.main.dao.User;
import cn.edu.bjtu.jzlj.dao.SysUser;
//import com.main.service.CustomerMessageService;
//import com.main.service.CustomerService;
//import com.main.util.results.Resp;
import cn.edu.bjtu.jzlj.util.results.Resp;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
//import javax.json.Json;
import java.util.HashSet;

public class ShiroRealm extends AuthorizingRealm {
//    @Resource
//    private UserController userController;
//    private SysUserController sysUserController;
//    @Resource
//    private CustomerController customerController;

    /**
     * 认证(登录时调用)
     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        // 获取前台输入账号密码
//        String userName = (String) token.getPrincipal();
//        String passWord = new String((char[]) token.getCredentials());
//        Resp resp = null;
//        SimpleAuthenticationInfo info = null;
//        if (userName.length() > 6 && ("applet".equals(userName.substring(0, 6))||"wechat".equals(userName.substring(0,6)))) {
//            //customerId unionid
//            userName = userName.substring(6, userName.length());
//            //userid unionid 验证
//			resp = customerController.getCustomerByCustomerIdAndUnionid(userName, passWord);
//            //userid openid 验证
////            resp = customerController.getCustomerByCustomerIdAndOpenid(userName, passWord);
//            if (!resp.isSuccess()) {
//                throw new AuthenticationException(resp.getMessage());
//            }
//            Customer user = (Customer) resp.getRespBody();
//            info = new SimpleAuthenticationInfo(user, passWord, getName());
//        } else {
//            resp = userController.getUserByunameupasswd(userName, passWord);
//            if (!resp.isSuccess()) {
//                throw new AuthenticationException(resp.getMessage());
//            }
//            User user = (User) resp.getRespBody();
//            info = new SimpleAuthenticationInfo(user, passWord, getName());
//        }
//        return info;
//    }


    @Resource
//    private UserController userController;
    private SysUserController sysUserController;

     /**
      * @Author: 田英杰
      * @Description: 认证（登陆时调用）
      * @Date 2020/11/13 21:48
      * @Param  * @param token
      * @return org.apache.shiro.authc.AuthenticationInfo
      * @throws:
      **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取前台输入账号密码
        String userName = (String) token.getPrincipal();
        String passWord = new String((char[]) token.getCredentials());
        Resp resp = null;
        SimpleAuthenticationInfo info = null;
//        resp = userController.getUserByunameupasswd(userName, passWord);
        resp = sysUserController.getUserByunameupasswd(userName, passWord);

        if (!resp.isSuccess()) {
            throw new AuthenticationException(resp.getMessage());
        }
//        User user = (User) resp.getRespBody();
        SysUser sysUser = (SysUser) resp.getRespBody();
        info = new SimpleAuthenticationInfo(sysUser, passWord, getName());
//        if (userName.length() > 6 && ("applet".equals(userName.substring(0, 6))||"wechat".equals(userName.substring(0,6)))) {
//            //customerId unionid
//            userName = userName.substring(6, userName.length());
//            //userid unionid 验证
//            resp = customerController.getCustomerByCustomerIdAndUnionid(userName, passWord);
//            //userid openid 验证
////            resp = customerController.getCustomerByCustomerIdAndOpenid(userName, passWord);
//            if (!resp.isSuccess()) {
//                throw new AuthenticationException(resp.getMessage());
//            }
//            Customer user = (Customer) resp.getRespBody();
//            info = new SimpleAuthenticationInfo(user, passWord, getName());
//        } else {
//            resp = userController.getUserByunameupasswd(userName, passWord);
//            if (!resp.isSuccess()) {
//                throw new AuthenticationException(resp.getMessage());
//            }
//            User user = (User) resp.getRespBody();
//            info = new SimpleAuthenticationInfo(user, passWord, getName());
//        }
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        HashSet<String> set = new HashSet<String>();
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		info.setStringPermissions(set);
//		return info;
        return null;
    }
}