package com.aniwag.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RescueServiceImpl implements RescueService{

	
	private String FCMURL = "";
	private String FCMKEY = "";	
	
	
	
	@Override
	public int RequestRescue() throws Exception{
	
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.header("","")
				.addHeader("", "")
				.build();
		try(Response response = client.newCall(request).execute()){
			String responseString = response.body().string();
		}
				
		
		return 0;
	}

	
	
	
}
