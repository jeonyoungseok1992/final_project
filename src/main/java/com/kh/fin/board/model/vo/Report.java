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
public class Report {
	private int reportNo;
	private int memberNo;
	private String boardNo;
	private String replyNo;
	private String reportDate;
	private String reportReason;
}
