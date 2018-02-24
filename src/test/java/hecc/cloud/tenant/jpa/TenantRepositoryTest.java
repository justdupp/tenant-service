package hecc.cloud.tenant.jpa;

import hecc.cloud.tenant.entity.TenantEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther xuhoujun
 * @Description:
 * @Date: Created In 下午2:28 on 2018/2/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TenantRepositoryTest {

    @Autowired
    private TenantRepository tenantRepository;
    @Test
    public void testTenantJpa(){
        TenantEntity tenant = new TenantEntity();
        tenant.name = "justdupp";
        tenant.password = "root";
        tenantRepository.save(tenant);
    }

}