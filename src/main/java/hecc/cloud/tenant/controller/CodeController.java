package hecc.cloud.tenant.controller;

import hecc.cloud.tenant.client.QuickPassClient;
import hecc.cloud.tenant.entity.CodeEntity;
import hecc.cloud.tenant.entity.TenantEntity;
import hecc.cloud.tenant.enumer.CodeTypeEnum;
import hecc.cloud.tenant.jpa.CodeRepository;
import hecc.cloud.tenant.jpa.TenantRepository;
import hecc.cloud.tenant.vo.quickpass.CodeVO;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhoujun
 * @description: 码控制器
 * @date: Created In 下午10:24 on 2018/3/12.
 */
@RestController
@RequestMapping("/code")
public class CodeController extends BaseController {

    @Autowired
    private QuickPassClient quickPassClient;
    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private CodeRepository codeRepository;

    @ApiOperation(value = "更新码信息")
    @RequestMapping(value = "/modifyCode", method = RequestMethod.POST)
    public ResponseVO modifyCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            quickPassClient.modifyCode(code);
            return succeed(null);
        } else {
            return failed("code不能为空", ERROR_BIND_CODE_FAILED);
        }
    }

    @ApiOperation(value = "更新码租户")
    @RequestMapping(value = "/modifyCodeOwner", method = RequestMethod.POST)
    public ResponseVO modifyCodeOwner(Long ownerId, String code) {
        if (ownerId != null && StringUtils.isNotBlank(code)) {
            quickPassClient.modifyCodeOwner(ownerId, code);
            return succeed(null);
        } else {
            return failed("ownerId或code不能为空", ERROR_BIND_CODE_FAILED);
        }
    }

    @ApiOperation(value = "设置默认码")
    @RequestMapping(value = "/setDefaultCode", method = RequestMethod.POST)
    public ResponseVO setDefaultCode(String platform) {
        CodeVO codeVO = quickPassClient.setDefaultCode(platform);
        if (codeVO == null) {
            return failed("已经建过默认码", ERROR_BIND_CODE_FAILED);
        } else {
            return succeed(null);
        }
    }

    @ApiOperation(value = "设置顶级码")
    @RequestMapping(value = "/setTopCode", method = RequestMethod.POST)
    public ResponseVO setTopCode(Long tenantId) {
        TenantEntity tenant = tenantRepository.findOne(tenantId);
        if (BooleanUtils.isTrue(tenant.topTenant)) {
            quickPassClient.setTopCode(tenantId);
            return succeed(null);
        } else {
            return failed("不是顶级租户", ERROR_BIND_CODE_FAILED);
        }
    }

    @ApiOperation(value = "获取默认码")
    @RequestMapping(value = "/fetchDefaultCodes", method = RequestMethod.GET)
    public ResponseVO fetchDefaultCodes() {
        return succeed(quickPassClient.fetchDefaultCodes());
    }

    @ApiOperation(value = "获取码列表")
    @RequestMapping(value = "/codes", method = RequestMethod.GET)
    public ResponseVO fetchCodes(Long tenantId) {
        if (tenantId != null) {
            return succeed(quickPassClient.getCodeListByTenantId(tenantId));
        } else {
            return failed("tenantId不能为空", ERROR_BIND_CODE_FAILED);
        }
    }

    @ApiOperation(value = "绑码")
    @RequestMapping(value = "/bindCode", method = RequestMethod.POST)
    public ResponseVO bindCode(Long tenantId, String code) {
        if (tenantId != null && StringUtils.isNotBlank(code)) {
            TenantEntity tenant = tenantRepository.findOne(tenantId);
            CodeEntity codeEntity = codeRepository.findOneByCodeAndDelIsFalse(code);
            tenant.parent = codeEntity.tenant;
            tenantRepository.save(tenant);
            quickPassClient.bindCode(code, tenantId);
            return succeed(null);
        } else {
            return failed("tenantId或codeId不能为空", ERROR_BIND_CODE_FAILED);
        }
    }

    @ApiOperation(value = "创建code")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String crateCode(Long tenantId, String platform, CodeTypeEnum codeType) {
        CodeEntity codeEntity = new CodeEntity();
        codeEntity.tenant = tenantId == null ? null : tenantRepository.findOne(tenantId);
        codeEntity.type = codeType;
        codeEntity.platform = platform;
        codeRepository.saveAndFlush(codeEntity);
        codeEntity.code = DigestUtils.sha1Hex(codeEntity.id + "");
        codeRepository.save(codeEntity);
        return codeEntity.code;
    }


}
