package junjie.fun.mywiki.exception;

import junjie.fun.mywiki.constant.code.Code;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final Code code;

    public BusinessException(Code code) {
        super(code.getMsg());
        this.code = code;
    }
}
