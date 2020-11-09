package com.aniwag.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aniwag.domain.KakaoPayReadyDTO;
import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ShopControllerTests {
	private String ContentType = "application/json; charset=UTF-8";
	public static final okhttp3.MediaType JSON = okhttp3.MediaType.get("application/json; charset=utf-8");
	
	
	@Test
	public void kakaoPayReadyPostTest() {		
		KakaoPayReadyDTO dto = new KakaoPayReadyDTO();
		dto.setItem_name("testItem_name");
		dto.setPartner_user_id("testPartner_user_id");
		dto.setQuantity("1");
		dto.setTotal_amount("5500");
		
		Gson gson = new Gson();
		
		okhttp3.RequestBody body = okhttp3.RequestBody.create(gson.toJson(dto),JSON);
		
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.header("Content-Type", ContentType)
				.url("http://localhost:8080/payment/kakaoPay")
				.post(body)
				.build();
		try {
			Response response = client.newCall(request).execute();
			log.info(response.body().string());
			log.info(response.code());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
