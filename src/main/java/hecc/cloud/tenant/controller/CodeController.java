package hecc.cloud.tenant.controller;

import hecc.cloud.tenant.client.QuickPassClient;
import hecc.cloud.tenant.client.vo.CodeVO;
import hecc.cloud.tenant.entity.TenantEntity;
import hecc.cloud.tenant.jpa.TenantRepository;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther xuhoujun
 * @Description: 码控制器
 * @Date: Created In 下午10:24 on 2018/3/12.
 */
@RestController
@RequestMapping("/code")
public class CodeController extends BaseController {

    @Autowired
    private QuickPassClient quickPassClient;
    @Autowired
    private TenantRepository tenantRepository;

    @ApiOperation(value = "更新码信息")
    @RequestMapping(value = "/modifyCode", method = RequestMethod.POST)
    public ResponseVO modifyCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            quickPassClient.modifyCode(code);
            return successed(null);
        } else {
            return failed("code不能为空", 1001);
        }
    }

    @ApiOperation(value = "更新码租户")
    @RequestMapping(value = "/modifyCodeOwner", method = RequestMethod.POST)
    public ResponseVO modifyCodeOwner(Long ownerId, String code) {
        if (ownerId != null && StringUtils.isNotBlank(code)) {
            quickPassClient.modifyCodeOwner(ownerId, code);
            return successed(null);
        } else {
            return failed("ownerId或code不能为空", 1001);
        }
    }

    @ApiOperation(value = "设置默认码")
    @RequestMapping(value = "/setDefaultCode", method = RequestMethod.POST)
    public ResponseVO setDefaultCode(String platform) {
        CodeVO codeVO = quickPassClient.setDefaultCode(platform);
        if (codeVO == null) {
            return failed("已经建过默认码", 1001);
        } else {
            return successed(null);
        }
    }

    @ApiOperation(value = "设置顶级码")
    @RequestMapping(value = "/setTopCode", method = RequestMethod.POST)
    public ResponseVO setTopCode(Long tenantId) {
        TenantEntity tenant = tenantRepository.findOne(tenantId);
        if (BooleanUtils.isTrue(tenant.topTenant)) {
            quickPassClient.setTopCode(tenantId);
            return successed(null);
        } else {
            return failed("不是顶级租户", 1001);
        }
    }

    @ApiOperation(value = "获取默认码")
    @RequestMapping(value = "/fetchDefaultCodes", method = RequestMethod.GET)
    public ResponseVO fetchDefaultCodes() {
        return successed(quickPassClient.fetchDefaultCodes());
    }


}
