package com.mymosaic.hall.dto;

import com.mymosaic.common.file.UploadFile;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class VideoWorkDto extends WorkDto {

    private Integer subCategoryId;
    private List<Integer> genreIds;
    private List<UploadFile> workImages;
    private List<String> loadedImages;
    private String production;
    private List<String> performers;
    private Integer year;
}
