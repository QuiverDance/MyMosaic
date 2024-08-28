package com.mymosaic.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiarySearchAndSortParam {

    String keyword;
    Integer year;
    Integer month;
    String sortBy = "date";
    String sortDir = "asc";
}
