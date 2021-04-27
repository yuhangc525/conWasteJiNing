package cn.edu.bjtu.jzlj.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: ld
 * @Description:springboot 工具类
 * @Date 2019/5/7 13:51
 * @Param null
 * @return
 * @throws:
 **/
public class SpringUtil implements ApplicationContextAware, DisposableBean
{
    // 静态上下文变量
	private static ApplicationContext applicationContext = null;

	// 重载赋值
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
	{
		SpringUtil.applicationContext = applicationContext;
	}

	// 重载释放
	@Override
	public void destroy() throws Exception	{ applicationContext = null;	}

	/**
	 * @Author: ld
	 * @Description: 获取上下文对象
	 * @Date 2019/5/7 13:53
	 * @Param
	 * @return org.springframework.context.ApplicationContext
	 * @throws:
	 **/
	public static ApplicationContext getApplicationContext()
	{
		return applicationContext;
	}

	/**
	 * @Author: ld
	 * @Description: 通过名字获取bean
	 * @Date 2019/5/7 13:53
	 * @Param name，参数名字
	 * @return java.lang.Object
	 * @throws:
	 **/
	public static Object getBean(String name){return getApplicationContext().getBean(name);
	}

	/**
	 * @Author: ld
	 * @Description: 通过类返回泛型bean
	 * @Date 2019/5/7 13:55
	 * @Param clazz
	 * @return T
	 * @throws:
	 **/
	public static <T> T getBean(Class<T> clazz)
	{
		return getApplicationContext().getBean(clazz);
	}

	/**
	 * 通过name,以及Clazz返回指定的Bean
	 * @param name
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T getBean(String name,Class<T> clazz)
	{
		return getApplicationContext().getBean(name, clazz);
	}
}