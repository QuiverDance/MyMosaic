package com.mymosaic.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiarySearchAndSortParam {

    String keyword;
    Integer year;
    Integer month;
    String sortBy;
    String sortDir;
}
