package com.kh.fin.board.model.vo;

import java.util.List;

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
public class ScheduleDTO {
	private String startDate;
    private String endDate;
    private List<attractionDTO> placeInfo;
    private List<hotelDTO> lodgingInfo;
    private String transportation;
    private String regionName;
    private String regionX;
    private String regionY;
    private int regionNo;
    private int memberNo;
    private int tripPlannerNo;
    private int transportationNo;
    private int tripPlanNo;

   
   
}
