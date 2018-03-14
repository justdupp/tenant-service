package hecc.cloud.tenant;

import okhttp3.OkHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
public class TenantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenantServiceApplication.class, args);
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }
}
