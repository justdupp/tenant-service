package hecc.cloud.tenant.controller;

import hecc.cloud.tenant.client.CreditClient;
import hecc.cloud.tenant.client.credit.CardVO;
import hecc.cloud.tenant.enumer.CreditBankTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther xuhoujun
 * @Description:
 * @Date: Created In 下午10:26 on 2018/3/14.
 */
@RestController
@RequestMapping("product")
public class ProductController extends BaseController {
    @Autowired
    private CreditClient creditClient;

    @GetMapping("/list")
    public ResponseVO allProducts() {
        return successed(creditClient.getAllCard());
    }

    @PostMapping("/add")
    public ResponseVO addProduct(String name, String short_name, String bank_logo,
                                 String bank_detail, String money, CreditBankTypeEnum type) {
        creditClient.saveAndUpdateCard(
                new CardVO(null, name, type, short_name, bank_logo, bank_detail, money));
        return successed(null);
    }
}
