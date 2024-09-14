package com.mymosaic.hall.common;

import com.mymosaic.hall.web.VideoWorkAddForm;
import com.mymosaic.hall.web.WorkAddForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkAddFormConverter implements Converter<WorkAddForm, WorkAddForm> {

    @Override
    public WorkAddForm convert(WorkAddForm source) {
        log.info("workAddForm converter start");
        return new VideoWorkAddForm();
    }
}
