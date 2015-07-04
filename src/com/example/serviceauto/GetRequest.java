package com.example.serviceauto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class GetRequest extends Thread{
	
	private MainActivity m;
	private String nume;
	private String prenume;
	private String phone = "0725896385";
	private String model;
	private String brand;
	private String serie;
	private String objective = "verificare";
	private String date;

	public GetRequest(MainActivity m, String nume, String prenume, String model, String brand,
			String serie, String date) {
		super();
		this.m = m;
		this.nume = nume;
		this.prenume = prenume;
		this.model = model;
		this.brand = brand;
		this.serie = serie;
		this.date = date;
	}
	
	@Override
	public void run(){
		InputStream io = null;
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://10.0.3.2:8080/ServiceAuto/registerCar");
		
		String json = "";
		
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.accumulate("firstName", prenume);
			jsonObject.accumulate("lastName", nume);
			jsonObject.accumulate("phone", phone);
			jsonObject.accumulate("carSerial", serie);
			jsonObject.accumulate("carBrand", brand);
			jsonObject.accumulate("carModel", model);
			jsonObject.accumulate("objective", objective);
			jsonObject.accumulate("date", date);
			
			json = jsonObject.toString();
			
			StringEntity e = new StringEntity(json);
			httpPost.setEntity(e);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			io = httpResponse.getEntity().getContent();
			String rez = convertInputStreamToString(io);
			System.out.println("------------rez: " + rez);
			m.registrationID = rez;
			m.ok = 1;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }
}
