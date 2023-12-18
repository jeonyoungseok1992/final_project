package com.kh.fin.member.model.vo;

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
public class Member {
	
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String memberNickName;
	private String memberEmail;
	private String memberPhone;
	private String memberProfileImg;
	private Date createDate;
	private Date modifyDate;
	private String memberRight;
	private String memberStatus;
	private String memberGrade;
	private String boardNo;
	private String fromMemberNo;
	private String toMemberNo;
	
}
