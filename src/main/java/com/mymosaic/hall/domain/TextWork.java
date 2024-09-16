package com.mymosaic.hall.domain;

import com.mymosaic.common.file.UploadFile;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public class TextWork extends Work{

    private Integer subCategoryId;
    private List<Integer> genreIds;
    private List<UploadFile> workImages;
    private String publisher;
    private List<String> authors;
    private Integer year;

    public void updateTextWorkInfo(Boolean visibility, String name, String content, Float rating,
                                    Integer subCategoryId, List<Integer> genreIds, List<UploadFile> workImages, String publisher,
                                    List<String> authors, Integer year){
        super.updateWorkInfo(visibility, name, content, rating);
        this.subCategoryId = subCategoryId;
        this.genreIds = genreIds;
        this.workImages = workImages;
        this.publisher = publisher;
        this.authors = authors;
        this.year = year;
    }
}
