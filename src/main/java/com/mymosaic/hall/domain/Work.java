package com.mymosaic.hall.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Work {

    @Setter
    private Long id;

    private Long memberId;
    private Integer categoryId;
    private Boolean visibility;
    private String name;
    private String content;
    private Float rating;
    private final LocalDateTime createdTime = LocalDateTime.now();
    private LocalDateTime lastTime = LocalDateTime.now();
//
//    public Work(Long memberId, Integer categoryId, Boolean visibility, String name, String content, Float rating){
//        this.memberId = memberId;
//        this.categoryId = categoryId;
//        this.visibility = visibility;
//        this.name = name;
//        this.content = content;
//        this.rating = rating;
//    }
}
