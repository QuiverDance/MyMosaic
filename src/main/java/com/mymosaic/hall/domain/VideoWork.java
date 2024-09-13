package com.mymosaic.hall.domain;

import com.mymosaic.common.file.UploadFile;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
public class VideoWork extends Work {

    private Integer subCategoryId;
    private List<Integer> genreIds;
    private List<UploadFile> workImages = new ArrayList<>();
    private String production;
    private List<String> performers;
    private Integer year;

    public void updateVideoWorkInfo(Boolean visibility, String name, String content, Float rating,
                                    Integer subCategoryId, List<Integer> genreIds, List<UploadFile> workImages, String production,
                                    List<String> performers, Integer year){
        super.updateWorkInfo(visibility, name, content, rating);
        this.subCategoryId = subCategoryId;
        this.genreIds = genreIds;
        this.workImages = workImages;
        this.production = production;
        this.performers = performers;
        this.year = year;
    }
}
