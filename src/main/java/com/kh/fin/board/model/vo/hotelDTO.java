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
public class hotelDTO {
	
    private String src;
    private String title;
    private String category;
    private String date;
    private String mapX;
    private String mapY;
    private int regionNo;
    private int attractionCategoryNo;
    private int tripNday;
    private int maxSize;
}
