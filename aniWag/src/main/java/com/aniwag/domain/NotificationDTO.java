package com.aniwag.domain;

import java.util.List;

import lombok.Data;

@Data
public class NotificationDTO {

	private List<String> registration_ids;
	private data data = new data();
	@Data
	public class data {
		private String title;
		private String text;
	}
	
}
