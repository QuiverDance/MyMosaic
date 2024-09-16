package com.mymosaic.hall.web;

import com.mymosaic.common.file.UploadFile;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class WorkAddForm {

    @NotEmpty
    private Integer categoryId;

    private Boolean visibility;

    @NotEmpty
    @Length(min = 1, max = 30)
    private String name;

    @Length(max = 100)
    private String content;

    @Range(min = 0, max = 5)
    private Float rating;

    private Integer subCategoryId;
    private List<Integer> genreIds = new ArrayList<>();
    private String production;
    private List<String> performers = new ArrayList<>();
    private Integer year;
    private List<MultipartFile> workImageFiles = new ArrayList<>();

    private List<UploadFile> workImages;
    private List<String> loadedImages;
    private String publisher;
    private List<String> authors;
}
