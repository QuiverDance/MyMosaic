package com.mymosaic.hall.dto;

import com.mymosaic.common.file.UploadFile;
import com.mymosaic.hall.domain.Work;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
public class VideoWorkDto extends Work {

    private Integer subCategory;
    private List<Integer> genreIds;
    private List<UploadFile> workImages;
    private String production;
    private List<String> performers;
    private Integer year;
}
