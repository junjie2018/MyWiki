package junjie.fun.mywiki.context;

import junjie.fun.mywiki.dto.TokenDataDTO;

public class UserContext {
    private static ThreadLocal<TokenDataDTO> tokenDataDTO = new ThreadLocal<>();

    public static TokenDataDTO getTokenDTO() {
        return tokenDataDTO.get();
    }

    public static String getToken() {
        return tokenDataDTO.get().getToken();
    }

    public static Long getUserId() {
        return tokenDataDTO.get().getId();
    }

    public static String getLoginName() {
        return tokenDataDTO.get().getLoginName();
    }

    public static void setTokenDataDTO(TokenDataDTO tokenDataDTO) {
        UserContext.tokenDataDTO.set(tokenDataDTO);
    }
}
