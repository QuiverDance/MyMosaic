package com.mymosaic.hall.web;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class VideoWorkAddForm extends WorkAddForm {

    private Integer subCategory;
    private List<Integer> genreIds;
    private String production;
    private List<String> performers;
    private Integer year;
    private List<MultipartFile> workImageFiles;
}
