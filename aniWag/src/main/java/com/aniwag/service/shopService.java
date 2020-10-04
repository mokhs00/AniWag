package com.aniwag.service;

import com.aniwag.domain.kakaoPayApprovalVO;
import com.aniwag.domain.kakaoPayReadyVO;

public interface shopService {

	
	public String kakaoPayReady();
	public kakaoPayApprovalVO kakaoPayAccept(String pg_token);
	
	
	
}
