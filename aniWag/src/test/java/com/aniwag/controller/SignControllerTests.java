package com.aniwag.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aniwag.domain.MemberVO;
import com.aniwag.domain.NotificationDTO;
import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SignControllerTests {
	private String ContentType = "application/json; charset=UTF-8";
	public static final okhttp3.MediaType JSON = okhttp3.MediaType.get("application/json; charset=utf-8");
	
	
	@Test
	public void signUpTest() {
		Gson gson = new Gson();

		MemberVO vo = new MemberVO();
		vo.setMem_id("testId");
		vo.setMem_password("1234");
		vo.setMem_name("testName");
		vo.setMem_addr1("test");
		vo.setMem_addr2("test");
		vo.setMem_addr3("test");
		vo.setMem_email("testEmail");
		vo.setMem_appkey("testAppkey");
		vo.setMem_gender("F");
		vo.setMem_phone("010-0000-0000");
		
		
		log.info(vo);
		RequestBody body = RequestBody.create(gson.toJson(vo), JSON);

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()				
				.header("Content-Type", ContentType)
				.url("http://localhost:8080/SignUp")
				.post(body)
				.build();
		try (Response response = client.newCall(request).execute()) {
			String responseString = response.body().string();
			log.info(responseString);
			log.info(response);
		} catch (Exception e) {
			log.info("RequestRescue ERROR : " + e);
			
		}
	}
	
	
}
