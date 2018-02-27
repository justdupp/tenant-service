package hecc.cloud.tenant.controller;

import hecc.cloud.tenant.entity.TenantEntity;
import hecc.cloud.tenant.jpa.TenantRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther xuhoujun
 * @Description:  admin控制器
 * @Date: Created In 下午8:51 on 2018/2/27.
 */
@RestController
@RequestMapping("/admin/")
public class AdminController extends BaseController {

    private static final Integer CODE = 1;

    @Autowired
    private TenantRepository tenantRepository;

    @ApiOperation(value = "设置默认租户")
    @RequestMapping(value = "/setDefaultTenant", method = RequestMethod.POST)
    public ResponseVO setDefaultUser(Long tenantId) {
        TenantEntity newTopTenant = tenantRepository.findOne(tenantId);
        TenantEntity oldTopTenant = tenantRepository
                .findOneByPlatformAndTopTenantIsTrueAndDelIsFalse(newTopTenant.platform);
        if (oldTopTenant != null) {
            return failed("已经存在顶级租户", CODE);
        } else {
            newTopTenant.topTenant = true;
            tenantRepository.saveAndFlush(newTopTenant);
            // TODO: 2018/2/27  quickpass客户段   待添加
            tenantRepository.findByPlatformAndParentIsNullAndDelIsFalse(newTopTenant.platform).forEach(u -> {
                if (!u.id.equals(newTopTenant.id)) {
                    u.parent = newTopTenant;
                    tenantRepository.save(u);
                }
            });
            return successed(null);
        }
    }
}
