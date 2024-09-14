package com.mymosaic.hall.common;

import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.web.WorkAddForm;
import lombok.extern.slf4j.Slf4j;

import java.beans.PropertyEditorSupport;

/**
 * Not used
 */
@Slf4j
public class WorkAddFormEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        log.info("text : {}", text);
        Integer categoryId = Integer.parseInt(text);

        log.info("WorkAddFormEditor start : {}", categoryId);
        WorkAddForm form;
        if (categoryId.equals(WorkCategoryConst.VIDEO)){
//            form = new VideoWorkAddForm();
//            form.setCategoryId(categoryId);
            form = null;
        }
        else{
            throw new IllegalArgumentException("Invalid category");
        }
        setValue(form);
    }
}
