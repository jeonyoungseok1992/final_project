package com.kh.fin.common.template;

import com.kh.fin.common.model.vo.PageInfo;

public class Pagenation {

	public static PageInfo getPageInfo(int listCount,int currentPage,int pageLimit,int boardLimit) {
		int maxPage; // 가장 마지막 페이지(총 페이지의 수)
		int startPage; // 페이징바의 시작수 
		int endPage; //페이징바의 끝수
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		
		startPage = ((currentPage - 1) / pageLimit) * pageLimit + 1;
		
		
		endPage = startPage + pageLimit -1;
		
		
		endPage = endPage > maxPage ? maxPage : endPage;
		
		PageInfo pi = new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
		return pi;
	}
}
