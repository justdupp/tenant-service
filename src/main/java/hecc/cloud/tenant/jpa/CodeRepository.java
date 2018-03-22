package hecc.cloud.tenant.jpa;

import hecc.cloud.tenant.entity.CodeEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Auther xuhoujun
 * @Description: code jpa
 * @Date: Created In 下午1:59 on 2018/2/24.
 */
@CacheConfig(cacheNames = "tenant")
public interface CodeRepository extends JpaRepository<CodeEntity, Long> {

    @Cacheable
  //  @Query("select c from CodeEntity c where c.code=?1 and c.del= false")
    CodeEntity findOneByCodeAndDelIsFalse(String code);

}
