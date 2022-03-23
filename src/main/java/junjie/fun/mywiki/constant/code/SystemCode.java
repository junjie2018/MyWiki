package junjie.fun.mywiki.constant.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemCode implements Code {
    SUCCESS("0", "Success"),
    ;

    private final String code;
    private final String msg;
}
