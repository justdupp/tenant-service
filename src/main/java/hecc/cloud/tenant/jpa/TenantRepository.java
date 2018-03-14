package hecc.cloud.tenant.jpa;

import hecc.cloud.tenant.entity.TenantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Auther xuhoujun
 * @Description: 租户jpa
 * @Date: Created In 下午2:00 on 2018/2/24.
 */
public interface TenantRepository extends JpaRepository<TenantEntity, Long> {

    @Query("select t from TenantEntity t where t.parent.id=?1 and t.del = false")
    List<TenantEntity> findByParentIdAndDelIsFalse(Long parentId);

    @Query("select t from TenantEntity t where t.platform = ?1 and t.topTenant = true and t.del = false")
    TenantEntity findOneByPlatformAndTopTenantIsTrueAndDelIsFalse(String platform);

    @Query("select t from TenantEntity t where t.platform = ?1 and t.parent = null and t.del = false")
    List<TenantEntity> findByPlatformAndParentIsNullAndDelIsFalse(String platform);

    @Query("select t from TenantEntity t where t.topTenant = true and t.del = false")
    List<TenantEntity> findByTopTenantIsTrueAndDelIsFalse();

    List<TenantEntity> findByNameAndPlatformAndDelIsFalse(String name, String platform);

    List<TenantEntity> findByNameAndDelIsFalse(String name);

    List<TenantEntity> findByPlatformAndDelIsFalse(String platform);


}
