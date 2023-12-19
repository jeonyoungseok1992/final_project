package com.kh.fin.data.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.fin.data.vo.LocationInfomation;

@Controller
public class APIController {
	
	public static final String SERVICE_KEY ="X3jsxSIDXM2%2FjASS2la%2Ffejz6t8ldQCvxn%2BBolDYTxrKRNHjXBTbQ1BP4xKmf2ZRhavVJaar7JNbKeoOidYFew%3D%3D";
	
	//임동건 추천여행지 공공데이터
//	@ResponseBody
//	@RequestMapping(value="recommendTrip.bo", produces="application/json; charset=UTF-8")
//	public JsonArray recommendInfo() throws IOException {
//		System.out.println("도착");
//		//OpenAPI서버로 요청하는 url만들기
//		String url = "https://apis.data.go.kr/B551011/KorService1/searchFestival1";
//		url += "?serviceKey=" + SERVICE_KEY;
//		url += "&numOfRows=3";
//		url += "&pageNo=1";
//		url += "&MobileOS=ETC";
//		url += "&MobileApp=Mapping";
//		url += "&_type=json";
//		//현재시간 포맷 정의 시작 
//		Date now = new Date();
//		// 원하는 형식의 문자열로 변환하기 위해 SimpleDateFormat을 생성합니다.
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//
//        // SimpleDateFormat을 사용하여 Date 객체를 문자열로 변환합니다.
//        String formattedDate = sdf.format(now);
//	
//		// 현재시간 포맷 정의 끝
//		url += "&eventStartDate=" + formattedDate;
//		URL requestUrl = new URL(url);
//		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
//		urlConnection.setRequestMethod("GET");
//	
//		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//		
//		String responseText = "";
//		String line;
//		
//		while((line = br.readLine()) != null) {
//			responseText += line;
//		}
//		
//
//		
//		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();
//		
//		JsonObject responseObj = totalObj.getAsJsonObject("response"); // response속성에 접근 : {} JsonObject
//		
//		
//		JsonObject bodyObj = responseObj.getAsJsonObject("body"); // body속성에 접근 : {} JsonObject
//	
//		
//		int totalCount = bodyObj.get("totalCount").getAsInt();
//	
//		
//		JsonObject itemsObj = bodyObj.getAsJsonObject("items"); // items속성 접근 : [] JsonArray
//		
//		
//		JsonArray itemArr = itemsObj.getAsJsonArray("item"); 
//		
//		
//		br.close();
//		urlConnection.disconnect();
//		
//		return itemArr;
//	}
	
//	@ResponseBody
//	@RequestMapping(value="recommendTripList.bo", produces="application/json; charset=UTF-8")
//	public JsonArray recommendListInfo() throws IOException {
//		System.out.println("도착");
//		//OpenAPI서버로 요청하는 url만들기
//		String url = "https://apis.data.go.kr/B551011/KorService1/searchFestival1";
//		url += "?serviceKey=" + SERVICE_KEY;
//		url += "&numOfRows=10";
//		url += "&pageNo=1";
//		url += "&MobileOS=ETC";
//		url += "&MobileApp=Mapping";
//		url += "&_type=json";
//		//현재시간 포맷 정의 시작 
//		Date now = new Date();
//		// 원하는 형식의 문자열로 변환하기 위해 SimpleDateFormat을 생성합니다.
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//
//        // SimpleDateFormat을 사용하여 Date 객체를 문자열로 변환합니다.
//        String formattedDate = sdf.format(now);
//	
//		// 현재시간 포맷 정의 끝
//		url += "&eventStartDate=" + formattedDate;
//		URL requestUrl = new URL(url);
//		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
//		urlConnection.setRequestMethod("GET");
//	
//		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//		
//		String responseText = "";
//		String line;
//		
//		while((line = br.readLine()) != null) {
//			responseText += line;
//		}
//		
//
//		
//		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();
//		
//		JsonObject responseObj = totalObj.getAsJsonObject("response"); // response속성에 접근 : {} JsonObject
//		
//		
//		JsonObject bodyObj = responseObj.getAsJsonObject("body"); // body속성에 접근 : {} JsonObject
//	
//		
//		int totalCount = bodyObj.get("totalCount").getAsInt();
//	
//		
//		JsonObject itemsObj = bodyObj.getAsJsonObject("items"); // items속성 접근 : [] JsonArray
//		
//		
//		JsonArray itemArr = itemsObj.getAsJsonArray("item"); 
//		
//		
//		br.close();
//		urlConnection.disconnect();
//		
//		return itemArr;
//	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="attractionList.api", produces="application/json; charset=UTF-8")
	public JsonArray attractionList(String location) throws IOException {
		 String apiKey = "JqBbpU80RaM%2BotpAP5CAxpqUfrlpz7%2FNT%2FV4tuBHrrs4aoMORzmFabSkWXHJXOVLMac7U7zdk1jbAUH%2BctPQlg%3D%3D"; // 여기에 실제 API 키를 넣어주세요
		    String baseUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
		    
		    // URL 구성을 위해 StringBuilder를 사용합니다.
		    StringBuilder urlBuilder = new StringBuilder(baseUrl);
		    urlBuilder.append("?serviceKey=").append(apiKey);
		    urlBuilder.append("&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=12&areaCode=1");
		
		URL requestUrl = new URL(urlBuilder.toString());
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
	
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();
		System.out.println(totalObj);
		JsonObject responseObj = totalObj.getAsJsonObject("response"); // response속성에 접근 : {} JsonObject
		System.out.println(responseObj);
		
		JsonObject bodyObj = responseObj.getAsJsonObject("body"); // body속성에 접근 : {} JsonObject
		System.out.println(bodyObj);
		
		JsonObject itemsObj = bodyObj.getAsJsonObject("items"); // items속성 접근 : [] JsonArray
		System.out.println(itemsObj);
		
		JsonArray itemArr = itemsObj.getAsJsonArray("item"); 
		System.out.println(itemArr);
		
		br.close();
		urlConnection.disconnect();
		
		return itemArr;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="attFoodList.api", produces="application/json; charset=UTF-8")
	public JsonArray attractionFoodList(String location) throws IOException {
		String apiKey = "JqBbpU80RaM%2BotpAP5CAxpqUfrlpz7%2FNT%2FV4tuBHrrs4aoMORzmFabSkWXHJXOVLMac7U7zdk1jbAUH%2BctPQlg%3D%3D"; // 여기에 실제 API 키를 넣어주세요
	    String baseUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
	    
	    // URL 구성을 위해 StringBuilder를 사용합니다.
	    StringBuilder urlBuilder = new StringBuilder(baseUrl);
	    urlBuilder.append("?serviceKey=").append(apiKey);
	    urlBuilder.append("&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=39&areaCode=1");
	    
	    URL requestUrl = new URL(urlBuilder.toString());
	    HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
	    urlConnection.setRequestMethod("GET");

	    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

	    StringBuilder responseText = new StringBuilder();
	    String line;

	    while ((line = br.readLine()) != null) {
	        responseText.append(line);
	    }

	    JsonObject totalObj = JsonParser.parseString(responseText.toString()).getAsJsonObject();
	    JsonObject responseObj = totalObj.getAsJsonObject("response");
	    JsonObject bodyObj = responseObj.getAsJsonObject("body");
	    JsonObject itemsObj = bodyObj.getAsJsonObject("items");
	    JsonArray itemArr = itemsObj.getAsJsonArray("item");
	    
	    br.close();
	    urlConnection.disconnect();
	    
	    return itemArr;
	}
	
	
	

	@ResponseBody
	@RequestMapping(value="attEventList.api", produces="application/json; charset=UTF-8")
	public JsonArray attEventList(String location) throws IOException {
		String apiKey = "JqBbpU80RaM%2BotpAP5CAxpqUfrlpz7%2FNT%2FV4tuBHrrs4aoMORzmFabSkWXHJXOVLMac7U7zdk1jbAUH%2BctPQlg%3D%3D"; // 여기에 실제 API 키를 넣어주세요
	    String baseUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
	    
	    // URL 구성을 위해 StringBuilder를 사용합니다.
	    StringBuilder urlBuilder = new StringBuilder(baseUrl);
	    urlBuilder.append("?serviceKey=").append(apiKey);
	    urlBuilder.append("&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=15&areaCode=1");
	    
	    URL requestUrl = new URL(urlBuilder.toString());
	    HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
	    urlConnection.setRequestMethod("GET");

	    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

	    StringBuilder responseText = new StringBuilder();
	    String line;

	    while ((line = br.readLine()) != null) {
	        responseText.append(line);
	    }

	    JsonObject totalObj = JsonParser.parseString(responseText.toString()).getAsJsonObject();
	    JsonObject responseObj = totalObj.getAsJsonObject("response");
	    JsonObject bodyObj = responseObj.getAsJsonObject("body");
	    JsonObject itemsObj = bodyObj.getAsJsonObject("items");
	    JsonArray itemArr = itemsObj.getAsJsonArray("item");
	    
	    br.close();
	    urlConnection.disconnect();
	    
	    return itemArr;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="kakaoMap.api", produces="application/json; charset=UTF-8")
	public JsonArray kakaoMap(String location) throws IOException {
		String apiKey = "JqBbpU80RaM%2BotpAP5CAxpqUfrlpz7%2FNT%2FV4tuBHrrs4aoMORzmFabSkWXHJXOVLMac7U7zdk1jbAUH%2BctPQlg%3D%3D"; // 여기에 실제 API 키를 넣어주세요
	    String baseUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
	    
	    // URL 구성을 위해 StringBuilder를 사용합니다.
	    StringBuilder urlBuilder = new StringBuilder(baseUrl);
	    urlBuilder.append("?serviceKey=").append(apiKey);
	    urlBuilder.append("&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=15&areaCode=1");
	    
	    URL requestUrl = new URL(urlBuilder.toString());
	    HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
	    urlConnection.setRequestMethod("GET");

	    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

	    StringBuilder responseText = new StringBuilder();
	    String line;

	    while ((line = br.readLine()) != null) {
	        responseText.append(line);
	    }

	    JsonObject totalObj = JsonParser.parseString(responseText.toString()).getAsJsonObject();
	    JsonObject responseObj = totalObj.getAsJsonObject("response");
	    JsonObject bodyObj = responseObj.getAsJsonObject("body");
	    JsonObject itemsObj = bodyObj.getAsJsonObject("items");
	    JsonArray itemArr = itemsObj.getAsJsonArray("item");
	    
	    br.close();
	    urlConnection.disconnect();
	    
	    return itemArr;
	}
	
	
	
	
	
	

}
