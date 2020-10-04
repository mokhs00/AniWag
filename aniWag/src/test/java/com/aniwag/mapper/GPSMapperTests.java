package com.aniwag.mapper;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aniwag.domain.GPSTrackerVO;
import com.aniwag.domain.GPSVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class GPSMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private GPSMapper mapper; 
	
	
	
	public void insertTest() {
		
		GPSVO vo = new GPSVO();
		vo.setGps_latitude(0);
		vo.setGps_longitude(0);
		vo.setGps_tracker_no(0L);
				
		
		log.info(""+ mapper.insert(vo));
		
	}
	
	
	public void getListTest() {		
		List<GPSVO> list = mapper.getList(1L);
		list.forEach(gps -> log.info(gps));
		
	}
	
	@Test
	public void createTest() {
		GPSTrackerVO vo = new GPSTrackerVO();		
		String key = UUID.randomUUID().toString().replace("-", "");
		key = key.substring(0,8);
		vo.setGps_tracker_key(key);
		int result =mapper.create(vo);
		log.info("GPSTracker Create Result : " + result);

	}
	

}
