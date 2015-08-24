package net.mooko.common.utils;

import java.lang.reflect.Method;

/**
 * @author puras <he@puras.me>
 * @since 15/6/14  下午7:21
 */
public class ReflectionUtils {
    /**
     * 获得method对象的一个简单字符串描述，不能保证唯一性
     * @param method
     * @return
     */
    public static String toSimpleSignature(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append(method.getDeclaringClass().getSimpleName()).append('.').append(method.getName()).append('(');
        Class<?>[] params = method.getParameterTypes();
        for (int i = 0; i <  params.length; i++) {
            sb.append(params[i].getSimpleName());
            if (i < params.length -1) {
                sb.append(",");
            }
        }
        sb.append(')');

        return sb.toString();
    }
}
