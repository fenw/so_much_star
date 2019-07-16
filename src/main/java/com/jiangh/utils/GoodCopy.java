package com.jiangh.utils;

import java.lang.reflect.Array;

public class GoodCopy {

	public static Object goodCopy(Object object,int newLength) {
		Class<? extends Object> cl = object.getClass();
		if(!cl.isArray()) throw new IllegalArgumentException();
		Object newInstance = Array.newInstance(cl.getComponentType(), newLength);
		System.arraycopy(object, 0, newInstance, 0, Math.min(Array.getLength(object), newLength));
		return newInstance;
	}
}
