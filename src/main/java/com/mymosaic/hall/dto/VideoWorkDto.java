package com.mymosaic.hall.dto;

import com.mymosaic.common.file.UploadFile;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class VideoWorkDto extends WorkDto {

    private Integer subCategory;
    private List<Integer> genreIds;
    private List<UploadFile> workImages;
    private String production;
    private List<String> performers;
    private Integer year;
}
