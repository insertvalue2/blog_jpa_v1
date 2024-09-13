package com.tenco.blog._domain.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenco.blog._domain.blog.dto.ArticleDTO;
import com.tenco.blog._domain.blog.entity.Article;
import com.tenco.blog._domain.blog.service.BlogService;
import com.tenco.blog.common.ApiUtil;
import com.tenco.blog.common.errors.Exception404;

import lombok.RequiredArgsConstructor;



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
}






