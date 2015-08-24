package net.mooko.common.json;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class ResponseHelper {

    public static <T> Response<T> createSuccessResponse() {
        return createResponse(ReturnCode.SUCCESS, null);
    }

    public static <T> Response<T> createSuccessResponse(T payload) {
        Response<T> response = createResponse(ReturnCode.SUCCESS, null);
        response.setPayload(payload);
        return response;
    }
    
    
    public static <T> Response<T> createResponse() {
        return createResponse(ReturnCode.NEVER_USED_CODE, null);
    }

    public static <T> Response<T> createResponse(int code, String description) {
        Response<T> response = new Response<T>();
        if (code != ReturnCode.NEVER_USED_CODE) response.setCode(code);
        if (description != null) response.setDescription(description);
        return response;
    }
    
    public static <T> Response<T> createResponse(int code, long lastUpdateTime) {
        Response<T> response = new Response<T>();
        if (code != ReturnCode.NEVER_USED_CODE) response.setCode(code);
        response.setLastUpdateTime(lastUpdateTime);
        return response;
    }
    
    public static <T> Response<T> createBusinessErrorResponse(String description) {
        return createResponse(ReturnCode.BUSINESS_ERROR, description);
    }
    
    public static <T> Response<T> createExceptionResponse(Exception e) {
        return createResponse(ReturnCode.EXCEPTION, e.getMessage());
    }

}
