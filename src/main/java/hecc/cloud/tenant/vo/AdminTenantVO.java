package hecc.cloud.tenant.vo;

import hecc.cloud.tenant.entity.TenantEntity;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * @Auther xuhoujun
 * @Description: admin 租户VO
 * @Date: Created In 下午10:41 on 2018/2/27.
 */
public class AdminTenantVO {

    private static final String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public Long id;
    public String name;
    public String idCard;
    public String mobile;
    public String platform;
    public String createDate;

    public AdminTenantVO(TenantEntity tenant) {
        this.id = tenant.id;
        this.name = tenant.name;
        this.idCard = tenant.idCard;
        this.mobile = tenant.mobile;
        this.platform = tenant.platform;
        this.createDate = DateFormatUtils.format(tenant.createDate, DATE_TIME_FORMAT_PATTERN);
    }
}
