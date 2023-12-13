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
public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardCreateDate;
	private String boardModifyDate;
	private String boardReport;
	private String boardWriter;
	private int boardTripPlanNo;
	private int boardCategory;
	private String attachement;
	private Date tripStartDate;
	private Date tripEndDate;
	private String keyword;
	private String condition;
	private String boardStatus;
	private String nameRegion;
	private int goodCount;
	private String tripPlanThumbnail;
	private String boardRegionNo;
	private int tripPlanNo;
	private int locationNo;
	private String tripNday;
	private String attractionNo;
	private String attracionName;
	private String attractionChangeNameImg;
	private int memberNo;
	private int regionNo;
	private String memberProfileImg;

	
	
}
