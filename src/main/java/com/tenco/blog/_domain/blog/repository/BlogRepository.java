package com.tenco.blog._domain.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenco.blog._domain.blog.entity.Article;

// @Repository 생략 가능 -> JpaRepository 선언하고 있음 
public interface BlogRepository extends JpaRepository<Article, Integer> {
    // 기본적인 CRUD 기능을 자동으로 제공
    // 추가적인 쿼리 메서드도 정의 가능
} 	
