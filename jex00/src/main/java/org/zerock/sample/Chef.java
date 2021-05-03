package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Chef {
	//restaurant클래스는 chef를 주입받아야함
	//@Data 어노테이션은 lombok의 setter, 생성자, toString()을 자동으로 생성해준다.
	
}
