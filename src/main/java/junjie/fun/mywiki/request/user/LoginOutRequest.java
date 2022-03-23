package junjie.fun.mywiki.request.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class LoginOutRequest {
    @NotEmpty(message = "token未传递")
    private String token;
}
