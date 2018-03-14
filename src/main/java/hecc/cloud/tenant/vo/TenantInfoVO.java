package hecc.cloud.tenant.vo;

import hecc.cloud.tenant.entity.TenantEntity;

/**
 * @Auther xuhoujun
 * @Description: 租户信息VO
 * @Date: Created In 上午12:31 on 2018/3/15.
 */
public class TenantInfoVO {
    public String name;
    public String idCard;
    public String bankAccountNumber;
    public String bankName;
    public String mobile;

    public TenantInfoVO(TenantEntity tenant) {
        this.name = tenant.name;
        this.idCard = tenant.idCard;
        this.bankAccountNumber = tenant.receiverBankAccount;
        this.bankName = tenant.receiverBankName;
        this.mobile = tenant.mobile;
    }
}
