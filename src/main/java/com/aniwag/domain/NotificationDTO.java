package com.aniwag.domain;

import java.util.List;

import lombok.Data;

@Data
public class NotificationDTO {

	private List<String> registration_ids;
	private notification notification = new notification();
	@Data
	public class notification {
		private String title;
		private String text;
	}
	
}
