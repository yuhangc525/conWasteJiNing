package cn.edu.bjtu.jzlj.config.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: MyShiroAuthenticationFilter
 * @Description: 自定义shiro拦截器
 * @Author miaopeng
 * @Date 2019-06-03 16:25:59
 **/
public class MyShiroAuthenticationFilterCopy  {
//    public MyShiroAuthenticationFilterCopy(){
//        super();
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        if (isLoginRequest(request, response)) {
//            return super.onAccessDenied(request, response);
//        } else {
//            if (isAjax((HttpServletRequest) request)) {
//                HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
//                httpServletResponse.addHeader("REQUIRE_AUTH", "true");
//                httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
//            } else {
//                saveRequestAndRedirectToLogin(request, response);
//            }
//            return false;
//        }
//    }
//
//    private boolean isAjax(HttpServletRequest request) {
//        String requestedWithHeader = request.getHeader("X-Requested-With");
//        if (requestedWithHeader != null && requestedWithHeader.equalsIgnoreCase("XMLHttpRequest")) {
//            return true;
//        } else {
//            return false;
//        }
   // }

}
