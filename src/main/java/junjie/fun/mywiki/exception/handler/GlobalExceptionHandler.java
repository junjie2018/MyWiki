package junjie.fun.mywiki.exception.handler;

import junjie.fun.mywiki.exception.BusinessException;
import junjie.fun.mywiki.response.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    @SuppressWarnings("rawtypes")
    public ResponseVo handleBusinessException(BusinessException e, HttpServletRequest request, HttpServletResponse response) {
        if (response.getContentType() != null && response.getContentType().contains("application/vnd.ms-excel")) {
            response.setContentType("application/json");
            response.setHeader("Content-Disposition", null);
        }
        return ResponseVo.error(e.getCode());
    }
}
