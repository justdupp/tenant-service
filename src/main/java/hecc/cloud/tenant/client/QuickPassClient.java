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

    /**
     * 设置默认租户
     *
     * @param tenantId
     * @return codeVO
     */
    @PostMapping("/domestic/tenant/default")
    CodeVO setDefaultTenant(@RequestParam("tenantId") Long tenantId);

    /**
     * 绑码操作
     *
     * @param code            码
     * @param currentTenantId 租户id
     * @return codeVO
     */
    @PostMapping("/domestic/code/bind")
    CodeVO bindCode(@RequestParam("code") String code, @RequestParam("currentTenantId") Long currentTenantId);

    /**
     * 创建码
     *
     * @param tenantId 租户id
     * @param code     码
     * @return
     */
    @RequestMapping(value = "/domestic/code", method = RequestMethod.POST)
    ResponseVO createCode(@RequestParam("tenantId") Long tenantId, @RequestParam("code") String code);

    /**
     * 根据租户id获取码
     *
     * @param tenantId 租户id
     * @return codeVO
     */
    @GetMapping("/domestic/tenant/{tenantId}/code")
    CodeVO getCodeByTenantId(@PathVariable("tenantId") Long tenantId);

    /**
     * 根据租户id获取码列表
     *
     * @param tenantId 租户id
     * @return List<CodeVO>
     */
    @GetMapping("/domestic/tenant/{tenantId}/codes")
    List<CodeVO> getCodeListByTenantId(@PathVariable("tenantId") Long tenantId);

    /**
     * 修改码
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/domestic/code/{code}/info", method = RequestMethod.POST)
    ResponseVO modifyCode(@PathVariable("code") String code);

    /**
     * 修改码所属租户
     *
     * @param newOwnerId 所属租户
     * @param code       码
     * @return
     */
    @RequestMapping(value = "/domestic/code/{code}/owner", method = RequestMethod.POST)
    ResponseVO modifyCodeOwner(@RequestParam("newOwnerId") Long newOwnerId, @PathVariable("code") String code);

    /**
     * 该租户是否使用默认码
     *
     * @param tenantId 租户id
     * @return yes or no
     */
    @GetMapping("/domestic/tenant/{tenantId}/isNormalUser")
    Boolean isCurrentTenantUseDefaultCode(@PathVariable("tenantId") Long tenantId);

    /**
     * 设置默认码
     *
     * @param platform 平台名称
     * @return CodeVO
     */
    @PostMapping("/domestic/code/default")
    CodeVO setDefaultCode(@RequestParam("platform") String platform);

    /**
     * 设置顶级码
     *
     * @param tenantId 租户id
     */
    @PostMapping("/domestic/code/top")
    void setTopCode(@RequestParam("tenantId") Long tenantId);

    /**
     * 获取默认码列表
     *
     * @return List<CodeVO>
     */
    @GetMapping("/domestic/code/default")
    List<CodeVO> fetchDefaultCodes();

}
