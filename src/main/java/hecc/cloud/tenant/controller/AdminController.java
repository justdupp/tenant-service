package hecc.cloud.tenant.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther xuhoujun
 * @Description: admin控制器
 * @Date: Created In 下午8:51 on 2018/2/27.
 */
@RestController
public class AdminController extends BaseController {

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return "hello "+name+"，this is tenant message";
    }
}
