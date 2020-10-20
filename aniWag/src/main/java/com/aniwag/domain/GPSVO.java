package com.aniwag.domain;

import java.util.Date;

import lombok.Data;

@Data
public class GPSVO {

	private String gps_tracker_key;
	private double gps_latitude;
	private double gps_longitude;
	private Date gps_time;
}
