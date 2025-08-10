/**
 * 
 */
package com.kyj.api.cyclelone.impl.suno;

/**
 
 {"token":null,"gpt_description_prompt":"경쾌하고 밝은 분위기의 여름밤 노래. 더위에 지친 일상에서 벗어나 시원한 밤바람과 별빛 아래 편안함과 행복을 느끼는 감정을 표현했습니다.  여름밤의 정취와 낭만을 즐기는 모습을 그리며 듣는 이로 하여금 긍정적이고 활기찬 에너지를 전달합니다.","mv":"chirp-v3-5","prompt":"",
 "metadata":{"create_mode":"simple","user_tier":"4497580c-f4eb-4f86-9f0e-960eb7c48d7d","lyrics_model":"default","can_control_sliders":[]},
 "make_instrumental":false,"user_uploaded_images_b64":[],"generation_type":"TEXT"}
 */
public class SunoRequest {
	private String token;
	private String gpt_description_prompt;
	private String mv;
	private String prompt;
	private Metadata metadata;
	private boolean make_instrumental;
	private String[] user_uploaded_images_b64;
	private String generation_type;

	// Getters and Setters

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getGpt_description_prompt() {
		return gpt_description_prompt;
	}

	public void setGpt_description_prompt(String gpt_description_prompt) {
		this.gpt_description_prompt = gpt_description_prompt;
	}

	public String getMv() {
		return mv;
	}

	public void setMv(String mv) {
		this.mv = mv;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public boolean isMake_instrumental() {
		return make_instrumental;
	}

	public void setMake_instrumental(boolean make_instrumental) {
		this.make_instrumental = make_instrumental;
	}

	public String[] getUser_uploaded_images_b64() {
		return user_uploaded_images_b64;
	}

	public void setUser_uploaded_images_b64(String[] user_uploaded_images_b64) {
		this.user_uploaded_images_b64 = user_uploaded_images_b64;
	}

	public String getGeneration_type() {
		return generation_type;
	}

	public void setGeneration_type(String generation_type) {
		this.generation_type = generation_type;
	}
}
