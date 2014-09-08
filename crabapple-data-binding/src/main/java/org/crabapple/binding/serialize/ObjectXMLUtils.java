package org.crabapple.binding.serialize;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.log4j.Logger;


public class ObjectXMLUtils {
	private static final Logger logger = Logger.getLogger(ObjectXMLUtils.class);
	
	public static String convertToXML(Object object) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException{
		String xml = "";
		Method[] methods = object.getClass().getDeclaredMethods();
		for(Method method:methods){
			if(method.getName().startsWith("get") || method.getName().startsWith("is")){
				System.out.println("Method Name:" + method.getName());
				Object obj = method.invoke(object, null);
				System.out.println("|invoke = " + obj);
				// 
				if(null == obj){
					// DO nothing
				}else if(obj.getClass().isPrimitive() || obj instanceof String){
					// If the class is primitive type or string type, save to string as format '<tag>value</tag>'
					String tagName = method.getName().replace("get", "");
					String value = obj.toString();
					if(null != value && !("").equals(value.trim())){
						xml += "<" + tagName+ ">" + obj.toString() + "</'" + tagName+ ">" ; 
					}else{
						xml += "<" + tagName+ "/>"; 
					}
				} else if(obj.getClass().isArray()){
					Method lengthMethod = obj.getClass().getDeclaredMethod("length", null);
					int length = Integer.parseInt(lengthMethod.invoke(obj, null).toString());
					for(int i=0;i<length; i++){
						Object aobj = getByArray(obj,i);
						xml += convertToXML(aobj);
					}
				}else{
					xml += convertToXML(obj);
				}
			}
		}
		return xml;
	}
	
	public static Object getByArray(Object array, int index) {  
	     return Array.get(array,index);  
	} 
	
	public static void show(Object object) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Method[] methods = object.getClass().getDeclaredMethods();
		StringBuffer sb = new StringBuffer();
		for(Method method:methods){
			sb.append("method.getName() = " + method.getName() + "\r\n");
			sb.append("\tmethod.getModifiers() = " + method.getModifiers() + "\r\n");
			sb.append("\tmethod.getClass() = " + method.getClass() + "\r\n");
			sb.append("\tmethod.getDefaultValue() = " + method.getDefaultValue() + "\r\n");
			sb.append("\tmethod.getGenericReturnType() = " + method.getGenericReturnType() + "\r\n");
			sb.append("\tmethod.getGenericParameterTypes() = " + method.getGenericParameterTypes() + "\r\n");
			sb.append("\tmethod.getExceptionTypes() = " + method.getExceptionTypes() + "\r\n");
			sb.append("\tmethod.getGenericExceptionTypes() = " + method.getGenericExceptionTypes() + "\r\n");
			sb.append("\tmethod.getReturnType() = " + method.getReturnType() + "\r\n");
			sb.append("\tmethod.toGenericString() = " + method.toGenericString() + "\r\n");
			sb.append("\tmethod.toString() = " + method.toString()+ "\r\n");
			sb.append("\tmethod.isAccessible() = " + method.isAccessible()+ "\r\n");
			sb.append("\tmethod.isBridge() = " + method.isBridge()+ "\r\n");
			sb.append("\tmethod.isSynthetic() = " + method.isSynthetic()+ "\r\n");
			sb.append("\tmethod.isVarArgs() = " + method.isVarArgs()+ "\r\n");
			if(method.getName().startsWith("get")){
				sb.append("\tmethod.invoke() = " + method.invoke(object, null)+ "\r\n");
			}
			
		}
		
		Field[] fields = object.getClass().getFields();
		sb.append("Fields = " + fields);
		if(null != fields){
			sb.append("Fields.length = " + fields.length);
		}
		for(Field field:fields){
			sb.append("field.getName() = " + field.getName() + "\r\n");
			sb.append("\tfield.getModifiers() = " + field.getModifiers() + "\r\n");
			sb.append("\tfield.getClass() = " + field.getClass() + "\r\n");
			sb.append("\tfield.getGenericType() = " + field.getGenericType() + "\r\n");
			sb.append("\tfield.field.get(object) = " + field.get(object) + "\r\n");
			sb.append("\tfield.isAccessible() = " + field.isAccessible()+ "\r\n");
			sb.append("\tfield.isBridge() = " + field.isEnumConstant()+ "\r\n");
			sb.append("\tfield.isSynthetic() = " + field.isSynthetic()+ "\r\n");
		}
		
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
//		String xml = convertToXML(customer);
//		System.out.println(xml);
		
		// TODO: Test Reflect class
//		show(customer);
	}

}
