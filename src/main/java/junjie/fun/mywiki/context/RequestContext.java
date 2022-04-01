package junjie.fun.mywiki.context;

public class RequestContext {
    private static ThreadLocal<String> remoteAddress = new ThreadLocal<>();

    public static String getRemoteAddress() {
        return remoteAddress.get();
    }

    public static void setRemoteAddress(String remoteAddress) {
        RequestContext.remoteAddress.set(remoteAddress);
    }
}
