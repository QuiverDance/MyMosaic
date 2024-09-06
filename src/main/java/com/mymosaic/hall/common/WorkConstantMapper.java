package com.mymosaic.hall.common;

import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.constant.WorkGenreConst;
import com.mymosaic.hall.constant.WorkSubcategoryConst;

public class WorkConstantMapper {

    public static String getCategoryName(Integer categoryId) {
        if(categoryId.equals(WorkCategoryConst.VIDEO))
            return "Video Work";
        else if(categoryId.equals(WorkCategoryConst.TEXT))
            return "Text Work";
        else if(categoryId.equals(WorkCategoryConst.CHARACTER))
            return "Character Work";
        return "Null Category";
    }

    public static String getSubCategoryName(Integer subCategoryId){
        if(subCategoryId.equals(WorkSubcategoryConst.MOVIE))
            return "Movie";
        else if(subCategoryId.equals(WorkSubcategoryConst.DRAMA))
            return "Drama";
        else if(subCategoryId.equals(WorkSubcategoryConst.ANIMATION))
            return "Animation";
        return "Null Subcategory";
    }

    public static String getGenreName(Integer genreId){
        if(genreId.equals(WorkGenreConst.ACTION))
            return "Action";
        else if(genreId.equals(WorkGenreConst.FANTASY))
            return "Fantasy";
        else if(genreId.equals(WorkGenreConst.SF))
            return "SF";
        else if(genreId.equals(WorkGenreConst.COMEDY))
            return "Comedy";
        else if(genreId.equals(WorkGenreConst.HORROR))
            return "Horror";
        return "Null Genre";
    }
}
