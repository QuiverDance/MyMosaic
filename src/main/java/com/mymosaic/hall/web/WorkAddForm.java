package com.mymosaic.hall.web;

import lombok.Data;

@Data
public abstract class WorkAddForm {

    private Integer categoryId;
    private Boolean visibility;
    private String name;
    private String content;
    private Float rating;
}
