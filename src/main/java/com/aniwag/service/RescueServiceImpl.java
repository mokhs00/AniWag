package com.aniwag.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniwag.domain.NotificationDTO;
import com.aniwag.mapper.MemberMapper;
import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
@Log4j
public class RescueServiceImpl implements RescueService {

	private String FCMURL = "https://fcm.googleapis.com/fcm/send";
	private String FCMAutorizationHeader = "key=AAAATE0XOVA:APA91bFz5Z2QXG9hpITPDecWdMSrXrsEXnZ3lTcqwLsRNnmBzBbv4hgMTUYokF6QvOV-4WArOqjmviMfRI_nAQAxsHPsWVeBFxmOi3UHL7MuTDZrWiuUyvIc9lpNUO7FCs3Xu3IBymzf";
	private String ContentType = "application/json; charset=UTF-8";
	public static final okhttp3.MediaType JSON = okhttp3.MediaType.get("application/json; charset=utf-8");

	private String titlePrefix = "🚨[";
	private String titleSuffix = "도움요청]";
	private String text = "누군가 애타게 반려동물을 찾고 있어요! 손길이 필요합니다!";
	
	@Setter(onMethod_ = @Autowired)
	MemberMapper mapper;


	@Override
	public int RequestRescue(String addr2) {

		Gson gson = new Gson();

		NotificationDTO notificationDTO = new NotificationDTO();		
		notificationDTO.setRegistration_ids(mapper.getListAppkey(addr2));		
		notificationDTO.getNotification().setTitle(titlePrefix+ addr2 + titleSuffix);
		notificationDTO.getNotification().setText(text);		
		
		log.info(notificationDTO);
		RequestBody body = RequestBody.create(gson.toJson(notificationDTO), JSON);

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.header("Authorization", FCMAutorizationHeader)
				.addHeader("Content-Type", ContentType)
				.url(FCMURL)
				.post(body)
				.build();
		try (Response response = client.newCall(request).execute()) {
			String responseString = response.body().string();
			log.info(responseString);
			log.info(response);
		} catch (Exception e) {
			log.info("RequestRescue ERROR : " + e);
			return -1;
		}

		return 1;
	}

}
