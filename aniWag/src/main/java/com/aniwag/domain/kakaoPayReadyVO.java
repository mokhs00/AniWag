package com.aniwag.domain;

import java.util.Date;

import lombok.Data;

@Data
public class kakaoPayReadyVO {
	
	private String tid;
//	private String next_redirect_app_url;
//	private String next_redirect_mobile_url;
	private String next_redirect_pc_url;
//	private String android_app_scheme;
	private Date created_at; // 결제 준비 요청 시간
	

}
