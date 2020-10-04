package com.aniwag.domain;

import java.util.ArrayList;

import lombok.Data;

@Data
public class NotificationDTO {

	private ArrayList<String> registration_ids;
	class data {
		private String title;
		private String text;
	}
	
}
