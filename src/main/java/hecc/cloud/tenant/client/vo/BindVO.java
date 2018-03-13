package hecc.cloud.tenant.client.vo;

import hecc.cloud.tenant.entity.TenantEntity;
import org.apache.commons.lang.StringUtils;

/**
 * @Auther xuhoujun
 * @Description:
 * @Date: Created In 下午10:32 on 2018/3/13.
 */
public class BindVO {
    public String name;
    public String mobile;

    public BindVO(TenantEntity parent) {
        this.name = parent.name;
        this.mobile = StringUtils.trimToEmpty(parent.mobile);
    }
}
