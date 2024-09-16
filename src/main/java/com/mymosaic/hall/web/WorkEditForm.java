package com.mymosaic.hall.web;

import com.mymosaic.common.file.UploadFile;
import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.dto.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class WorkEditForm {

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

    private String publisher;
    private List<String> authors;

    private List<MultipartFile> workImageFiles;

    public WorkEditForm(WorkDto work, Integer categoryId){
        visibility = work.getVisibility();
        name = work.getName();
        content = work.getContent();
        rating = work.getRating();

        if(categoryId.equals(WorkCategoryConst.VIDEO)){
            VideoWorkDto videoWork = (VideoWorkDto)work;
            subCategoryId = videoWork.getSubCategoryId();
            genreIds = videoWork.getGenreIds();
            production = videoWork.getProduction();
            performers = videoWork.getPerformers();
            year = videoWork.getYear();
        }
        else if(categoryId.equals(WorkCategoryConst.TEXT)){
            TextWorkDto textWork = (TextWorkDto)work;
            subCategoryId = textWork.getSubCategoryId();
            genreIds = textWork.getGenreIds();
            publisher = textWork.getPublisher();
            authors = textWork.getAuthors();
            year = textWork.getYear();
        }
        else if(categoryId.equals(WorkCategoryConst.CHARACTER)){

        }
    }

    public VideoWorkEditParam toVideoEditParam(List<UploadFile> uploadFiles){
        return VideoWorkEditParam.builder()
                .visibility(visibility)
                .name(name)
                .content(content)
                .rating(rating)
                .subCategoryId(subCategoryId)
                .genreIds(genreIds)
                .production(production)
                .performers(performers)
                .year(year)
                .workImageFiles(uploadFiles)
                .build();
    }

    public TextWorkEditParam toTextEditParam(List<UploadFile> uploadFiles){
        return TextWorkEditParam.builder()
                .visibility(visibility)
                .name(name)
                .content(content)
                .rating(rating)
                .subCategoryId(subCategoryId)
                .genreIds(genreIds)
                .publisher(publisher)
                .authors(authors)
                .year(year)
                .workImageFiles(uploadFiles)
                .build();
    }
}
