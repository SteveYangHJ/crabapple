package com.crabapple.sample.vm;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * OOM: 方法区溢出示例
 * @author SteveHJ
 *
 */
public class JavaMethodAreaOOM {
	
	static class OOMObject{}

	/**
	 * VM args: -XX:PermSize=10m -XX:MaxPermSize=10m
	 * @param args
	 */
	public static void main(String[] args) {
		while(true){
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor(){
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable{
					return proxy.invokeSuper(obj, args);
				}
			});
			enhancer.create();
		}
	}

}
