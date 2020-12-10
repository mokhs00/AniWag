package com.aniwag.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aniwag.domain.NotificationDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberMapperTests {

	@Setter(onMethod_ = @Autowired)
	private MemberMapper mapper;
	
	
	public void testGetListAppKey() {
		List<String> list = mapper.getListAppkey("구로구");
		
		log.info(mapper.getListAppkey("양천구"));
		list.forEach(appkey -> log.info(appkey));
	}
	

	
	
	
}
