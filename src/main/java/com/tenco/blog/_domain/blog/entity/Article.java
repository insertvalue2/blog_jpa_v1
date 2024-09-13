package com.tenco.blog._domain.blog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 엔티티 지정
@Data
@NoArgsConstructor
@Table(name = "article")  // 테이블 이름을 명시적으로 설정
public class Article {
	
	@Builder // 특정 생성자 빌더 패턴 생성 
 	public Article(String title, String content) {
		this.title = title; 
		this.content = content; 
	}
	
	@Id // 기본키 지정 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment 설정
	@Column(name = "id", updatable = false)
	private Long id; 
	
	@Column(name = "title", nullable = false) // not null 설정
	private String title; 
	
	@Column(name = "content", nullable = false)
	private String content;
	
}

