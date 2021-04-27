package cn.edu.bjtu.jzlj.config.shiro;

//import io.netty.util.internal.StringUtil;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
//这个包没有，直接注释掉了
//import org.hsqldb.lib.StringUtil;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

	@Value("${spring.redis.host}")
	private String host = "127.0.0.1";
//	private String host = "localhost";

	@Value("${spring.redis.port}")
	private int port = 6379;
	// seesion 过期时间
	@Value("${server.session-timeout}")
	private int sessionTimeout=30;
	@Value("${spring.redis.timeout}")
	private int timeout = 0;

	@Value("${spring.redis.password}")
	private String password = "";

	private final String CACHE_KEY = "shiro:cache:";
	private final String SESSION_KEY = "shiro:session:";

	@Bean(name="shiroFilterFactoryBean")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(
			@Qualifier("shiroSecurityManager") SecurityManager securityManager) {

		//shiroFilterFactoryBean对象
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 配置shiro安全管理器 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 指定要求登录时的链接
		shiroFilterFactoryBean.setLoginUrl("/");

		//添加自定义拦截器
		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
		filters.put("authc", new MyShiroAuthenticationFilter());
//		filters.put("anon", new AnonymousFilter());
		shiroFilterFactoryBean.setFilters(filters);

		// filterChainDefinitions拦截器=map必须用：LinkedHashMap，因为它必须保证有序
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		//filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/staticpic/**", "anon");
		filterChainDefinitionMap.put("/user/login", "anon");
		filterChainDefinitionMap.put("/getvehicle", "anon");
		filterChainDefinitionMap.put("/app/wxmnlogin", "anon");
		filterChainDefinitionMap.put("/app/wxapplogin", "anon");
		filterChainDefinitionMap.put("/app/getguidepage", "anon");
		filterChainDefinitionMap.put("/user/sendSms", "anon");
		filterChainDefinitionMap.put("/tx/**", "anon");
		filterChainDefinitionMap.put("/token", "anon");
		filterChainDefinitionMap.put("/app/gettoken", "anon");
		filterChainDefinitionMap.put("/direct-login","anon");
		//consul健康检查放行
		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
		filterChainDefinitionMap.put("/**", "anon");
		// 下面的是要认证
		//filterChainDefinitionMap.put("/**", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean("shiroSecurityManager")
	public SecurityManager shiroSecurityManager(
			@Qualifier("shiroRealm")ShiroRealm myShiroRealm,
			@Qualifier("sessionManager")ShiroSessionManager sessionManager) {

		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//禁止地址栏中出现;JSESSIONID=shiro_session_e26690b8-c25b-4564-816a-622fcb7c5160
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		// 设置realm.
		securityManager.setRealm(myShiroRealm);
		// //注入ehcache缓存管理器;
//		securityManager.setCacheManager(ehCacheManager());
//		if(!StringUtil.isEmpty(password)) {
//			securityManager.setCacheManager(cacheManager());
//		}
		securityManager.setCacheManager(cacheManager());
		// //注入session管理器;
		securityManager.setSessionManager(sessionManager);
		//注入Cookie记住我管理器
		securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	@SuppressWarnings("deprecation")
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
//		byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
//		cookieRememberMeManager.setCipherKey(Base64.decode("12asdf234asdfasdf=="));
		return cookieRememberMeManager;
	}

	@Bean
	public SimpleCookie rememberMeCookie() {
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		//30天
		simpleCookie.setMaxAge(259200);
		return simpleCookie;
	}

	@Bean(name="shiroRealm")
	public ShiroRealm shiroRealm() {
		ShiroRealm shiroRealm = new ShiroRealm();
		return shiroRealm;
	}

	/*
	@Bean(name="shiroSessionManager")
	public DefaultWebSessionManager shiroSessionManager(
			@Qualifier("shiroSessionIdCookie") SimpleCookie simpleCookie,
			@Qualifier("shiroDao") RedisSessionDAO shiroDao ) {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionIdCookie(simpleCookie);
		sessionManager.setSessionDAO(shiroDao);
//		sessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
		return sessionManager;
	}
	*/
	@Bean(name="shiroSessionManager")
	public DefaultWebSessionManager shiroSessionManager(
			@Qualifier("shiroSessionIdCookie") SimpleCookie simpleCookie,
			@Qualifier("shiroDao") ShiroDao  shiroDao
			) {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionIdCookie(simpleCookie);
		// sessionManager.setSessionDAO(shiroDao);
		//if(!StringUtil.isEmpty(password)) {
			sessionManager.setSessionDAO(sessionDAO());
		//}else{
			//sessionManager.setSessionDAO(shiroDao);
		//}
//		sessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
		return sessionManager;
	}

	/**
	 * 自定义的 shiro session 缓存管理器，用于跨域等情况下使用 token 进行验证，不依赖于sessionId
	 * @return
	 */
	@Bean(name = "sessionManager")
	public ShiroSessionManager sessionManager(){
		//将我们继承后重写的shiro session 注册
		ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
		//如果后续考虑多tomcat部署应用，可以使用shiro-redis开源插件来做session 的控制，或者nginx 的负载均衡
		shiroSessionManager.setSessionDAO(sessionDAO());
		return shiroSessionManager;
	}



	@Bean(name="shiroDao")
	public ShiroDao shiroDao() {
		return new ShiroDao();
	}

	@Bean(name="shiroSessionIdCookie")
	public SimpleCookie shiroSessionIdCookie() {
		//DefaultSecurityManager
		SimpleCookie simpleCookie = new SimpleCookie();
		//sessionManager.setCacheManager(ehCacheManager());
		//如果在Cookie中设置了"HttpOnly"属性，那么通过程序(JS脚本、Applet等)将无法读取到Cookie信息，这样能有效的防止XSS攻击。
		simpleCookie.setHttpOnly(true);
		simpleCookie.setName("SHRIOSESSIONID");
		//单位秒
//		simpleCookie.setMaxAge(86400);
		return simpleCookie;
	}

	@Bean(name="sessionDAO")
	public SessionDAO sessionDAO() {
		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
		redisSessionDAO.setRedisManager(redisManager());
		redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
		redisSessionDAO.setExpire(sessionTimeout*60);
		redisSessionDAO.setKeyPrefix(SESSION_KEY);
		return redisSessionDAO;
	}

	@Bean
	public ShiroSessionIdGenerator sessionIdGenerator() {
		return new ShiroSessionIdGenerator();
	}
	/**
	 * 配置shiro redisManager, 使用的是shiro-redis开源插件
	 * @return RedisManager
	 */
	private RedisManager redisManager() {
		RedisManager redisManager = new RedisManager ();
		redisManager.setHost(host);
		redisManager.setPort(port);
		redisManager.setTimeout(timeout);
		if(StringUtils.isEmpty(password)) {
			redisManager.setPassword(null);
		}else{
			redisManager.setPassword(password);
		}
		//redisManager.setDatabase(0);
		redisManager.getJedisPool();
		return redisManager;
	}


	/**
	 * 配置Cache管理器：用于往Redis存储权限和角色标识  (使用的是shiro-redis开源插件)
	 */
	@Bean
	public RedisCacheManager
	cacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager();

		redisCacheManager.setRedisManager(redisManager());
		redisCacheManager.setKeyPrefix(CACHE_KEY);
		redisCacheManager.setExpire(sessionTimeout*60);
		// 配置缓存的话要求放在session里面的实体类必须有个id标识 注：这里id为用户表中的主键，否-> 报：User must has getter for field: xx
		redisCacheManager.setPrincipalIdFieldName("id");
		return redisCacheManager;
	}
	/**
	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	 */
	@Bean(name="shiroAdvisorAutoProxyCreator")
	public DefaultAdvisorAutoProxyCreator shiroAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	@Bean(name="shiroAuthorizationAttributeSourceAdvisor")
	public AuthorizationAttributeSourceAdvisor shiroAuthorizationAttributeSourceAdvisor(
			@Qualifier("shiroSecurityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
