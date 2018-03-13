package hecc.cloud.tenant.controller;

import hecc.cloud.tenant.entity.TenantEntity;
import hecc.cloud.tenant.jpa.TenantRepository;
import hecc.cloud.tenant.vo.TenantEntityVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @Auther xuhoujun
 * @Description: API控制器
 * @Date: Created In 下午9:03 on 2018/3/13.
 */
@RestController
@RequestMapping("/api/tenant/")
public class APIController extends  BaseController{

    @Autowired
    private TenantRepository tenantRepository;

    @ApiOperation(value = "获取租户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseVO getInfo(@RequestHeader Long tenantId) {
        TenantEntity tenant = tenantRepository.findOne(tenantId);
        return successed(new TenantEntityVO(tenant));
    }

    @ApiOperation(value = "上传图片")
    @RequestMapping(value = "/uploadPics", method = RequestMethod.POST)
    public ResponseVO uploadPics(@RequestHeader Long userId, @NotNull String idCardFontPic,
                                 @NotNull String idCardBackPicUrl) {
        TenantEntity tenant = tenantRepository.findOne(userId);
        tenant.idCardFontPic = idCardFontPic;
        tenant.idCardBackPic = idCardBackPicUrl;
        tenantRepository.save(tenant);
        return successed(null);
    }
}
