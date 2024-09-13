package com.tenco.blog._domain.post.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenco.blog._domain.blog.dto.ArticleDTO;
import com.tenco.blog._domain.blog.entity.Article;
import com.tenco.blog._domain.blog.repository.BlogRepository;

// 강의 x 
// 애플리케이션의 모든 계층을 포함하는 통합 테스트를 수행할 때 유용
@SpringBootTest // 테스트용 애플리케이션 컨텍스트 구성
// 실제 애플리케이션처럼 동작할 수 있도록 준비하는 과정 
//(필요한 빈(bean), 설정, 그리고 다른 컴포넌트들을 모두 로드하고 관리)

@AutoConfigureMockMvc // MockMvc 생성 및 자동 구성
public class BlogApiControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	// 스프링 프레임워크에서 제공하는 직렬화, 역직렬화를 위한 클래스
	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private BlogRepository postRepository;

	// 테스트 실행 전 실행하는 메서드
	@BeforeEach
	public void mockMvcSetUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		postRepository.deleteAll();
	}

	@DisplayName("addARticle : 글쓰기 테스트 ")
	@Test
	public void addArticle() throws Exception {

		// given
		final String url = "/api/article";
		final String title = "글 제목 ";
		final String content = "글 내용 ";
		final ArticleDTO articleDTO = new ArticleDTO(title, content);

		// 객체을 JSON 형힉으로 직렬화
		final String requestBody = objectMapper.writeValueAsString(articleDTO);

		// when
		// 설정한 내용을 바탕으로 요청 전송
		ResultActions result = mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(requestBody));
		
		// then
		result.andExpect(MockMvcResultMatchers.status().isCreated());
		
		List<Article> articles = postRepository.findAll();
		
		assertThat(articles.size()).isEqualTo(1);
		assertThat(articles.get(0).getTitle()).isEqualTo(title);
		assertThat(articles.get(0).getContent()).isEqualTo(content);

	}

	public static void main(String[] args) throws JsonProcessingException {
		// ObjectMapper 란 
		ObjectMapper tempObjectMapper = new ObjectMapper();
		
		final String title = "글 제목 ";
		final String content = "글 내용 ";
		final ArticleDTO articleDTO = new ArticleDTO(title, content);

		// 객체을 JSON 형힉으로 직렬화
		final String requestBody = tempObjectMapper.writeValueAsString(articleDTO);
		System.out.println(requestBody);
	}

}
