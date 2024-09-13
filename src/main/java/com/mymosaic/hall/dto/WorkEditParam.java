package com.mymosaic.hall.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class WorkEditParam {

    private Boolean visibility;
    private String name;
    private String content;
    private Float rating;
}
