package com.kh.fin.board.model.vo;

import java.sql.Date;

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
public class Plan {
	private int tripPlanNo;
	private String tripPlannerNo;
	private String tripPlanTitle;
	private String tripStartDate;
	private String tripEndDate;
	private int transportationNo;
	private int locationNo;
	private String tripPlanThumbnail;
	private int attractionNo;
	private String attractionName;
	private String attractionMemo;
	private String attractionAddress;
	private String categoryName;
	
	private String attractionChangeNameImg;
	private int regionNo;
	private String regionName;
	private int tripNday;
	private int maxNday;

}
