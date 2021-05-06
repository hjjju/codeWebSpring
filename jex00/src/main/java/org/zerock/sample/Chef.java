package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Data;

/*
 * @Component : 해당클래스가 스프링에서 객체로만들어서관리하는대상임음명시
 * @Component가 있는 클래스를 스프링이 읽어 주도록 @ComponentScan을 통해서 지정되어있으므로 해당 패키지에있는 클래스들을 조사하면서
 *  @Component가 존재하는 클래스들을 객체로 생성해서 빈으로 관리.
 * */


@Component    
@Data
public class Chef {
	//restaurant클래스는 chef를 주입받아야함
	//@Data 어노테이션은 lombok의 setter, 생성자, toString()을 자동으로 생성해준다.
	
}
