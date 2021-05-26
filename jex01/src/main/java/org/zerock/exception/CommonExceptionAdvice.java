package org.zerock.exception;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j;

// 해당 객체가 스프링의 컨트롤러에서 발생하는 예외를 처리하는 존재임을 명시.
@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
// 해당 메서드 () 가 들어가는 예외타임을 처리한다.
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		log.error("Exception......" + ex.getMessage());
		model.addAttribute("exception", ex);
		log.error(model);
		return "error_page";
	}

}