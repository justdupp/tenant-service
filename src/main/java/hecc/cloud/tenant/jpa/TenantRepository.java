package hecc.cloud.tenant.jpa;

import hecc.cloud.tenant.entity.TenantEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author xuhoujun
 * @description: 租户jpa
 * @date: Created In 下午2:00 on 2018/2/24.
 */
@CacheConfig(cacheNames = "tenant")
public interface TenantRepository extends JpaRepository<TenantEntity, Long> {


    /**
     * 根据父租户id查询
     *
     * @param parentId 父租户id
     * @return 租户列表
     */
    @Query("select t from TenantEntity t where t.parent.id=?1 and t.del = false")
    List<TenantEntity> findByParentIdAndDelIsFalse(Long parentId);

    /**
     * 根据平台查询顶级租户
     *
     * @param platform 平台名称
     * @return 顶级租户
     */
    @Cacheable
    TenantEntity findOneByPlatformAndTopTenantIsTrueAndDelIsFalse(String platform);

    /**
     * 根据平台名称查询非顶级租户列表
     *
     * @param platform 平台名称
     * @return 租户列表
     */
    @Query("select t from TenantEntity t where t.platform = ?1 and t.parent = null and t.del = false")
    List<TenantEntity> findByPlatformAndParentIsNullAndDelIsFalse(String platform);

    /**
     * 查询顶级租户列表
     *
     * @return 顶级租户列表
     */
    @Query("select t from TenantEntity t where t.topTenant = true and t.del = false")
    List<TenantEntity> findByTopTenantIsTrueAndDelIsFalse();

    /**
     * 根据租户名称和平台名称查询租户列表
     *
     * @param name     租户名称
     * @param platform 平台名称
     * @return 租户列表
     */
    List<TenantEntity> findByNameAndPlatformAndDelIsFalse(String name, String platform);

    /**
     * 根据租户名称查询租户列表
     *
     * @param name 租户名称
     * @return 租户列表
     */
    List<TenantEntity> findByNameAndDelIsFalse(String name);

    /**
     * 根据平台名称查询租户列表
     *
     * @param platform 平台名称
     * @return 租户列表
     */
    List<TenantEntity> findByPlatformAndDelIsFalse(String platform);


}
