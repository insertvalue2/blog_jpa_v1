package com.tenco.blog._domain.blog.dto;

import java.util.List;

import lombok.Data;

/**
 * 샘플 코드 
 */
@Data
public class Person {

	private Integer id; 
	private String name; 
	private int age; 
	private List<String> hobbies; 
}
