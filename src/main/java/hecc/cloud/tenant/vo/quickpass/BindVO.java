package hecc.cloud.tenant.vo.quickpass;

import hecc.cloud.tenant.entity.TenantEntity;
import org.apache.commons.lang.StringUtils;

/**
 * @author xuhoujun
 * @description: 绑定VO
 * @date: Created In 下午11:06 on 2018/3/14.
 */
public class BindVO {

    /**
     * 租户名称
     */
    public String name;
    /**
     * 手机号码
     */
    public String mobile;

    public BindVO(TenantEntity parent) {
        this.name = parent.name;
        this.mobile = StringUtils.trimToEmpty(parent.mobile);
    }
}
