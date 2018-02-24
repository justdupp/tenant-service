package hecc.cloud.tenant.controller;

import hecc.cloud.tenant.entity.CodeEntity;
import hecc.cloud.tenant.enumer.CodeTypeEnum;
import hecc.cloud.tenant.jpa.CodeRepository;
import hecc.cloud.tenant.jpa.TenantRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/code/create",method = RequestMethod.POST)
    public String crateCode(Long userId,String platform,CodeTypeEnum codeType){
        CodeEntity codeEntity = new CodeEntity();
        codeEntity.tenant = userId == null ? null:tenantRepository.findOne(userId);
        codeEntity.type = codeType;
        codeEntity.platform = platform;
        codeRepository.saveAndFlush(codeEntity);
        codeEntity.code = DigestUtils.sha1Hex(codeEntity.id+ "");
        codeRepository.save(codeEntity);
        return codeEntity.code;
    }
}
