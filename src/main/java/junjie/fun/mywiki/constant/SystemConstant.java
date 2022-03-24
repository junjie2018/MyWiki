package junjie.fun.mywiki.constant;

import org.omg.CORBA.PUBLIC_MEMBER;

public class SystemConstant {
    public static final String REQUEST_START_TIME = "requestStartTime";
    public static final String OPTIONS = "options";
    public static final String TOKEN_KEY = "TOKEN:%s";

    public static String getTokenKey(String token) {
        return String.format(TOKEN_KEY, token);
    }
}
