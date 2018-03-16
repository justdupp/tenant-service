package hecc.cloud.tenant.vo.credit;

import hecc.cloud.tenant.enumer.CreditBankTypeEnum;

/**
 * @Auther xuhoujun
 * @Description: 银行卡VO
 * @Date: Created In 下午10:57 on 2018/3/14.
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
    public String shortName;
    /**
     * 卡logo
     */
    public String bankLogo;
    /**
     * 卡全程
     */
    public String bankDetail;
    /**
     * 金额
     */
    public String money;
    /**
     * 是否显示
     */
    public Boolean show;

    public CardVO(Long id, Boolean show) {
        this.id = id;
        this.show = show;
    }

    public CardVO(Long id, String name, CreditBankTypeEnum type, String shortName,
                  String bankLogo, String bankDetail, String money, Boolean show) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.shortName = shortName;
        this.bankLogo = bankLogo;
        this.bankDetail = bankDetail;
        this.money = money;
        this.show = show;
    }
}
