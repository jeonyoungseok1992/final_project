package com.kh.fin.board.model.vo;

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
public class Recommend {
	private int recommendBoardNo;
	private String recommendTitleImg;
	private String recommendBoardRegion;
	private String recommendBoardTitle;
	private String recommendBoardContent;
	private String recommendBoardCreateDate;
	private String recommendBoardModifyDate;
}
