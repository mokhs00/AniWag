package com.aniwag.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("/rescue")
public class RescueController {
	
	
	// 구조요청 받기 후 FCM 연동 주변 사용자들에게 구조요청 푸시알림
	
	// TODO : Notification 프로세스  -> 기기를 선택하고 구조요청? 그러면 기기 번호를 가지고 조회 가능. 
	// 구조요청 내용은?  ex) title : "#{address}에서 구조요청!" ,test : "#{name}님의 애완동물이 위험해요!" 
//	public ResponseEntity<String> Notification(){
//		
//	}
	
	
	// qr코드 찍고 주인에게 허가요청 보내기.
	

}
