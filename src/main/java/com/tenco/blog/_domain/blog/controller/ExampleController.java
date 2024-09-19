package com.tenco.blog._domain.blog.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenco.blog._domain.blog.dto.Person;


@Controller
@RequestMapping("/thymeleaf") // 대문 만들기 
public class ExampleController {
	
	/**
	 * 정적 자원은 컨트롤러를 거치지 않고 바로 클라이언트에 제공됩니다.
	 * 예를 들어, 정적 자원이 src/main/resources/static 디렉토리 아래에 위치하면,
	 * 해당 파일은 URL을 통해 직접 접근할 수 있다.
	 *
	 *   src/main/resources/static/a.html 파일이 존재하는 경우
	 *   브라우저에서 http://localhost:8080/a.html 로 바로 접근 가능.
	 * 
	 * 주의: 정적 자원은 기본적으로 src/main/resources/static 디렉토리 아래에 있어야 한다.
	 * 파일 확장자와 이름을 정확히 확인.
	 */
	
	
	/**
	 * 주소 설계 - http://localhost:8080/thymeleaf/example
	 * @param model
	 * @return html 
	 */
	// 반드시 주의!!!!
	// 뷰 파일 이름과 URL 경로를 다르게 설정해야 함 - 무한 루프 발생 
	@GetMapping("/example")
	public String example1(Model model) {
		//타임리프 - 뷰 리졸브 동작 (src/main/resources/templates 폴더 아래 HTML 파일 존재해야 함) 
		// HTML 5로 작성되어야 함
		Person person = new Person(); 
		person.setId(100);
		person.setName("홍길동");
		person.setAge(22);
		person.setHobbies(List.of("운동", "코딩", "노래"));
		
		model.addAttribute("person", person);
		model.addAttribute("today", LocalDate.now());
		
		return "person"; // person.html 파일 조회 
	}
	
}
