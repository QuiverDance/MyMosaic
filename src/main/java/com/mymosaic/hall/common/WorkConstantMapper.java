package com.mymosaic.hall.common;

import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.constant.WorkGenreConst;
import com.mymosaic.hall.constant.WorkSubcategoryConst;

public class WorkConstantMapper {

    public static String getCategoryName(Integer categoryId) {
        return switch (categoryId){
            case WorkCategoryConst.VIDEO -> "영상 작품";
            case WorkCategoryConst.TEXT -> "텍스트 작품";
            case WorkCategoryConst.CHARACTER -> "인물";
            default ->  "Null Category";
        };
    }

    public static String getSubCategoryName(Integer subCategoryId){
        return switch (subCategoryId){
            case WorkSubcategoryConst.MOVIE -> "영화";
            case WorkSubcategoryConst.DRAMA -> "드라마";
            case WorkSubcategoryConst.ANIMATION -> "애니메이션";
            case WorkSubcategoryConst.NOVEL -> "소설";
            case WorkSubcategoryConst.POETRY -> "시";
            case WorkSubcategoryConst.ESSAY -> "수필";
            default -> "Null Subcategory";
        };
    }

    public static String getGenreName(Integer genreId){
        return switch (genreId){
            case WorkGenreConst.ACTION -> "액션";
            case WorkGenreConst.FANTASY -> "판타지";
            case WorkGenreConst.SF -> "SF";
            case WorkGenreConst.COMEDY -> "코메디";
            case WorkGenreConst.HORROR -> "공포";
            default -> "Null Genre";
        };
    }
}
