package hecc.cloud.tenant.vo;

import hecc.cloud.tenant.entity.TenantEntity;

/**
 * @author xuhoujun
 * @description: 租户信息VO
 * @date: Created In 上午12:31 on 2018/3/15.
 */
public class TenantInfoVO {

    /**
     * 租户名称
     */
    public String name;

    /**
     * 身份证号码
     */
    public String idCard;

    /**
     * 银行账号
     */
    public String bankAccountNumber;

    /**
     * 银行名称
     */
    public String bankName;

    /**
     * 手机号
     */
    public String mobile;

    public TenantInfoVO(TenantEntity tenant) {
        this.name = tenant.name;
        this.idCard = tenant.idCard;
        this.bankAccountNumber = tenant.receiverBankAccount;
        this.bankName = tenant.receiverBankName;
        this.mobile = tenant.mobile;
    }
}
