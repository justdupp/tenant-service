package hecc.cloud.tenant.controller;

import hecc.cloud.tenant.entity.TenantEntity;
import hecc.cloud.tenant.jpa.TenantRepository;
import hecc.cloud.tenant.vo.TenantEntityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther xuhoujun
 * @Description: 租户控制器
 * @Date: Created In 下午8:35 on 2018/2/25.
 */
@RestController
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    TenantRepository tenantRepository;

    @RequestMapping(value = "/user/getParent", method = RequestMethod.GET)
    public TenantEntityVO getParent(Long userId) {
        TenantEntity userEntity = tenantRepository.findOne(userId);
        return userEntity.parent == null ? null : new TenantEntityVO(userEntity.parent);
    }

}
