package cn.edu.bjtu.jzlj.controller;


import cn.edu.bjtu.jzlj.config.shiro.ShiroUtils;

import cn.edu.bjtu.jzlj.dao.SysUser;

import cn.edu.bjtu.jzlj.service.SysUserService;
import cn.edu.bjtu.jzlj.util.CreateValidateCode;
import cn.edu.bjtu.jzlj.util.results.Resp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;


/**
  * @Author: 田英杰
  * @Description: 用户登录退出
  * @Date 2020/12/8 21:07
  * @Param  * @param null
  * @return
  * @throws:
  **/




 @Api(description = "登录登出信息接口")
 @RestController
 @CrossOrigin(origins= "*", allowCredentials = "true")
public class LoginController {
     private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

     @Resource
     private SysUserService sysUserService;


      /**
       * @Author: 田英杰
       * @Description: 验证码？？（验证码博客：https://blog.csdn.net/qq_43672652/article/details/108082189）
       * @Date 2021/4/5 17:40
       * @Param  * @param null
       * @return
       * @throws:
       **/
      @ApiOperation(value = "验证码", httpMethod = "GET")
      @RequestMapping("/getCode")
      public void getValidateCode(HttpServletResponse response,HttpServletRequest request) throws IOException {
          // 设置相应类型,告诉浏览器输出的内容为图片
          response.setContentType("image/jpeg");

          //创建输出流
          OutputStream outputStream = response.getOutputStream();
          //获取session
          HttpSession session = request.getSession();
          //获取验证码
          CreateValidateCode createValidateCode = new CreateValidateCode();
          String generateVerifyCode = createValidateCode.getString();
          System.out.println("生成并存入session的验证码： " + generateVerifyCode);
          //将验证码存入session，做登录验证
          session.setAttribute("verificationCode",generateVerifyCode);
//          System.out.println(generateVerifyCode);
          //获取验证码图片
          BufferedImage image = createValidateCode.getImage();
          ImageIO.write(image, "JPEG", outputStream);
          //关流
          outputStream.flush();
          outputStream.close();

      }





     /**
       * @Author: 田英杰
       * @Description: 用户登录
       * @Date 2020/12/9 13:57
       * @Param  * @param null
       * @return 
       * @throws:
       **/
      @ApiOperation(value = "用户登录", httpMethod = "POST")
      @RequestMapping(value = "user/login",method = RequestMethod.POST)
     public Resp userLogin(@RequestParam("loginName") String longinName, @RequestParam("password") String password,
                           @RequestParam("rememberMe") Boolean rememberMe, @RequestParam("captcha") String captcha,HttpServletRequest request){
          long time = System.currentTimeMillis();
//          HttpSession session = request.getSession();
          Session session = SecurityUtils.getSubject().getSession();
          String code = (String) session.getAttribute("verificationCode");
          System.out.println("点击登录从session拿到的验证码： " + code);

         if (!StringUtils.isEmpty(longinName) && !StringUtils.isEmpty(password)){
             Subject subject = ShiroUtils.getSubject();
//             password = ShiroUtils.getMd5Pwd(password);
             UsernamePasswordToken token = new UsernamePasswordToken(longinName,password);
             SysUser sysUser = null;
             if (rememberMe){
                 token.setRememberMe(true);
             }
             try{
                 if (captcha.equalsIgnoreCase(code)){
                     subject.login(token);
                     sysUser = (SysUser) ShiroUtils.getSubject().getPrincipal();
                     LOGGER.info("当前的用户是:", sysUser);
                     return Resp.getInstantiationSuccessString("登录成功", sysUser);
                 } else {
                     LOGGER.error("登录失败，验证码错误！");
                     return Resp.getInstantiationErrorString("登陆失败，验证码错误","登陆失败，验证码错误");
                 }
//                 sysUser = ShiroUtils.getCurrentUser();
//                 sysUser = (SysUser) ShiroUtils.getSubject().getPrincipal();

             }catch (Exception e){
                 LOGGER.error("登陆失败，异常：" + e.getMessage() + ",用时：" + (System.currentTimeMillis() - time) + "ms");
                 return Resp.getInstantiationErrorString("登陆失败，用户名和密码不匹配","登陆失败，用户名和密码不匹配");
             }
         }else {
             return Resp.getInstantiationErrorString("登陆失败，请输入用户名和密码", "登陆失败，请输入用户名和密码");
         }
     }






      /**
       * @Author: 田英杰
       * @Description: 用户退出
       * @Date 2020/12/9 14:09
       * @Param  * @param null
       * @return
       * @throws:
       **/
      @ApiOperation(value = "用户退出", httpMethod = "GET")
      @RequestMapping(value = "/logout", method = RequestMethod.GET)
      @RequiresUser
     public Resp logout(){
          if (ShiroUtils.isLogin()){
              ShiroUtils.logout();
              return Resp.getInstantiationErrorString("退出成功","退出成功");
          }
          return Resp.getInstantiationErrorString("当前用户没有登录", null);
     }


}
