package cn.edu.bjtu.jzlj.config.shiro;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.main.dao.Message;
//import com.main.util.JwtUtils;
import cn.edu.bjtu.jzlj.util.JwtUtils;
//import com.main.util.results.Resp;
import cn.edu.bjtu.jzlj.util.results.Resp;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

//import javax.json.Json;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName: HeaderBasedWebSessionManager
 * @Description: TODO
 * @Author miaopeng
 * @Date 2019-10-08 11:22:00
 **/
public class ShiroSessionManager extends DefaultWebSessionManager implements WebSessionManager {
    /**
     * 定义的请求头中使用的标记key，用来传递 token
     */
    private static final String AUTHORIZATION = "Authorization";

//    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";


    public ShiroSessionManager() {
        super();
        //设置 shiro session 失效时间，默认为30分钟，这里现在设置为15分钟
        //setGlobalSessionTimeout(MILLIS_PER_MINUTE * 15);
    }


    /**
     * 获取sessionId，原本是根据sessionKey来获取一个sessionId
     * 重写的部分多了一个把获取到的token设置到request的部分。这是因为app调用登陆接口的时候，是没有token的，登陆成功后，产生了token,我们把它放到request中，返回结
     * 果给客户端的时候，把它从request中取出来，并且传递给客户端，客户端每次带着这个token过来，就相当于是浏览器的cookie的作用，也就能维护会话了
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //获取请求头中的 Authorization 的值，如果请求头中有 Authorization 则其值可以解析出来sessionId。shiro就是通过sessionId 来控制的
        HttpServletRequest httpServletRequest=WebUtils.toHttp(request);
        String jwt = httpServletRequest.getHeader(AUTHORIZATION);
        if (StringUtils.isEmpty(jwt)){
            Map<String, String[]> params = request.getParameterMap();
            jwt=StringUtils.join(params.get("accessToken"));
            if (!StringUtils.isEmpty(jwt)){
                jwt="Bearer "+jwt;
            }
        }
        if (StringUtils.isEmpty(jwt)) {
            //如果没有携带id参数则按照父类的方式在cookie进行获取sessionId
            return super.getSessionId(request, response);

        } else {
            //请求头中如果有 Authorization, 则其值为sessionId
            try {
                Map<String, String> claims = JwtUtils.verifyToken(jwt.replace("Bearer ", ""));
                String sessionId = claims.get("sessionId");
//             request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
                //sessionId
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
                request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
                return sessionId;
            } catch (TokenExpiredException e) {
                e.printStackTrace();
//                request.setAttribute("errorMsg", "Token已过期");
                String content=JSON.toJSONString( Resp.getInstantiationTokenError("Token已过期", Resp.JSONSTRING, null));
                printContent(response,content);
            } catch (SignatureVerificationException e) {
                e.printStackTrace();
//                request.setAttribute("errorMsg", "Token验证失败");
                String content=JSON.toJSONString( Resp.getInstantiationTokenError("Token验证失败", Resp.JSONSTRING, null));
                printContent(response,content);
            } catch (Exception e) {
                e.printStackTrace();
//                request.setAttribute("errorMsg", "Token无效");
                String content=JSON.toJSONString( Resp.getInstantiationTokenError("Token无效", Resp.JSONSTRING, null));
                printContent(response,content);
            }
            return null;
        }
    }

    private  void printContent(ServletResponse response, String content) {
        try {
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(content);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
