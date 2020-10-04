package com.aniwag.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.aniwag.domain.GPSTrackerVO;
import com.aniwag.domain.GPSVO;
import com.aniwag.mapper.GPSMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class GPSServiceImpl implements GPSService {

	private GPSMapper mapper;

	@Override
	public int insert(GPSVO vo) {

		log.info("GPS data insert. . .");
		return mapper.insert(vo);
	}

	@Override
	public List<GPSVO> getList(Long gps_tracker_no) {
		log.info("GPS read gps. . .");
		return mapper.getList(gps_tracker_no);
	}

	@Override
	public String create() {
		GPSTrackerVO vo = new GPSTrackerVO();
		String key = UUID.randomUUID().toString().replace("-", "");
		key.substring(0, 8);
		vo.setGps_tracker_key(key);
		int result = mapper.create(vo);
		log.info("GPSTracker Create Result : " + result);

		return result == 1 ? key : this.create();
	}

	@Override
	public GPSTrackerVO register(GPSTrackerVO vo) {

		int result = mapper.register(vo);

		return result == 1 ? vo : null;
	}

	@Override
	public void getAddress() {
		try {
			URL url = new URL(
					"https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?request=coordsToaddr&coords=129.1133567,35.2982640&sourcecrs=epsg:4326&output=json&orders=legalcode,admcode");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "9r521tqyv7");
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", "UnL3yipzwuUlbYZX0XuoixpclG4Xpy6rcHMrdiSP");
			int responseCode = conn.getResponseCode();
			String responseMessage = conn.getResponseMessage();
			
			
			
		} catch (Exception e) {

		}

	}

}
