package com.mymosaic.hall.web;

import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.dto.VideoWorkDto;
import com.mymosaic.hall.dto.VideoWorkEditParam;
import com.mymosaic.hall.dto.WorkDto;
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
    private List<MultipartFile> workImageFiles = new ArrayList<>();

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
    }

    public VideoWorkEditParam toVideoEditParam(){
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
                .workImageFiles(workImageFiles)
                .build();
    }
}
