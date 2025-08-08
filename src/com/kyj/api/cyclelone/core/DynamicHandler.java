/**
 * 
 */
package com.kyj.api.cyclelone.core;

import java.lang.reflect.Method;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * 
 */
public class DynamicHandler {

	public static Handler create(String controllerClassName, String methodName) {
		return ctx -> {
			try {
				// 컨트롤러 클래스를 동적으로 로드
				Class<?> controllerClass = Class.forName(controllerClassName);
				Object controllerInstance = controllerClass.getDeclaredConstructor().newInstance();

				// 지정된 메서드를 리플렉션으로 호출
				// 메서드 시그니처가 (Context ctx) 라고 가정합니다.
				Method method = controllerClass.getMethod(methodName, Context.class);
				method.invoke(controllerInstance, ctx);
			} catch (ClassNotFoundException e) {
				ctx.status(500).result("Error: Controller class not found - " + controllerClassName);
			} catch (NoSuchMethodException e) {
				ctx.status(500).result("Error: Method not found - " + methodName);
			} catch (Exception e) {
				ctx.status(500).result("Internal Server Error: " + e.getMessage());
			}
		};
	}

}
