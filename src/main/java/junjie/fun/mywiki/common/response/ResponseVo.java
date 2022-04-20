package junjie.fun.mywiki.common.response;

import junjie.fun.mywiki.constant.code.Code;
import lombok.*;

import static junjie.fun.mywiki.constant.code.SystemCode.SUCCESS;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseVo<T> {

    private String code;
    private String msg;
    private T data;

    public boolean isSuccess() {
        return this.code.equals(SUCCESS.getCode());
    }

    public static <T> ResponseVo<T> success() {
        return new ResponseVo<>(SUCCESS.getCode(), SUCCESS.getMsg(), null);
    }

    public static <T> ResponseVo<T> success(T data) {
        return new ResponseVo<>(SUCCESS.getCode(), SUCCESS.getMsg(), data);
    }

    public static <T> ResponseVo<T> error(Code code) {
        return new ResponseVo<>(code.getCode(), code.getMsg(), null);
    }

    public static <T> ResponseVo<T> error(Code code, T data) {
        return new ResponseVo<>(code.getCode(), code.getMsg(), data);
    }

    public static <T> ResponseVo<T> error(Code code, String msg) {
        return new ResponseVo<>(code.getCode(), msg, null);
    }

    public static <T> ResponseVo<T> error(Code code, String msg, T data) {
        return new ResponseVo<>(code.getCode(), msg, data);
    }
}
