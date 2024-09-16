package com.mymosaic.hall.dto;

import com.mymosaic.common.file.UploadFile;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class TextWorkEditParam extends WorkEditParam{

    private Integer subCategoryId;
    private List<Integer> genreIds;
    private List<UploadFile> workImageFiles;
    private String publisher;
    private List<String> authors;
    private Integer year;
}
