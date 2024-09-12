package com.tenco.blog._domain.post.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tenco.blog._domain.post.dto.ArticleDTO;
import com.tenco.blog._domain.post.entity.Article;
import com.tenco.blog._domain.post.service.PostService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController // @Controller + @ResponseBoby
// HTTP 응답 메세지 바디에 데이터를 반환(MIME TYPE)하는 컨트롤러 
public class PostApiController {
	
	// DI 처리 
	private final PostService postService;
	
	// URL 맵핑-> 주소설계 : http://localhost:8080/api/articles
	@PostMapping("/api/article")
	// URL 경로에 있는 값을 변수로 추출할 때 사용 방식 
	// @RequestParam, @PathVariable, Map<String,String>, DTO 방식
	// @RequestBody - HTTP 요청 메세지 바디(본문)에서 데이터 추출 (DTO로 선언 시 @RequestBody 생략 가능) 
	public ResponseEntity<Article> addArticle(@RequestBody ArticleDTO dto) {
		// 1. 인증검사 
		// 2. 유효성 검사 
		Article savedArticle = postService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
	}
}






