package hecc.cloud.tenant.vo.quickpass;

import hecc.cloud.tenant.entity.TenantEntity;
import org.apache.commons.lang.StringUtils;

/**
 * @Auther xuhoujun
 * @Description: 绑定VO
 * @Date: Created In 下午11:06 on 2018/3/14.
 */
public class BindVO {
    public String name;
    public String mobile;

    public BindVO(TenantEntity parent) {
        this.name = parent.name;
        this.mobile = StringUtils.trimToEmpty(parent.mobile);
    }
}
