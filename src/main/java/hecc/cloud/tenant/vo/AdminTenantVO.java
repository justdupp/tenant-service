package hecc.cloud.tenant.vo;

import hecc.cloud.tenant.entity.TenantEntity;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * @author xuhoujun
 * @description: admin 租户VO
 * @date: Created In 下午10:41 on 2018/2/27.
 */
public class AdminTenantVO {

    private static final String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * id
     */
    public Long id;

    /**
     * 名称
     */
    public String name;

    /**
     * 身份证号码
     */
    public String idCard;

    /**
     * 手机号
     */
    public String mobile;

    /**
     * 平台名称
     */
    public String platform;

    /**
     * 创建时间
     */
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
