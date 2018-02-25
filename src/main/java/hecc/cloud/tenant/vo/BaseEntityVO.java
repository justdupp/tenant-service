package hecc.cloud.tenant.vo;

import hecc.cloud.tenant.entity.BaseEntity;

import java.util.Date;

/**
 * @Auther xuhoujun
 * @Description:  返回对象基本VO
 * @Date: Created In 下午8:21 on 2018/2/25.
 */
public abstract class BaseEntityVO {

    /**
     * 主键
     */
    public Long id;

    /**
     * 创建时间
     */
    public Date createDate;

    /**
     * 更新时间
     */
    public Date modifyDate;

    /**
     * 乐观锁
     */
    public Integer version;

    /**
     * 逻辑删除
     */
    public Boolean del;
    /**
     * 平台标识
     */
    public String platform;

    public BaseEntityVO(BaseEntity entity) {
        this.id = entity.id;
        this.createDate = entity.createDate;
        this.modifyDate = entity.modifyDate;
        this.version = entity.version;
        this.del = entity.del;
        this.platform = entity.platform;
    }
}
