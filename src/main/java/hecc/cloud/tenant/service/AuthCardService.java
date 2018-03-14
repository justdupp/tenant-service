package hecc.cloud.tenant.service;

import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Auther xuhoujun
 * @Description: auth service
 * @Date: Created In 下午9:34 on 2018/3/13.
 */
@Component
public class AuthCardService {

    @Autowired
    OkHttpClient httpClient;
    @Value("${tenant-service.auth-card.url}")
    private String authURL;
    @Value("${tenant-service.auth-card.code}")
    private String authCode;

    public AuthResponseVO auth(String username, String account, String idCard, String mobile)
            throws IOException {
        Request request = new Request.Builder().get().url(
                authURL + "?name=" + URLEncoder.encode(username, "utf-8") + "&acct=" + account
                        + "&id=" + idCard + "&phone=" + mobile)
                .addHeader("Authorization", "CODE " + authCode).build();
        Response response = httpClient.newCall(request).execute();
        if (response.code() == 200) {
            return new AuthResponseVO(JSON.parseObject(response.body().bytes(), AuthCodeResponseVO.class));
        } else {
            throw new IOException();
        }
    }

    public static class AuthResponseVO {

        public boolean isSuccess;
        public String msg;

        public AuthResponseVO(AuthCodeResponseVO response) {
            this.isSuccess = false;
            this.msg = "调用失败";
            if (response != null && response.resp != null) {
                switch (response.resp.code) {
                    case 0:
                        this.isSuccess = true;
                        this.msg = "调用成功";
                        break;
                    case 1:
                        this.isSuccess = false;
                        this.msg = "交易失败，请稍后重试";
                        break;
                }
            }
        }
    }

    public static class AuthCodeResponseVO {

        public Resp resp;

        public static class Resp {

            public int code;
        }
    }

}
