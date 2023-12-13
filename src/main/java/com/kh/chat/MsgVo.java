package com.kh.chat;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class MsgVo {
	private String message;
	private Date time;
	private int myNo;
	private int youNo;
	private String memberProfileImg;
}
