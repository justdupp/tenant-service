package hecc.cloud.tenant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Auther xuhoujun
 * @Description: 租户实体对象
 * @Date: Created In 下午1:46 on 2018/2/24.
 */
@Entity
@Table(name = "tenant")
public class TenantEntity extends BaseEntity {

    /**
     * 关联上级租户
     */
    @ManyToOne
    public TenantEntity parent;
    /**
     * 租户姓名
     */
    public String name;

    /**
     * 租户手机号
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
     * 银行卡是否鉴权通过
     */
    @Column(name = "bank_card_has_pwd")
    public Boolean bankCardHasPWD;

    /**
     * 登录姓名
     */
    public String userName;

    /**
     * 登录密码
     */
    public String password;

    /**
     * 微信openid
     */
    public String openid;

    /**
     * 是否顶级租户
     */
    public Boolean topTenant;

    /**
     * 身份证正面照
     */
    public String idCardFontPic;
    /**
     * 身份证反面照
     */
    public String idCardBackPic;

}
