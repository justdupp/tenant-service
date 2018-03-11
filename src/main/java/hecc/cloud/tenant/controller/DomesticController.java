package hecc.cloud.tenant.controller;

import hecc.cloud.tenant.entity.CodeEntity;
import hecc.cloud.tenant.entity.TenantEntity;
import hecc.cloud.tenant.enumer.CodeTypeEnum;
import hecc.cloud.tenant.jpa.CodeRepository;
import hecc.cloud.tenant.jpa.TenantRepository;
import hecc.cloud.tenant.vo.TenantEntityVO;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Auther xuhoujun
 * @Description: 内部控制器
 * @Date: Created In 下午2:53 on 2018/2/24.
 */
@RestController
@RequestMapping("/domestic")
public class DomesticController extends BaseController {

    @Autowired
    private TenantRepository tenantRepository;
    @Autowired
    private CodeRepository codeRepository;

    @ApiOperation(value = "创建code")
    @RequestMapping(value = "/code/create",method = RequestMethod.POST)
    public String crateCode(Long tenantId, String platform, CodeTypeEnum codeType){
        CodeEntity codeEntity = new CodeEntity();
        codeEntity.tenant = tenantId == null ? null:tenantRepository.findOne(tenantId);
        codeEntity.type = codeType;
        codeEntity.platform = platform;
        codeRepository.saveAndFlush(codeEntity);
        // TODO: 2018/2/24 这里只是个雏形，code的id没有 暂时先用uuid代替
       // codeEntity.code = DigestUtils.sha1Hex(codeEntity.id+ "");
        codeEntity.code = UUID.randomUUID().toString();
        codeRepository.save(codeEntity);
        return codeEntity.code;
    }

    @ApiOperation(value = "获取上级租户信息")
    @RequestMapping(value = "/tenant/getParent", method = RequestMethod.GET)
    public TenantEntityVO getParent(Long tenantId) {
        TenantEntity tenantEntity = tenantRepository.findOne(tenantId);
        return tenantEntity.parent == null ? null : new TenantEntityVO(tenantEntity.parent);
    }

    @ApiOperation(value = "获取租户信息")
    @RequestMapping(value = "/tenant/{tenantId}", method = RequestMethod.GET)
    public TenantEntityVO getTenant(@PathVariable("tenantId") Long tenantId) {
        return new TenantEntityVO(tenantRepository.findOne(tenantId));
    }
}
