package hecc.cloud.tenant.entity;

import hecc.cloud.tenant.enumer.CodeTypeEnum;

import javax.persistence.*;

/**
 * @Auther xuhoujun
 * @Description: 码实体对象
 * @Date: Created In 下午1:56 on 2018/2/24.
 */
@Entity
@Table(name = "code")
public class CodeEntity extends BaseEntity {
    /**
     * 关联租户
     */
    @ManyToOne
    public TenantEntity tenant;

    /**
     * 码
     */
    public String code;

    /**
     * 码类型
     */
    @Enumerated(EnumType.STRING)
    public CodeTypeEnum type;
}
