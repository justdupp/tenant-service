package hecc.cloud.tenant.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Auther xuhoujun
 * @Description: 封装实体基础属性
 * @Date: Created In 下午1:30 on 2018/2/24.
 */
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public abstract class BaseEntity {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    public Long id;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date createDate = new Date();

    /**
     * 修改时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date modifyDate = new Date();

    /**
     * 版本号--乐观锁
     */
    @Version
    public Integer version = 0;

    /**
     * 是否逻辑删除
     */
    public Boolean del = false;

    /**
     *  平台标识
     */
    @Column(nullable = false)
    public String platform;
}
