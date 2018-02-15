package com.gmb.madridshops.util;

import java.lang.reflect.Method;


class ReflectionUtil {
    public static boolean hasMethod(Class theClass, String methodName) {
        Method method = getMethod(theClass, methodName);
        return method != null;

    }

    public static Method getMethod(Class theClass, String methodName) {
        Method methodToFind = null;
        try {
            methodToFind = theClass.getMethod(methodName, (Class<?>[]) null);
        } catch (NoSuchMethodException | SecurityException e) {
            // nothing to do here
        }

        return methodToFind;
    }
}
