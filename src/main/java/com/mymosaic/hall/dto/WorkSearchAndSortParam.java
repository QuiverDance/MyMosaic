package com.mymosaic.hall.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class WorkSearchAndSortParam {

    String keyword;
    Integer categoryId;
    String sortBy;
    String sortDir;

    Integer year;
    Integer subcategoryId;
    List<Integer> genreIds;
}
