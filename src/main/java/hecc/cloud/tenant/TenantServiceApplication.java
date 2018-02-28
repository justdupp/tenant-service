package hecc.cloud.tenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TenantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantServiceApplication.class, args);
	}
}
