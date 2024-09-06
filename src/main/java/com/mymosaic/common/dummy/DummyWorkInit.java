package com.mymosaic.common.dummy;

import com.mymosaic.hall.constant.WorkCategoryConst;
import com.mymosaic.hall.constant.WorkGenreConst;
import com.mymosaic.hall.constant.WorkSubcategoryConst;
import com.mymosaic.hall.domain.VideoWork;
import com.mymosaic.hall.domain.Work;
import com.mymosaic.hall.repository.WorkRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DummyWorkInit {

    private final WorkRepository workRepository;

    @PostConstruct
    public void init(){
        List<Work> works = createWorks();
        for (Work work : works) {
            workRepository.save(work);
        }
    }

    List<Work> createWorks(){
        List<Work> list = new ArrayList<>();
        list.add(VideoWork.builder()
                .genreIds(List.of(WorkGenreConst.ACTION, WorkGenreConst.COMEDY))
                .subCategory(WorkSubcategoryConst.MOVIE)
                .categoryId(WorkCategoryConst.VIDEO)
                .content("작품 내용1")
                .memberId(1L)
                .performers(List.of("배우1", "배우2"))
                .production("AAA 제작사")
                .rating(4.5f)
                .name("작품 이름1")
                .visibility(true)
                .year(2024)
                .build());
        list.add(VideoWork.builder()
                .genreIds(List.of(WorkGenreConst.ACTION))
                .subCategory(WorkSubcategoryConst.MOVIE)
                .categoryId(WorkCategoryConst.VIDEO)
                .content("작품 내용2")
                .memberId(1L)
                .performers(List.of("배우1", "배우2"))
                .production("BBB 제작사")
                .rating(3.5f)
                .name("작품 이름2")
                .visibility(true)
                .year(2024)
                .build());
        list.add(VideoWork.builder()
                .genreIds(List.of(WorkGenreConst.SF, WorkGenreConst.FANTASY))
                .subCategory(WorkSubcategoryConst.MOVIE)
                .categoryId(WorkCategoryConst.VIDEO)
                .content("작품 내용3")
                .memberId(2L)
                .performers(List.of("배우2", "배우3"))
                .production("BBB 제작사")
                .rating(2.5f)
                .name("작품 이름3")
                .visibility(true)
                .year(2024)
                .build());

        return list;
    }
}
