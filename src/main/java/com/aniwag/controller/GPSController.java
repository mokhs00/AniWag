package com.aniwag.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aniwag.domain.GPSTrackerVO;
import com.aniwag.domain.GPSVO;
import com.aniwag.service.GPSService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/GPS/")
public class GPSController {

	private GPSService service;

	// json 객체로 받아오기 @RequestBody
	// ex ) {"gps_tracker_no":1,"gps_latitude":0.0,"gps_longitude":0.0,"gps_time":0}
	@GetMapping(value = "/insert")
	public ResponseEntity<String> insertGet(@RequestParam("gps_tracker_key") String gps_tracker_key,
			@RequestParam("gps_longitude") String gps_longitude, @RequestParam("gps_latitude") String gps_latitude) {
		GPSVO vo = new GPSVO();
		vo.setGps_tracker_key(gps_tracker_key);
		vo.setGps_latitude(Double.parseDouble(gps_latitude));
		vo.setGps_longitude(Double.parseDouble(gps_longitude));
		log.info("GPS insert : " + vo.getGps_tracker_key());

		int insertCount = service.insert(vo);

		return insertCount == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PostMapping(value = "/insert", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> insertPost(@RequestBody GPSVO vo) {

		log.info("GPS insert : " + vo.getGps_tracker_key());

		int insertCount = service.insert(vo);

		return insertCount == 1 ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

	// ex )
	// [{"gps_tracker_no":null,"gps_latitude":0.0,"gps_longitude":0.0,"gps_time":1595489778000}]
	@GetMapping(value = "/{gps_tracker_key}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<GPSVO>> getList(@PathVariable("gps_tracker_key") String gps_tracker_key) {

		log.info("GPS read || gps_tracker_key : " + gps_tracker_key);

		return new ResponseEntity<>(service.getList(gps_tracker_key), HttpStatus.OK);

	}

	@PostMapping(value = "/register", consumes = "application/json", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<GPSTrackerVO> register(@RequestBody GPSTrackerVO vo) {

		log.info("GPS Tracker Register || vo : " + vo);

		return service.register(vo) != null ? new ResponseEntity<>(vo, HttpStatus.OK)
				: new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

	}

}
