package com.mymosaic.diary.web;

import com.mymosaic.diary.dto.DiarySearchAndSortParam;
import lombok.Data;

@Data
public class SearchAndSortForm {

    String keyword;
    String year;
    String month;
    String sortBy;
    String sortDir;

    public DiarySearchAndSortParam toParam(){
        Integer y = year != null && !year.isEmpty() ? Integer.parseInt(year) : null;
        Integer m = month != null && !month.isEmpty() ? Integer.parseInt(month) : null;

        return new DiarySearchAndSortParam(
                keyword, y, m,
                sortBy != null ? sortBy : "date",
                sortDir != null ? sortDir : "asc"
        );
    }
}
