package com.mymosaic.hall.web;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class WorkAddForm {

    private Integer categoryId;
    private Boolean visibility;
    private String name;
    private String content;
    private Float rating;

    private Integer subCategoryId;
    private List<Integer> genreIds;
    private String production;
    private List<String> performers;
    private Integer year;
    private List<MultipartFile> workImageFiles;
}
