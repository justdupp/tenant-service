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

import static java.util.stream.Collectors.toList;

/**
 * @Auther xuhoujun
 * @Description: 租户控制器
 * @Date: Created In 下午8:35 on 2018/2/25.
 */
@RestController
@RequestMapping("/tenant")
public class TenantController extends BaseController {

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
    public void updateTenant(@RequestBody TenantEntityVO tenant) {
        TenantEntity domestic = tenantRepository.findOne(tenant.id);
        if (tenant.receiverBankAccount != null) {
            domestic.receiverBankAccount = tenant.receiverBankAccount;
        }
        if (tenant.receiverBankName != null) {
            domestic.receiverBankName = tenant.receiverBankName;
        }
        if (tenant.mobile != null) {
            domestic.mobile = tenant.mobile;
        }
        if (tenant.name != null) {
            domestic.name = tenant.name;
        }
        if (tenant.idCard != null) {
            domestic.idCard = tenant.idCard;
        }
        if (tenant.parentId != null) {
            domestic.parent = tenantRepository.findOne(tenant.parentId);
        }
        tenantRepository.saveAndFlush(domestic);
    }

    @ApiOperation(value = "获取子租户信息")
    @RequestMapping(value = "/{tenantId}/children", method = RequestMethod.GET)
    List<TenantEntityVO> getChildren(@PathVariable("tenantId") Long tenantId) {
        return tenantRepository.findByParentIdAndDelIsFalse(tenantId).stream()
                .map(tenant -> new TenantEntityVO(tenant))
                .collect(toList());
    }

    @ApiOperation(value = "获取租户信息")
    @RequestMapping(value = "/tenantList", method = RequestMethod.GET)
    public ResponseVO fetchTenants(String platform, Long tenantId, String name) {
        if (tenantId != null) {
            TenantEntity tenant = tenantRepository.findOne(tenantId);
            return succeed(tenant == null ? Collections.emptyList()
                    : Arrays.asList(new AdminTenantVO(tenant)));
        } else if (StringUtils.isNotBlank(platform) && StringUtils.isNotBlank(name)) {
            return succeed(tenantRepository.findByNameAndPlatformAndDelIsFalse(name, platform).stream()
                    .map(tenant -> new AdminTenantVO(tenant)).collect(toList()));
        } else if (StringUtils.isBlank(platform) && StringUtils.isNotBlank(name)) {
            return succeed(tenantRepository.findByNameAndDelIsFalse(name).stream()
                    .map(tenant -> new AdminTenantVO(tenant)).collect(toList()));
        } else if (StringUtils.isNotBlank(platform) && StringUtils.isBlank(name)) {
            return succeed(tenantRepository.findByPlatformAndDelIsFalse(platform).stream()
                    .map(tenant -> new AdminTenantVO(tenant)).collect(toList()));
        } else {
            return failed("参数不能都为空", ERROR_VALID_FAILED);
        }
    }


    @ApiOperation(value = "设置默认租户")
    @RequestMapping(value = "/setDefaultTenant", method = RequestMethod.POST)
    public ResponseVO setDefaultUser(Long tenantId) {
        TenantEntity newTopTenant = tenantRepository.findOne(tenantId);
        TenantEntity oldTopTenant = tenantRepository
                .findOneByPlatformAndTopTenantIsTrueAndDelIsFalse(newTopTenant.platform);
        if (oldTopTenant != null) {
            return failed("已经存在顶级租户", ERROR_VALID_FAILED);
        } else {
            newTopTenant.topTenant = true;
            tenantRepository.saveAndFlush(newTopTenant);
            quickPassClient.setDefaultTenant(tenantId);
            tenantRepository.findByPlatformAndParentIsNullAndDelIsFalse(newTopTenant.platform).forEach(u -> {
                if (!u.id.equals(newTopTenant.id)) {
                    u.parent = newTopTenant;
                    tenantRepository.save(u);
                }
            });
            return succeed(null);
        }
    }

    @ApiOperation(value = "获取默认租户")
    @RequestMapping(value = "/fetchDefaultTenant", method = RequestMethod.GET)
    public ResponseVO fetchDefaultTenant() {
        return succeed(tenantRepository.findByTopTenantIsTrueAndDelIsFalse().stream()
                .map(tenant -> new AdminTenantVO(tenant))
                .collect(toList()));
    }

    @RequestMapping(value = "/isTopTenant", method = RequestMethod.GET)
    boolean getIsTopTenant(@RequestParam("tenantId") Long tenantId){
        TenantEntity entity =  tenantRepository.findOne(tenantId);
        return entity.topTenant;
    }

}
