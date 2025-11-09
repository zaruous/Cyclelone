/**
 * 
 */
package com.kyj.api.cyclelone.impl.slack;

import java.util.Map;
import java.util.Properties;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyj.api.cyclelone.core.PropertiesUtil;
import com.slack.api.Slack;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

/**
 * 
 */
public class SlackController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SlackController.class);
	// 봇 토큰 (xoxb-...)
	private String SLACK_BOT_TOKEN;
	// 메시지를 보낼 채널 ID (C00000000)
	private String CHANNEL_ID = "";
	private ObjectMapper objectMapper = new ObjectMapper();

	public SlackController() {
		Properties orLoad = PropertiesUtil.createOrLoad(SlackController.class, ()->{
			return PropertiesUtil.of(Map.of("SLACK_BOT_TOKEN", "", "CHANNEL_ID", ""));
		});
		SLACK_BOT_TOKEN = orLoad.getProperty("SLACK_BOT_TOKEN", "");
		CHANNEL_ID = orLoad.getProperty("CHANNEL_ID", "");
	}
	
	public void sendMessage(@NotNull Context ctx) throws Exception {

		
		try {
			
			if(SLACK_BOT_TOKEN == null || SLACK_BOT_TOKEN.isEmpty())
			{
				throw new RuntimeException("SLACK_BOT_TOKEN is empty.");
			}
			
			if(CHANNEL_ID == null || CHANNEL_ID.isEmpty())
			{
				throw new RuntimeException("CHANNEL_ID is empty.");
			}
			
			String body = ctx.body();
			Message value = objectMapper.readValue(body, Message.class);

			Slack slack = Slack.getInstance();

			ChatPostMessageResponse response = slack.methods()
					.chatPostMessage(req -> req.token(SLACK_BOT_TOKEN).channel(CHANNEL_ID)
					.text(value.getMessage())
					// 블록을 사용하여 더 풍부한 메시지 포맷팅 가능
					);

			if (response.isOk()) {
				LOGGER.info("메시지 전송 성공: " + response);
			} else {
				LOGGER.error("메시지 전송 실패: " + response.getError());
			}

		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(HttpStatus.INTERNAL_SERVER_ERROR);
			ctx.result(new RetMessage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()).toJson());
		}

	}

	public static class Message {
		private String message;

		public Message() {

		}

		public Message(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

	public class RetMessage {
		private int status;
		private String message;

		public RetMessage(HttpStatus internalServerError, String message) {
			this.status = internalServerError.getCode();
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String toJson() throws JsonProcessingException {
			return objectMapper.writeValueAsString(this);
		}
	}
}
