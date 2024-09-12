package com.tenco.blog._domain.post.service;

import org.springframework.stereotype.Service;

import com.tenco.blog._domain.post.dto.ArticleDTO;
import com.tenco.blog._domain.post.entity.Article;
import com.tenco.blog._domain.post.repository.PostRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final,(DI 처리)  
@Service // IoC(빈으로 등록)  
public class PostService {
	
	// final - 성능 개선을 위해 사용  
	private final PostRepository postRepository;
	
	// 블로그에 글을 추가하는 메서드 
	// @Transactional // 쓰기 지연(Write-behind)
	public Article save(ArticleDTO dto) {
		// 비즈니스 로직이 필요하다면 작성 ..  
		return postRepository.save(dto.toEntity());
	}
}
