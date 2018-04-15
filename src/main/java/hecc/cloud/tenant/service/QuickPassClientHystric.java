package hecc.cloud.tenant.service;

import hecc.cloud.tenant.client.QuickPassClient;
import hecc.cloud.tenant.controller.BaseController;
import hecc.cloud.tenant.vo.quickpass.CodeVO;

import java.util.List;

/**
 * @author xuhoujun
 * @description: 闪付熔断器
 * @date: Created In 下午6:13 on 2018/4/6.
 */
public class QuickPassClientHystric implements QuickPassClient {

    @Override
    public void setDefaultTenant(Long tenantId) {
    }

    @Override
    public CodeVO bindCode(String code, Long currentTenantId) {
        return null;
    }

    @Override
    public BaseController.ResponseVO createCode(Long tenantId) {
        return null;
    }

    @Override
    public CodeVO getCodeByTenantId(Long tenantId) {
        return null;
    }

    @Override
    public List<CodeVO> getCodeListByTenantId(Long tenantId) {
        return null;
    }

    @Override
    public BaseController.ResponseVO modifyCode(String code) {
        return null;
    }

    @Override
    public BaseController.ResponseVO modifyCodeOwner(Long newOwnerId, String code) {
        return null;
    }

    @Override
    public Boolean isCurrentTenantUseDefaultCode(Long tenantId) {
        return null;
    }

    @Override
    public CodeVO setDefaultCode(String platform) {
        return null;
    }

    @Override
    public void setTopCode(Long tenantId) {

    }

    @Override
    public List<CodeVO> fetchDefaultCodes() {
        return null;
    }
}
