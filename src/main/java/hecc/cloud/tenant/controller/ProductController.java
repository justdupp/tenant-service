package hecc.cloud.tenant.controller;

import hecc.cloud.tenant.client.CreditClient;
import hecc.cloud.tenant.enumer.CreditBankTypeEnum;
import hecc.cloud.tenant.vo.credit.CardVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuhoujun
 * @description: 产品控制器
 * @date: Created In 下午10:26 on 2018/3/14.
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private CreditClient creditClient;

    @ApiOperation(value = "获取产品列表")
    @GetMapping("/list")
    public ResponseVO allProducts() {
        return succeed(creditClient.getAllCard());
    }

    @ApiOperation(value = "新增产品")
    @PostMapping("/add")
    public ResponseVO addProduct(String name, String shortName, String bankLogo,
                                 String bankDetail, String money, CreditBankTypeEnum type, Boolean show) {
        creditClient.saveAndUpdateCard(
                new CardVO(null, name, type, shortName, bankLogo, bankDetail, money, show));
        return succeed(null);
    }

    @ApiOperation(value = "更新产品")
    @PostMapping("/modify")
    public ResponseVO modProduct(Long id, String name, String shortName, String bankLogo,
                                 String bankDetail, String money, CreditBankTypeEnum type, Boolean show) {
        creditClient.saveAndUpdateCard(
                new CardVO(id, name, type, shortName, bankLogo, bankDetail, money, show));
        return succeed(null);
    }

    @ApiOperation(value = "切换产品")
    @PostMapping("/toggleShow")
    public ResponseVO toggleProductShow(Long id, boolean show) {
        creditClient.saveAndUpdateCard(new CardVO(id, show));
        return succeed(null);
    }
}
