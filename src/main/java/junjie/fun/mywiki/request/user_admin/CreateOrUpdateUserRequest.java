package junjie.fun.mywiki.request.user_admin;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CreateOrUpdateUserRequest {
    private Long id;

    @NotNull
    private String loginName;

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$")
    private String password;
}
