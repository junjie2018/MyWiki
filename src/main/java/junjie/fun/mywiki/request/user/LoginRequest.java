package junjie.fun.mywiki.request.user;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class LoginRequest {
    @NotEmpty
    private String loginName;

    @NotEmpty
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$")
    private String password;

    @Valid
    @NotNull
    private Test test;

    @Data
    public static class Test {
        @NotBlank
        private String test;
    }
}
