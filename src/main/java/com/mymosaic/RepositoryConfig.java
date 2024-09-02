package com.mymosaic;

import com.mymosaic.diary.repository.DiaryRepository;
import com.mymosaic.diary.repository.MemoryDiaryRepository;
import com.mymosaic.hall.repository.MemoryWorkRepository;
import com.mymosaic.hall.repository.WorkRepository;
import com.mymosaic.member.repository.MemberRepository;
import com.mymosaic.member.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public DiaryRepository diaryRepository(){
        return new MemoryDiaryRepository();
    }

    @Bean
    public WorkRepository workRepository(){
        return new MemoryWorkRepository();
    }
}
