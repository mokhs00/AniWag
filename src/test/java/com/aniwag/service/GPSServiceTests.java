package com.aniwag.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class GPSServiceTests {

	@Test
	public void getAddressTest() {
		try {
			URL url = new URL(
					"https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc?request=coordsToaddr&coords=129.1133567,35.2982640&sourcecrs=epsg:4326&output=json&orders=legalcode,admcode");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "9r521tqyv7");
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", "UnL3yipzwuUlbYZX0XuoixpclG4Xpy6rcHMrdiSP");
			conn.setRequestMethod("GET");

			int responseCode = conn.getResponseCode();
			String responseMessage = conn.getResponseMessage();
			// 연결 타임아웃 설정 
	        conn.setConnectTimeout(3000); // 3초 
	        // 읽기 타임아웃 설정 
	        conn.setReadTimeout(3000); // 3초 
//			conn.setDoInput(true);
			InputStream in = conn.getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			byte[] buf = new byte[1024 * 8];
			int length = 0;
			while ((length = in.read(buf)) != -1) {
				out.write(buf, 0, length);
			}

			System.out.println(new String(out.toByteArray(), "UTF-8"));

			log.info(responseCode);
			log.info(responseMessage);

			conn.disconnect();
		} catch (Exception e) {
			log.info(e);
		}

	}

}
