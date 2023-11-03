package com.example.post.dto;

import com.example.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private final Long id;
    private final String title;
    private final String username;
    private final String contents;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

}

//이 코드는 `PostResponseDto`라는 클래스를 정의하고,
//이 클래스에 Lombok의 `@Getter` 어노테이션을 사용하여 getter 메서드를 자동으로 생성하고 있습니다.
// 이 클래스는 게시물의 정보를 클라이언트에 반환하기 위한 데이터 전송 객체(Data Transfer Object, DTO)로 사용될 수 있습니다.
//
// `PostResponseDto` 클래스의 멤버 변수들은
// 게시물의 식별자(`id`), 제목('title') 작성자 이름(`username`), 내용(`contents`), 생성일(`createdAt`), 그리고 수정일(`modifiedAt`)을 나타냅니다.
// 이 멤버 변수들은 `final`로 선언되어 변경할 수 없으며, 생성자를 통해 초기화됩니다.
//
// 생성자(`PostResponseDto(Post post)`)는 `Post` 엔터티를 입력으로 받아서 해당 엔터티의 정보를 이 DTO에 복사합니다.
// 이렇게 복사된 정보는 클라이언트에 반환될 때 사용될 수 있습니다.
//
// `@Getter` 어노테이션을 사용하면 이 DTO의 멤버 변수에 대한 getter 메서드가 자동으로 생성되므로,
// 클라이언트에서 이 DTO를 사용할 때 이러한 필드에 접근할 때 getter 메서드를 직접 작성하지 않아도 됩니다.