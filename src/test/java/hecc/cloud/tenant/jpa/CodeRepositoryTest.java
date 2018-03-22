package hecc.cloud.tenant.jpa;

import hecc.cloud.tenant.entity.CodeEntity;
import hecc.cloud.tenant.entity.TenantEntity;
import hecc.cloud.tenant.enumer.CodeTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther xuhoujun
 * @Description: code 单元测试
 * @Date: Created In 下午2:02 on 2018/2/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeRepositoryTest {
    @Autowired
    private CodeRepository codeRepository;

    @Test
    public void testCodeJPA(){
        CodeEntity code = new CodeEntity();
        TenantEntity tenant = new TenantEntity();
        tenant.id = 1L;
        code.code = "code11031";
        code.type = CodeTypeEnum.QUICK_PASS;
        code.platform = "quickpass";
        code.tenant = tenant;
        codeRepository.saveAndFlush(code);
    }

    @Test
    public void testCodeRepo(){
        System.out.println("第一次查询");
        CodeEntity codeEntity = codeRepository.findOneByCodeAndDelIsFalse("code11032");
        System.out.println(codeEntity.type);

        System.out.println("第二次查询");
        CodeEntity codeEntity2 = codeRepository.findOneByCodeAndDelIsFalse("code11032");
        System.out.println(codeEntity2.type);
    }
}