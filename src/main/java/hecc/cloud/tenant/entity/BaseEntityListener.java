package hecc.cloud.tenant.entity;

import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * @Auther xuhoujun
 * @Description: 实体基础类监听
 * @Date: Created In 下午1:33 on 2018/2/24.
 */
public class BaseEntityListener {

    /**
     * 用于更新修改时间
     * @param baseEntity 基础实体参数
     */
    @PreUpdate
    public void updateModifyDate(BaseEntity baseEntity){
        baseEntity.modifyDate = new Date();
    }
}
