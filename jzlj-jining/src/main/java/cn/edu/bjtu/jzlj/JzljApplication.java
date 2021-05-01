package cn.edu.bjtu.jzlj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableConfigurationProperties
@MapperScan("cn.edu.bjtu.jzlj.mapper")
public class JzljApplication {

	public static void main(String[] args) {
		SpringApplication.run(JzljApplication.class, args);
	}

}
