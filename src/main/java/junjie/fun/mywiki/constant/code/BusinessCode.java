package junjie.fun.mywiki.constant.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessCode implements Code {
    LOGIN_NAME_NOT_EXIST("100000", "用户名不存在"),
    PASSWORD_WRONG("100001", "密码错误"),
    LOGIN_NAME_EXIST("100003", "密码错误"),

    HAS_ALREADY_VOTE("100100", "该文档已点赞"),
    DOC_NOT_EXIST("100101", "该文档不存在"),
    ;

    @Getter
    private final String code;
    @Getter
    private final String msg;
}
