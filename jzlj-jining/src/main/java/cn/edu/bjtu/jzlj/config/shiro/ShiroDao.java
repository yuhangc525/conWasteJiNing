package cn.edu.bjtu.jzlj.config.shiro;


//import com.main.config.RedisConfig;
import cn.edu.bjtu.jzlj.config.RedisConfig;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

//https://blog.csdn.net/u013836676/article/details/80209922 ok
public class ShiroDao extends EnterpriseCacheSessionDAO {
	private Logger logger = LoggerFactory.getLogger(ShiroDao.class);
	/** session 超时时间 unit:分钟 */
	private Integer sessionTimeOut = 2;
	
	@Autowired
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;
	
	/** 
	 * key值出现\xac\xed\x00\x05t\x00前缀
	 * 查阅资料发现 RedisTemplate默认序列化方式用的是JdkSerializationRedisSerializer
	 * 此处修改为 Jackson2JsonRedisSerializer 序列化 */
	@Autowired(required = false)
	@SuppressWarnings("rawtypes")
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();//序列化为String
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);//序列化为Json
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        this.redisTemplate = redisTemplate;
    }
	
	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
		sessionId = RedisConfig.SHIRO_SESSION + "_" + sessionId;
		assignSessionId(session, sessionId);
		if (!StringUtils.isEmpty(session) && !StringUtils.isEmpty(session.getId())) {
			try {
				redisTemplate.opsForValue().set(sessionId,session,sessionTimeOut,TimeUnit.MINUTES);
			} catch (Exception e) {
			}
		}
		return sessionId;
	}
	
	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session result = null;
		try {
			Object object = redisTemplate.opsForValue().get(sessionId);
			if (null == object)
				return result;
			result = (Session) redisTemplate.opsForValue().get(sessionId);
		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
		}
		return result;
	}
	
	@Override
	protected void doUpdate(Session session) {
		if (!StringUtils.isEmpty(session) && !StringUtils.isEmpty(session.getId())) {
			try {
				String sessionId = session.getId().toString();
				redisTemplate.opsForValue().set(sessionId, session,sessionTimeOut,TimeUnit.MINUTES);
			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error(e.getMessage());
			} 
		}
	}

	@Override
	protected void doDelete(Session session) {
		if (!StringUtils.isEmpty(session) && !StringUtils.isEmpty(session.getId())) {
			try {
				String sessionId = session.getId().toString();
				redisTemplate.delete(sessionId);
			} catch (Exception e) {
//				e.printStackTrace();
//				logger.error(e.getMessage());
			}
		}
	}
}
