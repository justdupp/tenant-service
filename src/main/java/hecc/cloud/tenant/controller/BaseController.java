package hecc.cloud.tenant.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Auther xuhoujun
 * @Description: 封装接口公共返回
 * @Date: Created In 下午2:44 on 2018/2/24.
 */
public abstract class BaseController {

    protected static final int ERROR_VALID_FAILED = 1000;
    protected static final int ERROR_CODE_AUTH_FAILED = 2001;
    protected static final int ERROR_BIND_CODE_FAILED = 2002;
    protected static final int ERROR_BIND_CODE_NOT_FOUND = 2004;
    protected static final int ERROR_BIND_CODE_WRONG_PLATFORM = 2005;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseVO validErrorHandler(MethodArgumentNotValidException e) {
        return failed(e.getMessage(), ERROR_VALID_FAILED);
    }

    protected ResponseVO successed(Object obj) {
        ResponseVO result = new ResponseVO();
        result.msg = "OK";
        result.code = 0;
        result.object = obj;
        return result;
    }

    protected ResponseVO failed(String msg, Integer code) {
        ResponseVO result = new ResponseVO();
        result.msg = msg;
        result.code = code;
        result.object = null;
        return result;
    }

    public static class ResponseVO {
        public String msg;
        public Integer code;
        public Object object;
    }
}
