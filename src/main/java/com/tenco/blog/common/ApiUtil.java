package com.tenco.blog.common;

import lombok.Data;

@Data  
//제네릭 클래스로, API 응답을 포장하는 유틸리티 클래스
public class ApiUtil<T> {  

    private Integer status;  // 응답 상태 코드를 저장 (예: 200, 400, 500 등)
    private String msg;  // 응답 메시지를 저장 (성공, 에러 메시지 등)
    private T body;  // 응답의 실제 데이터를 저장 (제네릭 타입으로 어떤 타입이든 가능)

    // 성공적인 응답을 반환할 때 사용하는 생성자
    public ApiUtil(T body) {
        this.status = 200;  // 기본 상태 코드를 200으로 설정 (성공)
        this.msg = "성공";  // 기본 메시지를 '성공'으로 설정
        this.body = body;  // 실제 데이터(body)를 저장
    }

    // 커스텀 상태 코드와 메시지를 사용할 때 사용하는 생성자 (예: 에러 응답)
    public ApiUtil(Integer status, String msg) {
        this.status = status;  // 사용자 정의 상태 코드 설정
        this.msg = msg;  // 사용자 정의 메시지 설정
        this.body = null;  // 에러 응답의 경우 body는 null로 설정
    }
}
