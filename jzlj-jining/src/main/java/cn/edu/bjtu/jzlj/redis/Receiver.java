package cn.edu.bjtu.jzlj.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class Receiver {
    public void receiver(){
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "8.135.100.109", 6379, 10000, "8401A@redis");
        System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", "8.135.100.109", 6379));
        SubThread subThread = new SubThread(jedisPool);  //订阅者
        subThread.start();
    }
}
