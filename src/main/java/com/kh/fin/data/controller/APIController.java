package com.kh.fin.data.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

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
	
	@ResponseBody
	@RequestMapping(value="", produces="application/json; charset=UTF-8")
	public ArrayList locationInfo() throws IOException {
		System.out.println("도착");
		//OpenAPI서버로 요청하는 url만들기
		String url = "https://apis.data.go.kr/B551011/KorService1/locationBasedList1?serviceKey=JqBbpU80RaM%2BotpAP5CAxpqUfrlpz7%2FNT%2FV4tuBHrrs4aoMORzmFabSkWXHJXOVLMac7U7zdk1jbAUH%2BctPQlg%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.981611&mapY=37.568477&radius=1000&contentTypeId=12";
		
		URL requestUrl = new URL(url);
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
		
		int totalCount = bodyObj.get("totalCount").getAsInt();
		System.out.println(totalCount);
		
		JsonObject itemsObj = bodyObj.getAsJsonObject("items"); // items속성 접근 : [] JsonArray
		System.out.println(itemsObj);
		
		JsonArray itemArr = itemsObj.getAsJsonArray("item"); 
		System.out.println(itemArr);
		
		ArrayList<LocationInfomation> list = new ArrayList();
		
		for (int i = 0; i < itemArr.size(); i++) {
			JsonObject item = itemArr.get(i).getAsJsonObject();
			
			LocationInfomation loca = new LocationInfomation();
			
			loca.setTitle(item.get("title").getAsString());
			loca.setAddress(item.get("addr1").getAsString());
			loca.setMapX(item.get("mapx").getAsString());
			loca.setMapY(item.get("mapy").getAsString());
			loca.setImg(item.get("firstimage").getAsString());

			
			list.add(loca);	
		}
		
		System.out.println(list);
		
		br.close();
		urlConnection.disconnect();
		
		return list;
	}
	
	
	

}
