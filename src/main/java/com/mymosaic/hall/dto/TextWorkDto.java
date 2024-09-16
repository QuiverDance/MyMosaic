package com.mymosaic.hall.dto;

import com.mymosaic.common.file.UploadFile;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class TextWorkDto extends WorkDto {

    private Integer subCategoryId;
    private List<Integer> genreIds;
    private List<UploadFile> workImages;
    private List<String> loadedImages;
    private String publisher;
    private List<String> authors;
    private Integer year;
}
