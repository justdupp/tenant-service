package hecc.cloud.tenant.controller;

import hecc.cloud.tenant.client.QuickPassClient;
import hecc.cloud.tenant.client.vo.BindVO;
import hecc.cloud.tenant.entity.CodeEntity;
import hecc.cloud.tenant.entity.TenantEntity;
import hecc.cloud.tenant.jpa.CodeRepository;
import hecc.cloud.tenant.jpa.TenantRepository;
import hecc.cloud.tenant.service.AuthCardService;
import hecc.cloud.tenant.vo.TenantEntityVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * @Auther xuhoujun
 * @Description: API控制器
 * @Date: Created In 下午9:03 on 2018/3/13.
 */
@RestController
@RequestMapping("/api/tenant/")
public class APIController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(APIController.class);

    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private AuthCardService authCardService;
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private QuickPassClient quickPassClient;

    @ApiOperation(value = "获取租户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseVO getInfo(@RequestHeader Long tenantId) {
        TenantEntity tenant = tenantRepository.findOne(tenantId);
        return successed(new TenantEntityVO(tenant));
    }

    @ApiOperation(value = "上传图片")
    @RequestMapping(value = "/uploadPics", method = RequestMethod.POST)
    public ResponseVO uploadPics(@RequestHeader Long tenantId, @NotNull String idCardFontPic,
                                 @NotNull String idCardBackPicUrl) {
        TenantEntity tenant = tenantRepository.findOne(tenantId);
        tenant.idCardFontPic = idCardFontPic;
        tenant.idCardBackPic = idCardBackPicUrl;
        tenantRepository.save(tenant);
        return successed(null);
    }

    @ApiOperation(value = "鉴权操作")
    @RequestMapping(value = "/authCard", method = RequestMethod.POST)
    public ResponseVO authCard(String username, String account, String idCard, String mobile) {
        try {
            AuthCardService.AuthResponseVO responseVO = authCardService.auth(username, account, idCard, mobile);
            if (responseVO.isSuccess) {
                return successed(null);
            } else {
                return failed(responseVO.msg, ERROR_CODE_AUTH_FAILED);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return failed("鉴权失败,请稍后重试", ERROR_CODE_AUTH_FAILED);
        }
    }

    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    public ResponseVO bind(@RequestHeader Long tenantId, @NotNull String code) {
        TenantEntity tenant = tenantRepository.findOne(tenantId);
        CodeEntity codeEntity = codeRepository.findOneByCodeAndDelIsFalse(code);
        if (codeEntity == null) {
            return failed("码不存在或已被删除", ERROR_BIND_CODE_NOT_FOUND);
        }
        if (!codeEntity.platform.equals(tenant.platform)) {
            return failed("您不能绑定此码", ERROR_BIND_CODE_WRONG_PLATFORM);
        }
        switch (codeEntity.type) {
            case QUICK_PASS:
                if (!quickPassClient.isCurrentTenantUseDefaultCode(tenantId) && !codeEntity.tenant.id.equals(tenant.parent.id)) {
                    return failed(String.format("您不能绑定此码  %s[%s]",
                                    tenant.parent.name, tenant.parent.mobile), ERROR_BIND_CODE_FAILED);
                }
                tenant.parent = codeEntity.tenant;
                tenantRepository.save(tenant);
                return successed(new BindVO( tenant.parent));
            case CREDIT_CARD:
                //TODO: 去网银系统进行绑定
                break;
        }
        return successed(null);
    }
}
