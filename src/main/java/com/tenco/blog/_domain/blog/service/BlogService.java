package com.tenco.blog._domain.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tenco.blog._domain.blog.dto.ArticleDTO;
import com.tenco.blog._domain.blog.entity.Article;
import com.tenco.blog._domain.blog.repository.BlogRepository;
import com.tenco.blog.common.errors.Exception404;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final,(DI 처리)  
@Service // IoC(빈으로 등록)  
public class BlogService {
	
	// final - 성능 개선을 위해 사용  
	private final BlogRepository blogRepository;
	
	// 블로그에 글을 추가하는 메서드 
	// @Transactional // 쓰기 지연(Write-behind)
	public Article save(ArticleDTO dto) {
		// 비즈니스 로직이 필요하다면 작성 ..  
		return blogRepository.save(dto.toEntity());
	}
 
	// 서비스 클래스의 전체글 조회 메서드
	public List<Article> findAll() {
	    // 전체 게시글 조회
	    List<Article> articles = blogRepository.findAll();
	    // 조회된 게시글이 없는 경우 로그를 남기거나, 특정 동작을 수행할 수 있음
	    if (articles.isEmpty()) {
	        // 로그 추가 (필요에 따라)
	        System.out.println("조회된 글이 없습니다.");
	    }
	    // 조회된 게시글이 없더라도 빈 리스트를 반환
	    return articles; 
	}
	
	// 코드 추가 1 
	public Article findById(Integer id) {
		// Type mismatch: cannot convert from Optional<Article> to Article
		// Optional<T>는 Java 8에서 도입된 클래스이며, 
		// 값이 존재할 수도 있고 없을 수도 있는 상황을 명확하게 처리하기 위해 사용됩니다.
		// Optional 타입에 대해서 직접 조사하고 숙지 하세요! (문서 확인 및 검색 GPT 확인)  
		return blogRepository.findById(id).orElseThrow(() -> new Exception404("해당 게시글이 존재하지 않습니다"));
	}
	
	
}
