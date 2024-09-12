package com.tenco.blog._domain.post.dto;

import com.tenco.blog._domain.post.entity.Article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 기본 생성자 
@AllArgsConstructor // 모든 필드 값을 받는 생성자 
@Getter
// 주로 계층간에 데이터 전송 목적으로 설계  
public class ArticleDTO {
	
	private String title; 
	private String content;

	// DTO에 로직을 넣는 경우는
	// 보통 데이터 검증이나 포맷팅 정도로
	// 제한하는 것이 권장
	public Article toEntity() {
		return Article.builder()
				.title(this.title)
				.content(this.content)
				.build();
	}

}
