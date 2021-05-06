package org.zerock.sample;



import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.config.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
//@Runwith 테스트시 필요한 클래스 지정
@RunWith(SpringJUnit4ClassRunner.class)		// 현재 테스트코드가 스프링을 실행하는 역할을 할것이 이라는 것을  @RunWith  어노테이션으로 표시
/*
 * @ContextConfiguration : 테스트관련 어노테이션 
 * 스프링이 실행되면서 어떤설정 정보를 읽어 들여야하는지 명시
 * 속성: locations를 이용 문자열의배열로 xml설정파일 명시 또는
 * class 속성: @Configuration 이 적용된 클래스를 지정해 줄수도 있다.
 * 
 * */
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class SampleTest {
	
	@Setter(onMethod_ = { @Autowired } )	//@Autowired  해당 인스턴스 변수가 스프링으로 부터 자동으로 주입해 달라는 표시
	//onMethod setter메섣의 생성 시 메서드에 추가할 어노테이션 지정
	private Restaurant restaurant;
	
	@Test			// junit에서 테스트 대상을 표시하는 어노테이션
	public void testExist() {
		
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("-----------------------------------");
		log.info(restaurant.getChef());
	}

}
