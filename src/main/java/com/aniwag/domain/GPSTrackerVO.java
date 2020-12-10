package com.aniwag.domain;

import lombok.Data;

@Data
public class GPSTrackerVO {

	// GPS 추적기기 고유 키
	private String gps_tracker_key;
	// 회원 아이디
	private String mem_id;
	// 기기명 -> 회원이 지정
	private String gps_tracker_name;

}
