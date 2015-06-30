package com.example.serviceauto;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class GetStatus extends Thread {
	private MainActivity m;
	private String statusID;
	
	public GetStatus(MainActivity m, String statusID) {
		super();
		this.m = m;
		this.statusID = statusID;
	}
	
	@Override
	public void run(){
		HttpResponse httpResponse = null;
		
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet();
			httpGet.setURI(new URI("http://10.0.3.2:8000/server.php?registrationID=" 
							+ statusID));
			httpResponse = httpClient.execute(httpGet);
			
			String json = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(json + "--------------");
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(json);
				m.dataMesaj = jsonObject.getString("date");
				m.pretMesaj = jsonObject.getString("estimatedPrice");
				m.stareMesaj = jsonObject.getString("carState");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m.ok2 = 1;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
