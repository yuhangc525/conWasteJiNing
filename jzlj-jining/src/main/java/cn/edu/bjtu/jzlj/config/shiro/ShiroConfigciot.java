package cn.edu.bjtu.jzlj.config.shiro;

import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfigciot {
//
//	@Bean(name="shiroFilterFactoryBean")
//	public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("shiroSecurityManager")SecurityManager securityManager) {
//
//		//shiroFilterFactoryBean对象
//		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//		// 配置shiro安全管理器 SecurityManager
//		shiroFilterFactoryBean.setSecurityManager(securityManager);
//		// 指定要求登录时的链接
//		shiroFilterFactoryBean.setLoginUrl("/");
//
//		//添加自定义拦截器
//		Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
//		filters.put("authc", new MyShiroAuthenticationFilter());
////		filters.put("anon", new AnonymousFilter());
//		shiroFilterFactoryBean.setFilters(filters);
//
//		// filterChainDefinitions拦截器=map必须用：LinkedHashMap，因为它必须保证有序
//		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//		//filterChainDefinitionMap.put("/", "anon");
//
//		filterChainDefinitionMap.put("/static/**", "anon");
//		filterChainDefinitionMap.put("/user/login", "anon");
//		filterChainDefinitionMap.put("/user/wxlogin", "anon");
//		filterChainDefinitionMap.put("/tx/**", "anon");
//		filterChainDefinitionMap.put("/product/getProductBySpatialQuery", "anon");
//		filterChainDefinitionMap.put("/product/getAllProducts", "anon");
//		filterChainDefinitionMap.put("/product/getProductById", "anon");
//		filterChainDefinitionMap.put("/product/getPic", "anon");
//		//filterChainDefinitionMap.put("/map/**", "anon");
//		//consul健康检查放行
//		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
//		// filterChainDefinitionMap.put("/**", "anon");
//	    filterChainDefinitionMap.put("/**", "authc");
//
//		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//		return shiroFilterFactoryBean;
//	}
//
//	@Bean("shiroSecurityManager")
//	public SecurityManager shiroSecurityManager(
//			@Qualifier("shiroRealm")ShiroRealm myShiroRealm,MiniRealm miniRealm,
//			@Qualifier("shiroSessionManager")DefaultWebSessionManager sessionManager) {
//
//		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//		//禁止地址栏中出现;JSESSIONID=shiro_session_e26690b8-c25b-4564-816a-622fcb7c5160
//		sessionManager.setSessionIdUrlRewritingEnabled(false);
//		// 设置realm.
//		securityManager.setRealm(myShiroRealm);
//		// //注入ehcache缓存管理器;
////		securityManager.setCacheManager(ehCacheManager());
//		// //注入session管理器;
//		securityManager.setSessionManager(sessionManager);
//		//注入Cookie记住我管理器
//		securityManager.setRememberMeManager(rememberMeManager());
//
//		//新增部分多realm配置  前端后台不同得验证   @chenkang
//		ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
//		//至少成功一个都算认证通过得
//		modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
//		securityManager.setAuthenticator(modularRealmAuthenticator);
//		List<Realm> realms = new ArrayList<>();
//		realms.add(myShiroRealm);
//		realms.add(miniRealm);
//		securityManager.setRealms(realms);
//		//新增部分多realm配置  前端后台不同得验证   @chenkang
//
//		//securityManager.setRealm(shiroRealm);
//		// 配置 shiro session管理器
//		securityManager.setSessionManager(sessionManager);
//
//		// 配置 rememberMeCookie
//		securityManager.setRememberMeManager(rememberMeManager());
//
//		return securityManager;
//	}
//
//	@SuppressWarnings("deprecation")
//	@Bean
//	public CookieRememberMeManager rememberMeManager() {
//		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//		cookieRememberMeManager.setCookie(rememberMeCookie());
////		byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
////		cookieRememberMeManager.setCipherKey(Base64.decode("12asdf234asdfasdf=="));
//		return cookieRememberMeManager;
//	}
//
//	@Bean
//	public SimpleCookie rememberMeCookie() {
//		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//		//30天
//		simpleCookie.setMaxAge(259200);
//		return simpleCookie;
//	}
//
//	@Bean(name="shiroRealm")
//	public ShiroRealm shiroRealm() {
//		ShiroRealm shiroRealm = new ShiroRealm();
//		return shiroRealm;
//	}
//
//	/*
//	@Bean(name="shiroSessionManager")
//	public DefaultWebSessionManager shiroSessionManager(
//			@Qualifier("shiroSessionIdCookie") SimpleCookie simpleCookie,
//			@Qualifier("shiroDao") RedisSessionDAO shiroDao ) {
//		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//		sessionManager.setSessionIdCookie(simpleCookie);
//		sessionManager.setSessionDAO(shiroDao);
////		sessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
//		return sessionManager;
//	}
//	*/
//	@Bean(name="shiroSessionManager")
//	public DefaultWebSessionManager shiroSessionManager(
//			@Qualifier("shiroSessionIdCookie") SimpleCookie simpleCookie,
//			@Qualifier("shiroDao") ShiroDao shiroDao
//			) {
//		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//		sessionManager.setSessionIdCookie(simpleCookie);
//		sessionManager.setSessionDAO(shiroDao);
////		sessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
//		return sessionManager;
//	}
//
//	@Bean(name="shiroDao")
//	public ShiroDao shiroDao() {
//		return new ShiroDao();
//	}
//
//	@Bean(name="shiroSessionIdCookie")
//	public SimpleCookie shiroSessionIdCookie() {
//		//DefaultSecurityManager
//		SimpleCookie simpleCookie = new SimpleCookie();
//		//sessionManager.setCacheManager(ehCacheManager());
//		//如果在Cookie中设置了"HttpOnly"属性，那么通过程序(JS脚本、Applet等)将无法读取到Cookie信息，这样能有效的防止XSS攻击。
//		simpleCookie.setHttpOnly(true);
//		simpleCookie.setName("SHRIOSESSIONID");
//		//单位秒
////		simpleCookie.setMaxAge(86400);
//		return simpleCookie;
//	}
//
//	/**
//	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
//	 */
//	@Bean(name="shiroAdvisorAutoProxyCreator")
//	public DefaultAdvisorAutoProxyCreator shiroAdvisorAutoProxyCreator() {
//		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//		advisorAutoProxyCreator.setProxyTargetClass(true);
//		return advisorAutoProxyCreator;
//	}
//
//	@Bean(name="shiroAuthorizationAttributeSourceAdvisor")
//	public AuthorizationAttributeSourceAdvisor shiroAuthorizationAttributeSourceAdvisor(
//			@Qualifier("shiroSecurityManager") SecurityManager securityManager) {
//		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//		return authorizationAttributeSourceAdvisor;
//	}
}
