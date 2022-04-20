package junjie.fun.mywiki.response;

import lombok.Data;

@Data
public class LoginData {
    private Long id;
    private String loginName;
    private String name;
    private String token;
}
