package com.tenco.blog._domain.blog.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.tenco.blog._domain.blog.dto.ArticleDTO;
import com.tenco.blog._domain.blog.entity.Article;
import com.tenco.blog._domain.blog.repository.BlogRepository;
import com.tenco.blog.common.errors.Exception404;

import jakarta.transaction.Transactional;
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
	
	// SQL 에서 삭제 쿼리는 오류가 없다  0 row(s) affected 결과 수행 
	// "Spring Data JPA의 deleteById() 메서드도 이와 유사한 방식으로 동작합니다." 
	// 라고 보통 알려져 있지만 존재 하지 않는 엔티티(객체<-> row)삭제를 시도할 경우 
	// 예외를 발생시킬 수 있다. 
	public void delete(Integer id) {
		// deleteById 리턴 타입이 void 이다.
		// 직접 예외 처리 진행 
		try {
	        blogRepository.deleteById(id);
	    } catch (EmptyResultDataAccessException e) {
	        // 존재하지 않는 ID일 때 예외 처리
	        throw new Exception404("해당 ID의 게시글이 존재하지 않습니다.");
	    }
		
//      자주 사용 되는 방식 예시 코드 
//		if (blogRepository.existsById(id)) {
//	        blogRepository.deleteById(id);
//	    } else {
//	        throw new Exception404("해당 ID의 게시글이 존재하지 않습니다.");
//	    }
		
	}
	
	// 수정 비즈니스 로직에 대한 생각! 
	// 영속성 컨텍스트에 존재하면 해당 엔티티를 사용하고,
	// 그렇지 않으면 DB에서 조회하여 가져옵니다
	// 그리고 상태 값을 수정하고 그 결과를 호출한 곳으로 반환 한다.
	@Transactional
	public Article  update(Integer id, ArticleDTO dto) {
		
		Article articleEntity = blogRepository.findById(id)
				.orElseThrow(() -> new Exception404("not found : " + id));
		
		// 객체 상태 값 변경  
		articleEntity.update(dto.getTitle(), dto.getContent());
		
		// 리포지토리의 save() 메서드는 수정할 때도 사용 가능 하다.
		// 단, 호출하지 않는 이유는 더티 체킹(Dirty Checking) 동작 때문이다.
		// 즉, 트랜잭션 커밋 시 자동으로 영속성 컨텍스트와 데이터베이스(DB)에 변경 사항이 반영된다
		// blogRepository.save(articleEntity); 

		return articleEntity;
	}
	
}
