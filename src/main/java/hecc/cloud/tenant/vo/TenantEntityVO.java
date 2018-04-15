package hecc.cloud.tenant.vo;

import hecc.cloud.tenant.entity.TenantEntity;

/**
 * @author xuhoujun
 * @description: 返回租户对象VO
 * @date: Created In 下午8:25 on 2018/2/25.
 */
public class TenantEntityVO extends BaseEntityVO {

    /**
     * 关联上级用户
     */
    public Long parentId;
    /**
     * 租户姓名
     */
    public String name;
    /**
     * 用户手机号
     */
    public String mobile;
    /**
     * 商户编码
     */
    public String merchantCode;
    /**
     * 收款卡号
     */
    public String receiverBankAccount;
    /**
     * 收款卡银行名称
     */
    public String receiverBankName;
    /**
     * 身份证号
     */
    public String idCard;
    /**
     * 银行卡是否已鉴权通过
     */
    public boolean bankCardHasPassed;
    /**
     * 登录姓名
     */
    public String userName;
    /**
     * 微信openid
     */
    public String openid;
    /**
     * 登录密码
     */
    public String password;

    public TenantEntityVO(TenantEntity entity) {
        super(entity);
        this.parentId = entity.parent == null ? null : entity.parent.id;
        this.name = entity.name;
        this.mobile = entity.mobile;
        this.merchantCode = entity.merchantCode;
        this.receiverBankAccount = entity.receiverBankAccount;
        this.receiverBankName = entity.receiverBankName;
        this.idCard = entity.idCard;
        this.bankCardHasPassed = entity.bankCardHasPWD;
        this.userName = entity.userName;
        this.openid = entity.openid;
        this.password = entity.password;
    }
}
