package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
	/*
	 * 파라미터의수집 == binding(바인딩)
	 * 문자열로 전달된 데이터를 java.util.Data타입으로 변환할때 자동으로 호출되는 @InitBinder를 이용해 처리.
	 * */
	
	private String title;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date dueDate;
}
