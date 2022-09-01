package com.triplus.schedule.service;

import com.triplus.schedule.dto.CompanionDto;
import com.triplus.schedule.mapper.CompanionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanionService {
    @Autowired
    CompanionMapper companionMapper;

    public int insert(CompanionDto companionDto) {
        return companionMapper.insert(companionDto);
    }

    public CompanionDto select(CompanionDto companionDto) {
        return companionMapper.select(companionDto);
    }

}
