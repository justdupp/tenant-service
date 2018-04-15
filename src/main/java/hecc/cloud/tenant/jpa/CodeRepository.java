package hecc.cloud.tenant.jpa;

import hecc.cloud.tenant.entity.CodeEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author xuhoujun
 * @description: code jpa
 * @date: Created In 下午1:59 on 2018/2/24.
 */
@CacheConfig(cacheNames = "tenant")
public interface CodeRepository extends JpaRepository<CodeEntity, Long> {

    /**
     * 根据码查询码对象
     *
     * @param code 码
     * @return 码对象
     */
    @Cacheable
    CodeEntity findOneByCodeAndDelIsFalse(String code);

}
