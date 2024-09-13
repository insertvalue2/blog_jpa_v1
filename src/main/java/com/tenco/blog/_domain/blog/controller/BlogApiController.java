package com.tenco.blog._domain.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tenco.blog._domain.blog.dto.ArticleDTO;
import com.tenco.blog._domain.blog.entity.Article;
import com.tenco.blog._domain.blog.service.BlogService;
import com.tenco.blog.common.ApiUtil;
import com.tenco.blog.common.errors.Exception404;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j // Lombok에서 제공하는 애노테이션으로, 로깅을 쉽게 사용할 수 있도록 도와 준다.  
@RequiredArgsConstructor
@RestController // @Controller + @ResponseBoby
// HTTP 응답 메세지 바디에 데이터를 반환(MIME TYPE)하는 컨트롤러 
public class BlogApiController {
	
	// DI 처리 
	private final BlogService blogService;
	
	// URL 맵핑-> 주소설계 : http://localhost:8080/api/articles
	@PostMapping("/api/articles")
	// URL 경로에 있는 값을 변수로 추출할 때 사용 방식 
	// @RequestParam, @PathVariable, Map<String,String>, DTO 방식
	// @RequestBody - HTTP 요청 메세지 바디(본문)에서 데이터 추출 (DTO로 선언 시 @RequestBody 생략 가능) 
	public ResponseEntity<Article> addArticle(@RequestBody ArticleDTO dto) {
		// 1. 인증검사 
		// 2. 유효성 검사 
		Article savedArticle = blogService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
	}
	 
	@GetMapping("/api/articles")
	public ApiUtil<?> getAllArticles() {
		// 필요하다면 인증 검사 추가 
	    List<Article> articles = blogService.findAll();
	    
	    // 테스트 1 
//	    if (!articles.isEmpty()) {
//	        return new ApiUtil<>(204, "조회된 게시글이 없습니다."); // 204: No Content 상태 코드
//	    }
        // 테스트 2
//	    if (!articles.isEmpty()) {
//	        return new ApiUtil<>(new Exception404("게시글이 없습니다")); 
//	    }
        // 테스트 3
	    if (articles.isEmpty()) {
	         throw new Exception404("조회된 게시글이 없습니다");
	    }
	    
	    return new ApiUtil<>(articles);
	}
	
	// 코드 추가 2 
	// 주소 설계 : http://localhost:8080/api/articles/1
	// 리소스 식별을 명확하고 직관적으로 하기 위해서 상세보기 요청은 
	// 일반적으로 경로 변수(Path variable)로 설계 된다. RESTful API 지향 하기 위함
	@GetMapping("/api/articles/{id}")
	public ApiUtil<?> findArticle(@PathVariable(name = "id") Integer id) {
		// 1. 유효성 검사 생략 
		Article article = blogService.findById(id);
		log.info("새로운 게시글 생성 중...");
		// log.debug("게시글 조회: ID = {}", id); // 출력 안됨 
		// 애플리케이션의 클래스나 패키지에 대한 로그 레벨 설정이 없습니다.
		// 스프링 부트에서 사용하는 기본 로깅 프레임워크는 Logback 제한되어 있다. 
		// yml 파일 변경 
		log.error("게시글 조회 실패: ID = {}", id);
		return new ApiUtil<>(article);
	}
	
	/**
	 * 게시글 삭제 요청 
	 * @param id
	 * @return ApiUtil 
	 */
	@DeleteMapping("/api/articles/{id}")
	public ApiUtil<?> deleteArticle(@PathVariable(name = "id") Integer id) {
		// 1. 인증검사 생략 
		// 2. 유효성 검사 생략 
		blogService.delete(id);
		return new ApiUtil<>(200, "게시글이 성공적으로 삭제되었습니다.");
	}
	
}






