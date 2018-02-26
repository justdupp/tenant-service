package hecc.cloud.tenant.controller;

import hecc.cloud.tenant.entity.TenantEntity;
import hecc.cloud.tenant.jpa.TenantRepository;
import hecc.cloud.tenant.vo.TenantEntityVO;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "获取上层租户")
    @RequestMapping(value = "/tenant/getParent", method = RequestMethod.GET)
    public TenantEntityVO getParent(Long tenantId) {
        TenantEntity userEntity = tenantRepository.findOne(tenantId);
        return userEntity.parent == null ? null : new TenantEntityVO(userEntity.parent);
    }

    @ApiOperation(value = "获取相应的租户信息")
    @RequestMapping(value = "/tenant/{tenantId}", method = RequestMethod.GET)
    public TenantEntityVO getTenant(@PathVariable("tenantId") Long tenantId) {
        return new TenantEntityVO(tenantRepository.findOne(tenantId));
    }

}
