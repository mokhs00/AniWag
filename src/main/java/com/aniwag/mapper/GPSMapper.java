package com.aniwag.mapper;

import java.util.List;

import com.aniwag.domain.GPSTrackerVO;
import com.aniwag.domain.GPSVO;

public interface GPSMapper {
	
	
	public int create(GPSTrackerVO vo);
	// 기기 삭제
	public int register(GPSTrackerVO vo);
	public int insert(GPSVO vo);
	public List<GPSVO> getList(String gps_tracker_key);
	
	
}
