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
	private Date boardCreateDate;
	private Date boardModifyDate;
	private String boardReport;
	private String boardWirter;
	private int boardTripPlanNo;
	private int boardCategory;
	private String attachement;
	private String keyword;
	private String condition;
	private String boardStatus;
	
	
}
