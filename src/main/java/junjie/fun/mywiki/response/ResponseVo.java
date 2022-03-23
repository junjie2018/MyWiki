package junjie.fun.mywiki.response;

import junjie.fun.mywiki.constant.code.Code;
import lombok.Data;

import static junjie.fun.mywiki.constant.code.SystemCode.SUCCESS;

@Data
public class ResponseVo<T> {

    private final String code;
    private final String msg;
    private T data;

    public boolean isSuccess() {
        return this.code.equals(SUCCESS.getCode());
    }

    private ResponseVo(Code code, T data) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
    }

    private ResponseVo(Code code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }


    public static <T> ResponseVo<T> success(T data) {
        return new ResponseVo<>(SUCCESS, data);
    }

    public static <T> ResponseVo<T> success() {
        return new ResponseVo<>(SUCCESS);
    }

    public static <T> ResponseVo<T> error(Code code, T data) {
        return new ResponseVo<>(code, data);
    }

    @SuppressWarnings("rawtypes")
    public static ResponseVo error(Code code) {
        return new ResponseVo<>(code, null);
    }
}
