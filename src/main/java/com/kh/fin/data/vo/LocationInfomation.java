package com.kh.fin.data.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LocationInfomation {
	private String title;
	private String address;
	private String mapX;
	private String mapY;
	private String img;
}
