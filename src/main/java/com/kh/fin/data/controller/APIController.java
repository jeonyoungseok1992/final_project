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
	public JsonArray attractionList(int regionNo, String regionX, String regionY) throws IOException {
		String apiKey = "JqBbpU80RaM%2BotpAP5CAxpqUfrlpz7%2FNT%2FV4tuBHrrs4aoMORzmFabSkWXHJXOVLMac7U7zdk1jbAUH%2BctPQlg%3D%3D"; // 여기에 실제 API 키를 넣어주세요
	    String baseUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
	    String baseUrl2 = "https://apis.data.go.kr/B551011/KorService1/locationBasedList1";
	    // URL 구성을 위해 StringBuilder를 사용합니다.
	    StringBuilder urlBuilder = new StringBuilder(baseUrl);
	    urlBuilder.append("?serviceKey=").append(apiKey);
	    
	    StringBuilder urlBuilder2 = new StringBuilder(baseUrl2);
	    urlBuilder2.append("?serviceKey=").append(apiKey);
	    if (1 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=12&areaCode=1");
	    } else if (2 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=12&areaCode=2");
	    } else if (3 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=12&areaCode=3");
	    }else if (4 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=12&areaCode=5");
	    } else if (5 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=12&areaCode=4");
	    } else if (6 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=12&areaCode=7");
	    } else if (7 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=12&areaCode=6");
	    } else if (8 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.0533388346432&mapY=37.28899674266975&radius=10000&contentTypeId=12");
	    } else if (9 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.73000431027823&mapY=37.88529271588802&radius=10000&contentTypeId=12");
	    } else if (10 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.49159874489123&mapY=36.63535445860653&radius=10000&contentTypeId=12");
	    } else if (11 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.67292904748435&mapY=36.65878596833474&radius=10000&contentTypeId=12");
	    } else if (12 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.10887986301702&mapY=35.82017977840777&radius=10000&contentTypeId=12");
	    } else if (13 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.46278106600714&mapY=34.81602307435137&radius=10000&contentTypeId=12");
	    } else if (14 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=128.50580062317167&mapY=36.575951606061714&radius=10000&contentTypeId=12");
	    } else if (15 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=128.69205767632846&mapY=35.23763542805831&radius=10000&contentTypeId=12");
	    } else if (16 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.49827754759696&mapY=33.48892035330222&radius=10000&contentTypeId=12");					  
	    } 
	    
	    if(1 <= regionNo && regionNo <= 7) {
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
	    	
	    } else {
	    	URL requestUrl = new URL(urlBuilder2.toString());
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
	
	
	
	@ResponseBody
	@RequestMapping(value="attFoodList.api", produces="application/json; charset=UTF-8")
	public JsonArray attractionFoodList(int regionNo, String regionX, String regionY) throws IOException {
		String apiKey = "JqBbpU80RaM%2BotpAP5CAxpqUfrlpz7%2FNT%2FV4tuBHrrs4aoMORzmFabSkWXHJXOVLMac7U7zdk1jbAUH%2BctPQlg%3D%3D"; // 여기에 실제 API 키를 넣어주세요
	    String baseUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
	    String baseUrl2 = "https://apis.data.go.kr/B551011/KorService1/locationBasedList1";
	    // URL 구성을 위해 StringBuilder를 사용합니다.
	    StringBuilder urlBuilder = new StringBuilder(baseUrl);
	    urlBuilder.append("?serviceKey=").append(apiKey);
	    
	    StringBuilder urlBuilder2 = new StringBuilder(baseUrl2);
	    urlBuilder2.append("?serviceKey=").append(apiKey);
	    if (1 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=39&areaCode=1");
	    } else if (2 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=39&areaCode=2");
	    } else if (3 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=39&areaCode=3");
	    }else if (4 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=39&areaCode=5");
	    } else if (5 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=39&areaCode=4");
	    } else if (6 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=39&areaCode=7");
	    } else if (7 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=39&areaCode=6");
	    } else if (8 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.0533388346432&mapY=37.28899674266975&radius=10000&contentTypeId=39");
	    } else if (9 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.73000431027823&mapY=37.88529271588802&radius=10000&contentTypeId=39");
	    } else if (10 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.49159874489123&mapY=36.63535445860653&radius=10000&contentTypeId=39");
	    } else if (11 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.67292904748435&mapY=36.65878596833474&radius=10000&contentTypeId=39");
	    } else if (12 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.10887986301702&mapY=35.82017977840777&radius=10000&contentTypeId=39");
	    } else if (13 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.46278106600714&mapY=34.81602307435137&radius=10000&contentTypeId=39");
	    } else if (14 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=128.50580062317167&mapY=36.575951606061714&radius=10000&contentTypeId=39");
	    } else if (15 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=128.69205767632846&mapY=35.23763542805831&radius=10000&contentTypeId=39");
	    } else if (16 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.49827754759696&mapY=33.48892035330222&radius=10000&contentTypeId=39");					  
	    } 
	    
	    
	    if(1 <= regionNo && regionNo <= 7) {
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
	    	
	    } else {
	    	URL requestUrl = new URL(urlBuilder2.toString());
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
	
	
	

	@ResponseBody
	@RequestMapping(value="attEventList.api", produces="application/json; charset=UTF-8")
	public JsonArray attEventList(int regionNo) throws IOException {
		String apiKey = "JqBbpU80RaM%2BotpAP5CAxpqUfrlpz7%2FNT%2FV4tuBHrrs4aoMORzmFabSkWXHJXOVLMac7U7zdk1jbAUH%2BctPQlg%3D%3D"; // 여기에 실제 API 키를 넣어주세요
	    String baseUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
	    String baseUrl2 = "https://apis.data.go.kr/B551011/KorService1/locationBasedList1";
	    // URL 구성을 위해 StringBuilder를 사용합니다.
	    StringBuilder urlBuilder = new StringBuilder(baseUrl);
	    urlBuilder.append("?serviceKey=").append(apiKey);
	    
	    StringBuilder urlBuilder2 = new StringBuilder(baseUrl2);
	    urlBuilder2.append("?serviceKey=").append(apiKey);
	    if (1 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=15&areaCode=1");
	    } else if (2 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=15&areaCode=2");
	    } else if (3 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=15&areaCode=3");
	    }else if (4 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=15&areaCode=5");
	    } else if (5 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=15&areaCode=4");
	    } else if (6 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=15&areaCode=7");
	    } else if (7 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=15&areaCode=6");
	    } else if (8 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.0533388346432&mapY=37.28899674266975&radius=10000&contentTypeId=15");
	    } else if (9 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.73000431027823&mapY=37.88529271588802&radius=10000&contentTypeId=15");
	    } else if (10 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.49159874489123&mapY=36.63535445860653&radius=10000&contentTypeId=15");
	    } else if (11 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.67292904748435&mapY=36.65878596833474&radius=10000&contentTypeId=15");
	    } else if (12 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.10887986301702&mapY=35.82017977840777&radius=10000&contentTypeId=15");
	    } else if (13 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.46278106600714&mapY=34.81602307435137&radius=10000&contentTypeId=15");
	    } else if (14 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=128.50580062317167&mapY=36.575951606061714&radius=10000&contentTypeId=15");
	    } else if (15 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=128.69205767632846&mapY=35.23763542805831&radius=10000&contentTypeId=15");
	    } else if (16 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.49827754759696&mapY=33.48892035330222&radius=10000&contentTypeId=15");					  
	    } 
	    
	    
	    if(1 <= regionNo && regionNo <= 7) {
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
	    	
	    } else {
	    	URL requestUrl = new URL(urlBuilder2.toString());
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
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="attMotelList.api", produces="application/json; charset=UTF-8")
	public JsonArray attMotelList(int regionNo) throws IOException {
		System.out.println("모텔 옴");
		String apiKey = "JqBbpU80RaM%2BotpAP5CAxpqUfrlpz7%2FNT%2FV4tuBHrrs4aoMORzmFabSkWXHJXOVLMac7U7zdk1jbAUH%2BctPQlg%3D%3D"; // 여기에 실제 API 키를 넣어주세요
	    String baseUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
	    String baseUrl2 = "https://apis.data.go.kr/B551011/KorService1/locationBasedList1";
	    // URL 구성을 위해 StringBuilder를 사용합니다.
	    StringBuilder urlBuilder = new StringBuilder(baseUrl);
	    urlBuilder.append("?serviceKey=").append(apiKey);
	    
	    StringBuilder urlBuilder2 = new StringBuilder(baseUrl2);
	    urlBuilder2.append("?serviceKey=").append(apiKey);
	    if (1 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=32&areaCode=1");
	    } else if (2 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=32&areaCode=2");
	    } else if (3 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=32&areaCode=3");
	    }else if (4 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=32&areaCode=5");
	    } else if (5 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=32&areaCode=4");
	    } else if (6 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=32&areaCode=7");
	    } else if (7 ==regionNo) {
	    	urlBuilder.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=32&areaCode=6");
	    } else if (8 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.0533328346432&mapY=37.28899674266975&radius=10000&contentTypeId=32");
	    } else if (9 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.73000431027823&mapY=37.88529271588802&radius=10000&contentTypeId=32");
	    } else if (10 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.49159874489123&mapY=36.63535445860653&radius=10000&contentTypeId=32");
	    } else if (11 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.67292904748435&mapY=36.65878596833474&radius=10000&contentTypeId=32");
	    } else if (12 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=127.10887986301702&mapY=35.82017977840777&radius=10000&contentTypeId=32");
	    } else if (13 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.46278106600714&mapY=34.81602307435137&radius=10000&contentTypeId=32");
	    } else if (14 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=128.50580062317167&mapY=36.575951606061714&radius=10000&contentTypeId=32");
	    } else if (15 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=128.69205767632846&mapY=35.23763542805831&radius=10000&contentTypeId=32");
	    } else if (16 ==regionNo) {
	    	urlBuilder2.append("&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.49827754759696&mapY=33.48892035330222&radius=10000&contentTypeId=32");					  
	    } 
	    
	    
	    if(1 <= regionNo && regionNo <= 7) {
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
	    	
	    } else {
	    	URL requestUrl = new URL(urlBuilder2.toString());
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="attCategory.api", produces="application/json; charset=UTF-8")
	public JsonArray attractionTotalList(String category) throws IOException {
		String apiKey = "JqBbpU80RaM%2BotpAP5CAxpqUfrlpz7%2FNT%2FV4tuBHrrs4aoMORzmFabSkWXHJXOVLMac7U7zdk1jbAUH%2BctPQlg%3D%3D"; // 여기에 실제 API 키를 넣어주세요
	    String baseUrl = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
	    
	    // URL 구성을 위해 StringBuilder를 사용합니다.
	    StringBuilder urlBuilder = new StringBuilder(baseUrl);
	    urlBuilder.append("?serviceKey=").append(apiKey);
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    if ("명소".equals(category)) {
	        // 행사에 대한 URL 조건 추가
	    	urlBuilder.append("&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=12&areaCode=1");
	    } else if ("식당".equals(category)) {
	        // 식당에 대한 URL 조건 추가
	    	urlBuilder.append("&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=39&areaCode=1");
	    } else if ("행사".equals(category)) {
	        // 명소에 대한 URL 조건 추가
	    	urlBuilder.append("&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&contentTypeId=15&areaCode=1");
	    }
	    
	    
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
	public JsonArray kakaoMap(String regionX,String regionY) throws IOException {
		String apiKey = "X3jsxSIDXM2%2FjASS2la%2Ffejz6t8ldQCvxn%2BBolDYTxrKRNHjXBTbQ1BP4xKmf2ZRhavVJaar7JNbKeoOidYFew%3D%3D"; // 여기에 실제 API 키를 넣어주세요
	    String baseUrl = "https://apis.data.go.kr/B551011/KorService1/locationBasedList1";
	    String mapX = "&mapX=" + regionX;
	    String mapY = "&mapY=" + regionY;
	    // URL 구성을 위해 StringBuilder를 사용합니다.
	    StringBuilder urlBuilder = new StringBuilder(baseUrl);
	    urlBuilder.append("?serviceKey=").append(apiKey).append(mapX).append(mapY);;
	    urlBuilder.append("&numOfRows=20&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&radius=20000&contentTypeId=12");
	    
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
