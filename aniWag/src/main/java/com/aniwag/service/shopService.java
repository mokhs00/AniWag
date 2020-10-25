package com.aniwag.service;

import com.aniwag.domain.KakaoPayReadyDTO;
import com.aniwag.domain.kakaoPayApprovalVO;
import com.aniwag.domain.kakaoPayReadyVO;

public interface shopService {

	
	public String kakaoPayReady(KakaoPayReadyDTO dto);
//	public kakaoPayApprovalVO kakaoPayAccept(String pg_token);
	
	
	
}
