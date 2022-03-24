package junjie.fun.mywiki.exception.handler;

import com.alibaba.fastjson.JSON;
import junjie.fun.mywiki.exception.BusinessException;
import junjie.fun.mywiki.response.ResponseVo;
import junjie.fun.mywiki.system.SystemConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static junjie.fun.mywiki.constant.code.SystemCode.PARAM_VALIDATION_WRONG;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    @SuppressWarnings("rawtypes")
    public ResponseVo handleBusinessException(BusinessException e, HttpServletRequest request, HttpServletResponse response) {

        if (SystemConfig.isNotProd()) {
            e.printStackTrace();
        }

        return ResponseVo.error(e.getCode());
    }


    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    @SuppressWarnings("rawtypes")
    public ResponseVo validExceptionHandler(BindException e) {

        FieldError fieldError = e.getFieldError();

        // 非生产环境，且fieldError不为空，则覆盖code中的错误消息
        if (SystemConfig.isNotProd() && e.getFieldError() != null) {

            e.printStackTrace();

            return ResponseVo.error(
                    PARAM_VALIDATION_WRONG,
                    String.format("参数校验错误：%s", e.getFieldError().getField()));
        }

        // 生产环境，或fieldError为空，则使用code中的错误消息
        else {
            return ResponseVo.error(PARAM_VALIDATION_WRONG);
        }
    }
}
