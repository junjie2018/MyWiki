package junjie.fun.mywiki.response.data;

import lombok.Data;

@Data
public class LoginData {
    private Long id;
    private String loginName;
    private String name;
    private String token;
}
