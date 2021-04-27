package cn.edu.bjtu.jzlj.util.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName:
 * @Description: Swagger API 配置文档类
 * @Author ld
 * @Date 2019/9/9 15:43
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private String titleStr = "建筑垃圾接口 RESTful API";
    private String descriptionStr = "RESTful API 文档";
    private String versionStr = "0.0.1";
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title(titleStr)
                .description(descriptionStr)
                .version(versionStr)
                .build();

    }
}
