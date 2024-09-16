package com.mymosaic.hall.web;

import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.constant.WorkSubcategoryConst;
import com.mymosaic.hall.dto.WorkSearchAndSortParam;
import lombok.Data;

import java.util.List;

@Data
public class WorkSearchAndSortForm {

    String keyword;
    Integer categoryId;
    String sortBy;
    String sortDir;

    String year;
    Integer subcategoryId;
    List<Integer> genreIds;
    public WorkSearchAndSortParam toParam(){
        return WorkSearchAndSortParam.builder()
                .keyword(keyword)
                .categoryId(categoryId != null && categoryId.equals(WorkCategoryConst.ALL) ? null : categoryId)
                .subcategoryId(subcategoryId != null && subcategoryId.equals(WorkSubcategoryConst.ALL) ? null : subcategoryId)
                .genreIds(genreIds)
                .year(year != null && !year.isEmpty() ? Integer.parseInt(year) : null)
                .sortBy(sortBy != null ? sortBy : "date")
                .sortDir(sortDir != null ? sortDir : "asc")
                .build();
    }
}
