package com.csdig.cms.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.MethodUtils;

public class ReflectionUtils {

	public static Object invokeMethod(Object object, String methodName, Object arg) throws Exception {
		Object[] args = { arg };
		return invokeMethod(object, methodName, args);
	}

	public static Object invokeMethod(Object object, String methodName, Object[] args) throws Exception {
		int arguments = args.length;
		Class<?>[] parameterTypes = new Class[arguments];
		for (int i = 0; i < arguments; i++) {
			parameterTypes[i] = args[i].getClass();
		}
		return invokeMethod(object, methodName, args, parameterTypes);
	}

	public static Object invokeMethod(Object object, String methodName, Object[] args, Class<?>[] parameterTypes)
			throws Exception {
		try {
			return MethodUtils.invokeMethod(object, methodName, args, parameterTypes);
		} catch (InvocationTargetException ivk) {
			Throwable targetEcp = ivk.getTargetException();
			if (targetEcp instanceof Exception) {
				throw (Exception) targetEcp;
			}
			ivk.printStackTrace();
		}
		return null;
	}
}
