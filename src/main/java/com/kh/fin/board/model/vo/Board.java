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
	private String tripStartDate;
	private String tripEndDate;
	private String keyword;
	private String condition;
	private String boardStatus;
	private String tripPlanThumbnail;
	private String boardRegionNo;

	
	
}
