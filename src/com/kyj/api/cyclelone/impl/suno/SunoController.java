/**
 * 
 */
package com.kyj.api.cyclelone.impl.suno;

import java.net.URI;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jetbrains.annotations.NotNull;

import io.javalin.http.Context;

/**
 * 
 */
public class SunoController {

	public void generate(@NotNull Context ctx) throws Exception {
		/**
		 *{"token":null,"gpt_description_prompt":"경쾌하고 밝은 분위기의 여름밤 노래. 더위에 지친 일상에서 벗어나 시원한 밤바람과 별빛 아래 편안함과 행복을 느끼는 감정을 표현했습니다.  여름밤의 정취와 낭만을 즐기는 모습을 그리며 듣는 이로 하여금 긍정적이고 활기찬 에너지를 전달합니다.","mv":"chirp-v3-5","prompt":"","metadata":{"create_mode":"simple","user_tier":"4497580c-f4eb-4f86-9f0e-960eb7c48d7d","lyrics_model":"default","can_control_sliders":[]},"make_instrumental":false,"user_uploaded_images_b64":[],"generation_type":"TEXT"}
		 */
		SunoRequest bodyAsClass = ctx.bodyAsClass(SunoRequest.class);
		System.out.println(bodyAsClass.getPrompt());
//		POST /api/generate/v2-web/ HTTP/1.1
		
		//Origin: https://suno.com
		
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		HttpClient httpClient = httpClientBuilder.build();
		
		HttpPost request = new HttpPost("https://suno.com/api/generate/v2-web/");
		request.addHeader("Origin", "https://suno.com");
		request.addHeader("Host", "studio-api.prod.suno.com");
		request.addHeader("Referer", "https://suno.com/");
		request.addHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImNhdCI6ImNsX0I3ZDRQRDExMUFBQSIsImtpZCI6Imluc18yT1o2eU1EZzhscWRKRWloMXJvemY4T3ptZG4iLCJ0eXAiOiJKV1QifQ.eyJhdWQiOiJzdW5vLWFwaSIsImF6cCI6Imh0dHBzOi8vc3Vuby5jb20iLCJleHAiOjE3NTQ4MzI1OTQsImZ2YSI6WzI5MiwtMV0sImh0dHBzOi8vc3Vuby5haS9jbGFpbXMvY2xlcmtfaWQiOiJ1c2VyXzMweWZlZzJSSG9weUtVZW1HZEF1Zjk5TkRVYiIsImh0dHBzOi8vc3Vuby5haS9jbGFpbXMvZW1haWwiOiJjYWxsYWtyc29zMkBnbWFpbC5jb20iLCJodHRwczovL3N1bm8uYWkvY2xhaW1zL3Bob25lIjpudWxsLCJpYXQiOjE3NTQ4Mjg5OTQsImlzcyI6Imh0dHBzOi8vY2xlcmsuc3Vuby5jb20iLCJqdGkiOiJjNGZkMzAxMWYyNTk3NzJjN2VkNiIsIm5iZiI6MTc1NDgyODk4NCwic2lkIjoic2Vzc18zMTVRVDlFVjRVY0hHdzNWU2VsNm1HZFBuMWkiLCJzdWIiOiJ1c2VyXzMweWZlZzJSSG9weUtVZW1HZEF1Zjk5TkRVYiJ9.g00RkulE6moJX2OdKvIHBCuKhGZgEEXpjDPSAJ3F5Tk9_xGBBeA8iLF5IqlYAh1pmdQAtETkzJZl_IRS9eJMbeQjQ-T4LcbpJVLn5QeIdSgyFk6vL8nTiO2-tlvK8lmKmTpUa5bXY7udAwJ1JYFivu9phoBk0RfBHqtpg73f_rP7RF6RNtC7l9xNjX1DvNWFpbZJLdXe4s3rNgYx2exgUCksLWKP-HN31HRn4Ut6zOafhh1brNFQNq8jU2WvW_CCmGXSweb6Km5hs78yx2TCeEXJpHsz7qGs19YEkr1hquQPhwysProdUvXGjjO7xYa3N5amIkDzl4Lnv27hPczkTA");
		
		HttpResponse execute = httpClient.execute(request);
		ctx.status(execute.getStatusLine().getStatusCode());
		ctx.result(execute.getEntity().getContent());
		
		//.post("https://suno.com/api/generate/v2-web/", bodyAsClass, ctx.res(), "application/json");
	}
}
