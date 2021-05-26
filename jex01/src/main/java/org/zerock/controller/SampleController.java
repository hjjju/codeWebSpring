package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")  //현재 클래스의 모든 메소드들의 기본적URL경로, 클래스선언 , 매소드 선언에 사용가능
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void basic() {
		log.info("basic...................");
	}
	
	
	@RequestMapping(value="/basic", method= {RequestMethod.GET,RequestMethod.POST})
	public void basicGet() {
		log.info("basic get.......");
	}
	
	@GetMapping("/basicOnlyGet")  //RequestMapping의 축약형
	public void basicGet2() {
		log.info("basic get only get............");
	}
	
	
	
	//@RequestMapping은 GET,POST 방식 모두를 지원해야하는 경우에 배열로 처리해서 지정가능
	
	
	//Controller가 파라미터를 수집하는 방식은 파라미터 타입에 따라 자동으로 변환하는 방식을 이용.
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(""  + dto);
		
		return "ex01";
	}
	
	
	
	/*
	 * @RequsetParam은 파라미터로 사용된 변수의 이름과 전달되는 파라미터의이름이 이름이 다른 경우에 사용됨.
	 * */
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name:" + name);
		log.info("age: " + age);
		return "ex02";
	}
	
	
	//리스트,배열처리
	
	// 동일한 이름의 파라미터가 여러개 전달 되더라도 ArrayList<String> 이 생성되어 자동으로 수집됨.
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids: " + ids);    // ids: [111, 222, 333] 결과
		return "ex02List";
	}
	
	
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		
		log.info("array ids: " + Arrays.toString(ids)); 
		log.info("ids.toString: " + ids.toString()); 
		
		double[] values = {1.0, 1.1, 1.2};

		System.out.println("values.toString()" + values.toString()); // 이렇게 하면 [D@46a49e6 같은 값이 나옵니다.

		System.out.println("(Arrays.toString(values)" + Arrays.toString(values)); // 이렇게 하면 [1.0, 1.1, 1.2] 이 출력됩니다.
		
		return "ex02Array";
		
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		
		//[는 %5B로 ]는 %5D로 
		log.info("list dtos: " + list); 
		
		return "ex02Bean";
	}
	
	//데이터 변환 1
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//		
//	}
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: " + todo);
		return "ex03";
	}
	
	
	/*
	 * @ModelAttribute어노테이션
	 * 웹페이지의 구조: Request에 전달된 데이터를 가지고 추가적인 데이터를 생성해서 화면으로 전달하는 방식.
	 * Model의 경우 파라미터로 전달된 데이터는 존재하지않지만 화면에서 필요한 데이터를 전달하기 위해 사용.
	 * Ex) 페이지 번호는 파라미터로 전달되지만, 결과데이터를 전달하려면 Model에 담아서 전달.
	 * */
	
	
	//기본자료형의경우 파라미터로 선언하더라도 기본적으로 화면까지 전달되지는 않는다.
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		
		log.info("dto: " + dto);
		log.info("page: " + page); 		// 값은 넘어오나 뷰로 전달되지는 않음.
		
		
		
	//model Attribute는 강제로 전달받은 파라미터를 model에 담아 전달하도록 할때 사용.
	//@ModelAttribute : 타입에 관계없이 무조건 Model에 담에서전달-> 파리미터로 전달된 데이터를 다시 화면에서 사용해야 할 경우에 유용.
		
		return "/sample/ex04";
	}
	
	// RedirectAttributes 
	// 일회성 데이터 전달
	
	
	//string 타입
	// 1. redirect : 웹컨테이너 차원에서 페이지 이동 -> 글쓰기 o
	// 2. forward : 주소를 바꾸고 다른페이지로 이동 -> 글쓰기 x, 검색, 조회 o

	
	
	//6.5.3 객체 타입
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06.........");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
		
	}
	
	
	
	//ResponseEntity 타입
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07......");
//		"name": "홍길동"
		String msg = "{\"name\": \"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/JSON;charset=UTF-8");
		
		return new ResponseEntity<>(msg,header,HttpStatus.OK);
		
	}
	
	
	//파일 업로드
	//commons - fileupload이용
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload.................");
	}
	
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		files.forEach(file->{
			log.info("----------------------------");
			log.info("name: " + file.getOriginalFilename());
			log.info("size: " + file.getSize()); 
			
		});
	}
	
}
