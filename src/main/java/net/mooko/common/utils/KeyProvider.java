package net.mooko.common.utils;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class KeyProvider {
    public static final KeyProvider INSTANCE = new KeyProvider();
    private KeyProvider() {}
    
    public String getTokenKey() {
        return "roBu$SofT　LogiStics";
    }
    
    public String getVerifyCodeKey() {
        return "RoBu$SofT　LogiStics";
    }
    
    public String getRandomPassKey() {
        return "robu$SofT　LogiStics";
    }
}
