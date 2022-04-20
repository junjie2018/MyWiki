package junjie.fun.mywiki.exception.handler;

import junjie.fun.mywiki.exception.BusinessException;
import junjie.fun.mywiki.common.response.ResponseVo;
import junjie.fun.mywiki.system.SystemConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static junjie.fun.mywiki.constant.code.SystemCode.PARAM_CANT_PARSE;
import static junjie.fun.mywiki.constant.code.SystemCode.PARAM_VALIDATION_WRONG;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseVo<Void> handleBusinessException(BusinessException e, HttpServletRequest request, HttpServletResponse response) {

        // 非生产环境
        if (SystemConfig.isNotProd()) {
            e.printStackTrace();
        }

        return ResponseVo.error(e.getCode());
    }


    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ResponseVo<Void> validExceptionHandler(BindException e) {


        // 非生产环境
        if (SystemConfig.isNotProd()) {

            e.printStackTrace();

            // fieldError不为空，则打印更具体的消息
            if (e.getFieldError() != null) {
                return ResponseVo.error(
                        PARAM_VALIDATION_WRONG,
                        String.format("%s：%s", e.getFieldError().getField(), e.getFieldError().getDefaultMessage()));
            }
        }

        return ResponseVo.error(PARAM_VALIDATION_WRONG);
    }

    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseVo<Void> validExceptionHandler(HttpMessageNotReadableException e) {
        // 非生产环境
        if (SystemConfig.isNotProd()) {

            e.printStackTrace();

            return ResponseVo.error(
                    PARAM_CANT_PARSE,
                    "请求体未传递，或请求体不符合JSON格式，请检查");
        }

        return ResponseVo.error(PARAM_CANT_PARSE);
    }
}
