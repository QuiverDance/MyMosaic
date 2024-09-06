package com.mymosaic.hall.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
public abstract class WorkDto {

    private Long id;

    private Long memberId;
    private Integer categoryId;
    private Boolean visibility;
    private String name;
    private String content;
    private Float rating;
    private LocalDateTime createdTime;
    private LocalDateTime lastTime;
}
