package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component  //spring에게 해다클래스가 간리해야할 대상(Bean)임을 표시
@Data
public class Restaurant {

	@Setter(onMethod_ = @Autowired)	//@Setter: 자동으로 setChef()를 컴파일시 생성
	private Chef chef;
}
