package com.triplus.schedule.service;

import com.triplus.schedule.dto.CompanionDto;
import com.triplus.schedule.mapper.CompanionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanionService {
    @Autowired
    CompanionMapper companionMapper;

    public int addCompanion(CompanionDto dto) {
        return companionMapper.addCompanion(dto);
    }

    public CompanionDto find(String id) {
        return companionMapper.find(id);
    }
}
