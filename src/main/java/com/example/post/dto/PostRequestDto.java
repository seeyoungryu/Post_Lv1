package com.example.post.dto;
import lombok.Getter;


@Getter
public class PostRequestDto {
    private String username;
    private String contents;
    private String password;
    private String title;

}


//    이 코드는 PostRequestDto라는 클래스를 정의하고,
//        이 클래스에 Lombok의 @Getter 어노테이션을 사용하여
//        getter 메서드를 자동으로 생성하고 있는 것으로 보입니다.
//        이 클래스는 게시물을 생성하거나 업데이트하기 위한
//        데이터 전송 객체(Data Transfer Object, DTO)로 사용될 수 있습니다.