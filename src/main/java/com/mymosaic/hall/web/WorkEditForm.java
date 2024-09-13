package com.mymosaic.hall.web;

import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.dto.VideoWorkDto;
import com.mymosaic.hall.dto.VideoWorkEditParam;
import com.mymosaic.hall.dto.WorkDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class WorkEditForm {

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
