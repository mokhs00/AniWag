package com.aniwag.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aniwag.domain.KakaoPayReadyDTO;
import com.aniwag.service.shopService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/payment/")
public class ShopController {

	@Setter(onMethod_ = @Autowired)
	shopService service;

	@PostMapping(value = "/kakaoPay", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public ResponseEntity<String> kakaoPay(@RequestBody KakaoPayReadyDTO dto) {
		log.info("kakaoPay Read");
		String redirectURL = service.kakaoPayReady(dto);
		return redirectURL != null ? new ResponseEntity<String>(redirectURL, HttpStatus.OK)
				: new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/kakaoPaySuccess")
	@ResponseBody
	public String kakaoPaySuccess() {

		return "결제성공!";
	}

	@GetMapping("/kakaoPayCancel")
	@ResponseBody
	public String kakaoPayCancel() {

		return "결제 취소";
	}

	@GetMapping("/kakaoPayFail")
	@ResponseBody
	public String kakaoPayFail() {

		return "결제 실패";
	}

}
