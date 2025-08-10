/**
 * 
 */
package com.kyj.api.cyclelone.impl.suno;

/**
"metadata":{"create_mode":"simple","user_tier":"4497580c-f4eb-4f86-9f0e-960eb7c48d7d","lyrics_model":"default","can_control_sliders":[]},

 */
public class Metadata {
	private String create_mode;
	private String user_tier;
	private String lyrics_model;
	private String[] can_control_sliders;

	// Getters and Setters
	public String getCreate_mode() {
		return create_mode;
	}

	public void setCreate_mode(String create_mode) {
		this.create_mode = create_mode;
	}

	public String getUser_tier() {
		return user_tier;
	}

	public void setUser_tier(String user_tier) {
		this.user_tier = user_tier;
	}

	public String getLyrics_model() {
		return lyrics_model;
	}

	public void setLyrics_model(String lyrics_model) {
		this.lyrics_model = lyrics_model;
	}

	public String[] getCan_control_sliders() {
		return can_control_sliders;
	}

	public void setCan_control_sliders(String[] can_control_sliders) {
		this.can_control_sliders = can_control_sliders;
	}
}
