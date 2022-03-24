package junjie.fun.mywiki.dto;

import lombok.Data;

@Data
public class TokenDataDTO {
    private Long id;
    private String loginName;
    private String name;
    private String token;
}
