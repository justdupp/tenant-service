package hecc.cloud.tenant.client;

import hecc.cloud.tenant.client.vo.CodeVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther xuhoujun
 * @Description:  闪付客户端接口
 * @Date: Created In 下午10:45 on 2018/2/27.
 */
@FeignClient("quickpass-service")
public interface QuickPassClient {
    @PostMapping("/domestic/tenant/default")
    CodeVO setDefaultTenant(@RequestParam("tenantId") Long tenantId);

}
