package com.aniwag.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class RescueServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private RescueService service;
	
	
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void RequestRescueTest() {
		log.info(service.RequestRescue("a"));
	}

}
