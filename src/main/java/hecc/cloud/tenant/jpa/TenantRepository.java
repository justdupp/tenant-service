package hecc.cloud.tenant.jpa;

import hecc.cloud.tenant.entity.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther xuhoujun
 * @Description: 租户jpa
 * @Date: Created In 下午2:00 on 2018/2/24.
 */
public interface TenantRepository extends JpaRepository<TenantEntity,Long> {

}
