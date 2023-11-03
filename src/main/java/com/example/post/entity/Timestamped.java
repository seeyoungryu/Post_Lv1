package com.example.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter // Getter 메서드 자동 생성을 위한 Lombok 어노테이션
@MappedSuperclass // 공통 필드를 포함한 상위 클래스를 나타내는 어노테이션
@EntityListeners(AuditingEntityListener.class) // 엔티티 이벤트 리스너를 사용하여 생성일 및 수정일 자동 처리

public abstract class Timestamped {

    @CreatedDate // 생성일 시간을 자동으로 설정하는 Spring Data JPA 어노테이션
    @Column(updatable = false) // 업데이트되지 않도록 설정 (생성 시간은 수정되지 않음)
    private LocalDateTime createdAt; // 엔티티가 생성된 날짜 및 시간

    @LastModifiedDate // 수정일 시간을 자동으로 설정하는 Spring Data JPA 어노테이션
    @Column // 수정 시간 열을 나타내는 어노테이션
    private LocalDateTime modifiedAt; // 엔티티가 마지막으로 수정된 날짜 및 시간
}


//Timestamped 클래스는 다른 엔티티 클래스에서 생성일 및 수정일을 관리하는 데 사용될 수 있는 공통 필드를 정의합니다.
// 이를 통해 생성 및 수정 일자를 자동으로 추적할 수 있습니다.