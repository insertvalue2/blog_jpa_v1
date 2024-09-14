package com.tenco.blog._domain.blog.entity;

import com.tenco.blog.common.errors.Exception400;

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
	
	// 작업 1 
	// 도메인 모델 자체에 상태 변경 로직을 포함시켜 엔티티의 일관성과 무결성을 유지
	// 객체 상태값 수정하기 
	public void update(String title, String content) {
		// 입력 값 검증 로직 추가
        if (title == null || title.trim().isEmpty()) {
            throw new Exception400("제목은 null이거나 빈 문자열일 수 없습니다.");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new Exception400("내용은 null이거나 빈 문자열일 수 없습니다.");
        }
        this.title = title;
        this.content = content;
	}
	
}

