package com.aniwag.service;

import java.util.List;

import com.aniwag.domain.GPSTrackerVO;
import com.aniwag.domain.GPSVO;



public interface GPSService {
	

	// tracker 생성
	public String create();
	// tracker 등록
	public GPSTrackerVO register(GPSTrackerVO vo);	
	// 동물과 연결된 tracker 정보 받기
	// tracker 폐기

	public int insert(GPSVO vo);
	public List<GPSVO> getList(String gps_tracker_key);
	
	// 좌표로 주소 얻어오기
	public void getAddress();
		
	
	

}
