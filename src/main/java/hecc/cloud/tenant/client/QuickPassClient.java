package hecc.cloud.tenant.client;

import hecc.cloud.tenant.controller.BaseController.ResponseVO;
import hecc.cloud.tenant.vo.quickpass.CodeVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther xuhoujun
 * @Description: 闪付客户端接口
 * @Date: Created In 下午10:45 on 2018/2/27.
 */
@FeignClient("quickpass-service")
public interface QuickPassClient {

    @PostMapping("/domestic/tenant/default")
    CodeVO setDefaultTenant(@RequestParam("tenantId") Long tenantId);

    @PostMapping("/domestic/code/bind")
    CodeVO bindCode(@RequestParam("code") String code, @RequestParam("currentTenantId") Long currentTenantId);

    @RequestMapping(value = "/domestic/code", method = RequestMethod.POST)
    ResponseVO createCode(@RequestParam("tenantId") Long tenantId, @RequestParam("code") String code);

    @GetMapping("/domestic/tenant/{tenantId}/code")
    CodeVO getCodeByTenantId(@PathVariable("tenantId") Long tenantId);

    @GetMapping("/domestic/tenant/{tenantId}/codes")
    List<CodeVO> getCodeListByTenantId(@PathVariable("tenantId") Long tenantId);

    @RequestMapping(value = "/domestic/code/{code}/info", method = RequestMethod.POST)
    ResponseVO modifyCode(@PathVariable("code") String code);

    @RequestMapping(value = "/domestic/code/{code}/owner", method = RequestMethod.POST)
    ResponseVO modifyCodeOwner(@RequestParam("newOwnerId") Long newOwnerId, @PathVariable("code") String code);

    @GetMapping("/domestic/tenant/{tenantId}/isNormalUser")
    Boolean isCurrentTenantUseDefaultCode(@PathVariable("tenantId") Long tenantId);

    @PostMapping("/domestic/code/default")
    CodeVO setDefaultCode(@RequestParam("platform") String platform);

    @PostMapping("/domestic/code/top")
    void setTopCode(@RequestParam("tenantId") Long tenantId);

    @GetMapping("/domestic/code/default")
    List<CodeVO> fetchDefaultCodes();

}
