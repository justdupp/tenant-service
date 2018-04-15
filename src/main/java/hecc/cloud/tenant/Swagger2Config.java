package hecc.cloud.tenant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xuhoujun
 * @description: swagger配置中心
 * @date: Created In 下午1:23 on 2018/2/24.
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Value("${tenant-swagger2.hostname}")
    private String hostName;

    @Value("${tenant-swagger2.isEnable}")
    private boolean isEnable;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).enable(isEnable)
                .apiInfo(apiInfo()).host(hostName).select()
                .apis(RequestHandlerSelectors.basePackage("hecc.cloud.tenant"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("租户业务中心 APIS")
                .description("具体内容详见：http://xuhoujun.com")
                .termsOfServiceUrl("http://xuhoujun.com")
                .version("1.0").build();
    }
}
