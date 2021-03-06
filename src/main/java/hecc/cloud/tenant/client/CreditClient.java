package hecc.cloud.tenant.client;

import hecc.cloud.tenant.vo.credit.CardVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author xuhoujun
 * @description: creditClient
 * @date: Created In 下午10:19 on 2018/3/14.
 */
@FeignClient("credit-service")
public interface CreditClient {

    /**
     * 获取卡列表
     *
     * @return List<CardVO>
     */
    @GetMapping("/domestic/allCards")
    List<CardVO> getAllCard();

    /**
     * 新增卡
     *
     * @param cardVO 卡VO
     */
    @PostMapping("/domestic/createCard")
    void saveAndUpdateCard(CardVO cardVO);
}
