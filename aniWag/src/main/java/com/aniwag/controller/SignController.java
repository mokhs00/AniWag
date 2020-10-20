package com.aniwag.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aniwag.domain.MemberVO;
import com.aniwag.service.SignService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@AllArgsConstructor
@RequestMapping("/")
public class SignController {

	SignService service;

	@PostMapping(value = "/SignUp", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> SignUp(@RequestBody MemberVO vo) {
		
		
		log.info(vo);
		
		return service.SignUp(vo) == 1 ? new ResponseEntity<String>(HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/SignIn", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> SignIn(@RequestBody MemberVO vo) {

		return service.SignIn(vo) == 1 ? new ResponseEntity<String>(HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping(value = "/Modify", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<String> Modify(@RequestBody MemberVO vo) {

		
		return service.SignIn(vo) == 1 ? new ResponseEntity<String>(HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	
	
	// TODO : 회원 탈퇴 이름 짓는 게 젤 어려워,,

}
