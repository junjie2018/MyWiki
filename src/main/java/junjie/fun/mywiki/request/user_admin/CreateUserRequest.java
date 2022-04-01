package junjie.fun.mywiki.request.user_admin;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CreateUserRequest {
    @NotBlank
    private String loginName;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$")
    private String password;
}
