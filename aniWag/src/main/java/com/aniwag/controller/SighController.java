package com.aniwag.controller;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class SighController {
	
	
	@RequestMapping("/SighUp")
	public void SighUp() {
		
	}
	
	@RequestMapping("/SighIn")
	public void SighIn() {
		
	}
	
	
	// TODO : SighOut
	public void SighOut() {
		
	}
	
	
	

}
