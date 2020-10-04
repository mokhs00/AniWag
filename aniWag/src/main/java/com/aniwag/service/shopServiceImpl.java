package com.aniwag.service;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.aniwag.domain.kakaoPayApprovalVO;
import com.aniwag.domain.kakaoPayReadyVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class shopServiceImpl implements shopService {
// 

	private static final String kakaoPayHOST = "https://kapi.kakao.com";

	private kakaoPayReadyVO kakaoPayReadyVO;
	private kakaoPayApprovalVO kakaoPayApprovalVO;

	// 가맹점 코드 > 테스트용 : 'TC0ONETIME'
	private String CID = "TC0ONETIME";
	private String adminKey = "f46df10365dac65a02bc1e51d0116bc1";

	@Override
	public String kakaoPayReady() {

		// REST 동기방식 처리 -> 결제이므로 동기처리가 필요!
		RestTemplate restTemplate = new RestTemplate();

		// HTTP Header
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + adminKey);
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		// Body MultiValueMap
		// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/LinkedMultiValueMap.html
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", CID);
		params.add("partner_order_id", "CarpeDM");
		params.add("partner_user_id", "사용자 1");
		params.add("item_name", "갤럭시S9");
		params.add("quantity", "1");
		params.add("total_amount", "2100");
		params.add("tax_free_amount", "100");
		params.add("approval_url", "http://localhost:8080/payment/kakaoPaySuccess"); // 결제 성공 시 redirect url, 최대
																								// 255자
		params.add("cancel_url", "http://localhost:8080/payment/kakaoPayCancel"); // 결제 취소 시 redirect url, 최대
																							// 255자
		params.add("fail_url", "http://localhost:8080/payment/kakaoPayFail"); // 결제 실패 시 redirect url, 최대 255자

		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		

		try {
		
			kakaoPayReadyVO = restTemplate.postForObject(new URI(kakaoPayHOST + "/v1/payment/ready"), body,
					kakaoPayReadyVO.class);
			log.info("" + kakaoPayReadyVO);
			
			return kakaoPayReadyVO.getNext_redirect_pc_url();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/payment/kakaoPay";

	}

	@Override
	public kakaoPayApprovalVO kakaoPayAccept(String pg_token) {
		RestTemplate restTemplate = new RestTemplate();
		
		// HTTP HEADER
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " + adminKey);
		headers.add("Content-type", MediaType.APPLICATION_FORM_URLENCODED + ";charset=utf-8");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		// HTTP BODY
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		
		params.add("cid", CID);
		params.add("tid", kakaoPayReadyVO.getTid());
		params.add("partner_order_id", "CarpeDM");
		params.add("partner_user_id", "사용자 1");
		params.add("pg_token",pg_token);
		
		HttpEntity<MultiValueMap<String, String>> body 
		= new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		try {
			kakaoPayApprovalVO = restTemplate.postForObject(new URI(kakaoPayHOST+"/v1/payment/approve"), body, kakaoPayApprovalVO.class);
			log.info(" " + kakaoPayApprovalVO);
			return kakaoPayApprovalVO;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	

}
