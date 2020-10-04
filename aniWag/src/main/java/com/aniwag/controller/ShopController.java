package com.aniwag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.aniwag.service.shopService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/payment/")
public class ShopController {
	
	
	@Setter(onMethod_ = @Autowired)
	shopService service;
	
	@GetMapping("/index")
	public void index() {
		
	}
	
	@GetMapping("/test")
	public void test(Model model) {		
	   
	}
	@GetMapping("/kakaoPay")
	public void kakaoPayGet() {
		log.info("kakaoPay Read");
		
	}
	
	@PostMapping("/kakaoPay")
	public String kakaoPay() {
		log.info("kakao post. . .");
		return "redirect:" + service.kakaoPayReady();
	}
	
	@GetMapping("/kakaoPaySuccess")
	// pg_token은 결제 승인 API 호출 시 사용합니다. 사용자가 결제를 취소한 경우의 처리는 공통 가이드를 참고합니다.
	public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
		log.info("kakaoPaySuccess get pg_token . . .");
		log.info("pg_token : " + pg_token);
		model.addAttribute("info",service.kakaoPayAccept(pg_token));
		return "/payment/kakaoPayAccept";
		
		
	}
	
	
	


}
