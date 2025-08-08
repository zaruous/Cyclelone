/**
 * 
 */
package com.kyj.api.cyclelone;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyj.api.cyclelone.core.DynamicHandler;
import com.kyj.api.cyclelone.core.MappingInfo;
import com.kyj.api.cyclelone.core.MappingItem;
import com.kyj.api.cyclelone.core.MethodInfo;

import io.javalin.Javalin;
import io.javalin.http.Handler;

/**
 * 
 */
public class Starter {

	private static Javalin app;
	private static int port = 11022; // 기본 포트 설정
	private static String MAPPING_FILE = "urlmapping.json";

	/**
	 * @param args
	 * @throws IOException
	 * @throws DatabindException
	 * @throws StreamReadException
	 */
	public static void main(String[] args) throws Exception {

		app = Javalin.create(config -> {
			config.useVirtualThreads = true;
			config.bundledPlugins.enableCors(cfg -> {
				cfg.addRule(r -> {
					r.anyHost();
				});
			});
			config.http.asyncTimeout = 10_000L;
			config.showJavalinBanner = true;
		});
		
		// JSON 파일을 읽어 매핑 정보 로드
		ObjectMapper mapper = new ObjectMapper();
		MappingInfo mappingInfo = mapper.readValue(new File(MAPPING_FILE), MappingInfo.class);

		// 매핑 정보에 따라 라우팅 설정
		for (MappingItem item : mappingInfo.list()) {
			for (MethodInfo methodInfo : item.methods()) {
				Handler handler = DynamicHandler.create(item.controller(), methodInfo.name());

				switch (methodInfo.method().toUpperCase()) {
				case "GET":
					app.get(item.path(), handler);
					break;
				case "POST":
					app.post(item.path(), handler);
					break;
				case "PUT":
					app.put(item.path(), handler);
					break;
				case "DELETE":
					app.delete(item.path(), handler);
					break;
				default:
					System.err.println("Unsupported HTTP method: " + methodInfo.method());
					break;
				}
			}
		}
		
		app.start(port);
		


	}

}
