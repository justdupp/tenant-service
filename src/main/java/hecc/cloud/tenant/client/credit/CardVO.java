package hecc.cloud.tenant.client.credit;

import hecc.cloud.tenant.enumer.CreditBankTypeEnum;

/**
 * @Auther xuhoujun
 * @Description:
 * @Date: Created In 下午10:23 on 2018/3/14.
 */
public class CardVO {
    public Long id;
    /**
     * 卡名称
     */
    public String name;
    /**
     * 卡类型
     */
    public CreditBankTypeEnum type;
    /**
     * 卡简称
     */
    public String short_name;
    /**
     * 卡logo
     */
    public String bank_logo;
    /**
     * 卡全程
     */
    public String bank_detail;
    /**
     * 金额
     */
    public String money;

    public CardVO(Long id, String name, CreditBankTypeEnum type, String short_name,
                  String bank_logo, String bank_detail, String money) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.short_name = short_name;
        this.bank_logo = bank_logo;
        this.bank_detail = bank_detail;
        this.money = money;
    }

}
