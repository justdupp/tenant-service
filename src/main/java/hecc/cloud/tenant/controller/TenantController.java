package hecc.cloud.tenant.controller;

import hecc.cloud.tenant.client.QuickPassClient;
import hecc.cloud.tenant.entity.TenantEntity;
import hecc.cloud.tenant.jpa.TenantRepository;
import hecc.cloud.tenant.vo.AdminTenantVO;
import hecc.cloud.tenant.vo.TenantEntityVO;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther xuhoujun
 * @Description: 租户控制器
 * @Date: Created In 下午8:35 on 2018/2/25.
 */
@RestController
@RequestMapping("/tenant")
public class TenantController extends BaseController  {

    @Autowired
    TenantRepository tenantRepository;
    @Autowired
    QuickPassClient quickPassClient;

    @ApiOperation(value = "获取上层租户")
    @RequestMapping(value = "/getParent", method = RequestMethod.GET)
    public TenantEntityVO getParent(Long tenantId) {
        TenantEntity userEntity = tenantRepository.findOne(tenantId);
        return userEntity.parent == null ? null : new TenantEntityVO(userEntity.parent);
    }

    @ApiOperation(value = "获取相应的租户信息")
    @RequestMapping(value = "/{tenantId}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/{tenantId}/children", method = RequestMethod.GET)
    List<TenantEntityVO> getChildren(@PathVariable("tenantId") Long tenantId) {
        return tenantRepository.findByParentIdAndDelIsFalse(tenantId).stream()
                .map(tenant -> new TenantEntityVO(tenant))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "获取租户信息")
    @RequestMapping(value = "/tenantList", method = RequestMethod.GET)
    public ResponseVO fetchTenants(String platform, Long tenantId, String name) {
        if (tenantId != null) {
            TenantEntity tenant = tenantRepository.findOne(tenantId);
            return successed(tenant == null ? Collections.emptyList()
                    : Arrays.asList(new AdminTenantVO(tenant)));
        } else if (StringUtils.isNotBlank(platform) && StringUtils.isNotBlank(name)) {
            return successed(tenantRepository.findByNameAndPlatformAndDelIsFalse(name, platform).stream()
                    .map(tenant -> new AdminTenantVO(tenant)).collect(
                            Collectors.toList()));
        } else if (StringUtils.isBlank(platform) && StringUtils.isNotBlank(name)) {
            return successed(tenantRepository.findByNameAndDelIsFalse(name).stream()
                    .map(tenant -> new AdminTenantVO(tenant)).collect(
                            Collectors.toList()));
        } else if (StringUtils.isNotBlank(platform) && StringUtils.isBlank(name)) {
            return successed(tenantRepository.findByPlatformAndDelIsFalse(platform).stream()
                    .map(tenant -> new AdminTenantVO(tenant)).collect(
                            Collectors.toList()));
        } else {
            return failed("参数不能都为空", 1001);
        }
    }

}
