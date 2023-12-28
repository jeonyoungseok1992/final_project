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
public class Reply {

   private int replyNo;
   private int memberNo;
   private int replyBno;
   private int replyBoardNo;
   private String replyWriter;
   private String replyContent;
   private Date replyCreateDate;
   private String replyModifyDate;
   private int replyReport;
   private int replyStaus;
   private String memberProfileImg;
   private int replyGroup;


}

	
	
	
	

