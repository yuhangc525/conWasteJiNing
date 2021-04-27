package cn.edu.bjtu.jzlj.config.shiro;

import cn.edu.bjtu.jzlj.dao.SysUser;
//import com.main.dao.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @program: hywx
 * @description: shiro共通
 * @author: tangjing
 * @create: 2019-09-25 15:47
 **/
public class ShiroUtils {

    public static String getMd5Pwd(String pwd) {
        Md5Hash md5Password = new Md5Hash(pwd, "rs", 2);
        return md5Password.toString();
    }

    /**
     * zy 获取当前Session
     * @return Session
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * zy 获取Shiro 的Subject
     * @return Subject
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * cz 获取当前用户
     * @return User
     */
    public static SysUser getCurrentUser() {
        SysUser sysUser = (SysUser) ShiroUtils.getSubject().getPrincipal();
        return sysUser;
    }
//    public static User getCurrentUser() {
//        User user = (User) ShiroUtils.getSubject().getPrincipal();
//        return user;
//    }

    /**
     * zy 判断当前用户是否登录
     * @return boolean(login=true/logout=false)
     */
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    /**
     * zy 当前用户退出
     * @return boolean(login=true/logout=false)
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static void main(String[] args) {
        String md5Pwd = getMd5Pwd("12345678");
        System.out.println(md5Pwd);
    }
}

