package com.mymosaic.hall.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@SuperBuilder
public class VideoWorkEditParam extends WorkEditParam{

    private Integer subCategoryId;
    private List<Integer> genreIds;
    private String production;
    private List<String> performers;
    private Integer year;
    private List<MultipartFile> workImageFiles;
}
