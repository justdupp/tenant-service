package hecc.cloud.tenant.jpa;

import hecc.cloud.tenant.entity.TenantEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author xuhoujun
 * @description:
 * @date: Created In 下午2:28 on 2018/2/24.
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

    @Test
    public void testFindByParentIdAndDelIsFalse() {
        List<TenantEntity> userEntityList = tenantRepository.findByParentIdAndDelIsFalse(1L);
        System.out.println(userEntityList.size());
        userEntityList.stream().forEach(tenant -> {
            System.out.println(tenant.bankCardHasPWD);
            System.out.println(tenant.receiverBankAccount);
            System.out.println(tenant.platform);
            System.out.println(tenant.receiverBankName);
            System.out.println(tenant.mobile);
            System.out.println(tenant.openid);
        });
    }

    @Test
    public void testFindOneByPlatformAndTopTenantIsTrueAndDelIsFalse(){
        TenantEntity entity = tenantRepository.findOneByPlatformAndTopTenantIsTrueAndDelIsFalse("credit");
        System.out.println("first select");
        System.out.println(entity.name);
        System.out.println(entity.openid);

        System.out.println("second select");

        TenantEntity entity2 = tenantRepository.findOneByPlatformAndTopTenantIsTrueAndDelIsFalse("credit");
        System.out.println(entity2.name);
        System.out.println(entity2.openid);

    }

    @Test
    public void testFindByPlatformAndParentIsNullAndDelIsFalse() {
        List<TenantEntity> entityList = tenantRepository.findByPlatformAndParentIsNullAndDelIsFalse("credit");
        System.out.println(entityList.size());
        entityList.stream().forEach(tenant -> {
            System.out.println(tenant.bankCardHasPWD);
            System.out.println(tenant.receiverBankAccount);
            System.out.println(tenant.platform);
            System.out.println(tenant.receiverBankName);
            System.out.println(tenant.mobile);
            System.out.println(tenant.openid);
        });
    }
}