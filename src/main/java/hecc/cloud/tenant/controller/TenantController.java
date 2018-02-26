package hecc.cloud.tenant.controller;

import hecc.cloud.tenant.entity.TenantEntity;
import hecc.cloud.tenant.jpa.TenantRepository;
import hecc.cloud.tenant.vo.TenantEntityVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @ApiOperation(value = "更新租户信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateTenant(@RequestBody TenantEntityVO user) {
        TenantEntity domestic = tenantRepository.findOne(user.id);
        if (user.receiverBankAccount != null) {
            domestic.receiverBankAccount = user.receiverBankAccount;
        }
        if (user.receiverBankName != null) {
            domestic.receiverBankName = user.receiverBankName;
        }
        if (user.mobile != null) {
            domestic.mobile = user.mobile;
        }
        if (user.name != null) {
            domestic.name = user.name;
        }
        if (user.idCard != null) {
            domestic.idCard = user.idCard;
        }
        if (user.parent_id != null) {
            domestic.parent = tenantRepository.findOne(user.parent_id);
        }
        tenantRepository.saveAndFlush(domestic);
    }

    @ApiOperation(value = "获取子租户信息")
    @RequestMapping(value = "/tenant/{tenantId}/children", method = RequestMethod.GET)
    List<TenantEntityVO> getChildren(@PathVariable("tenantId") Long tenantId) {
        return tenantRepository.findByParentIdAndDelIsFalse(tenantId).stream()
                .map(tenant -> new TenantEntityVO(tenant))
                .collect(Collectors.toList());
    }

}
