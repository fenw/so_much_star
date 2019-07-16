package com.jiangh.utils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ReflectToString {
	
	private List<String> visited = new ArrayList<>();
	
	public String toString(Object object) {
		if(object ==null) return null;
		Class<? extends Object> cl = object.getClass();
		if(cl==String.class) return (String)object;
		if(visited.contains(cl.getName())) return "...";
		visited.add(cl.getName());
		if(cl.isArray()) {
			String r =cl.getComponentType()+"[]{";
			Class<?> componentType = cl.getComponentType();//获取数组类型
			for(int i =0;i<Array.getLength(object);i++) {
				if(i>0)r +=",";
				Object val = Array.get(object, i);
				if(componentType.isPrimitive()) {//判断是否是基本类型
					r+=val;
				}else {
					r+=toString(val);
				}
			}
			return r+="}";
		}
		
		String r = cl.getName();
		do {
			Field[] declaredFields = cl.getDeclaredFields();
			if(declaredFields.length>0) {
			r+="[";
			AccessibleObject.setAccessible(declaredFields, true);
			for (Field field : declaredFields) {
				if(!Modifier.isStatic(field.getModifiers())) {//判断是否是static
					if(!r.endsWith("[")) {
						r+=",";
					}
					r+=field.getName()+"=";
					Object val;
					try {
						val = field.get(object);
					if(field.getType().isPrimitive()) {//获取类型判断是否是基础类型
						r+=val;
					}else {
						r+=toString(val);
					}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			r+="]";
			}
			cl = cl.getSuperclass();
		}while(cl!=null);
		return r;
	}

}
