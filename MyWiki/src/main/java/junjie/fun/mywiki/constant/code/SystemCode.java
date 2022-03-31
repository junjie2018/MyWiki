package junjie.fun.mywiki.constant.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemCode implements Code {
    SUCCESS("0", "Success"),
    PARAM_VALIDATION_WRONG("1", "参数校验错误"),
    PARAM_CANT_PARSE("1", "参数无法正常解析"),
    FAILED("100401", "HTTP错误码：鉴权不通过"),
    ;

    private final String code;
    private final String msg;
}
