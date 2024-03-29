package com.tez.todoservice.service;

import com.tez.todoservice.model.Priority;
import com.tez.todoservice.model.dto.PriorityDto;
import com.tez.todoservice.model.requests.CreatePriorityRequest;
import com.tez.todoservice.repository.PriorityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public PriorityDto createPriority(CreatePriorityRequest request) {
        Priority priority = new Priority();
        priority.setPriorityName(request.getPriorityName());
        priority.setUserId(request.getUserId());
        priority.setPriorityStatus(Boolean.FALSE);
        Priority savedPriority = priorityRepository.save(priority);
        return mapToDto(savedPriority);
    }

    private PriorityDto mapToDto(Priority savedPriority) {
        PriorityDto priorityDto = new PriorityDto();
        priorityDto.setId(savedPriority.getId());
        priorityDto.setPriorityName(savedPriority.getPriorityName());
        priorityDto.setPriorityStatus(savedPriority.getPriorityStatus());
        priorityDto.setUserId(savedPriority.getUserId());
        return priorityDto;
    }

    public PriorityDto getPriority(Long id) {
        Priority priority = priorityRepository.findById(id).orElse(null);
        return mapToDto(priority);
    }

    public PriorityDto updatePriority(Long id) {
        Priority priority = priorityRepository.findById(id).orElse(null);
        priority.setPriorityStatus(!priority.getPriorityStatus());
        Priority updatedPriority = priorityRepository.save(priority);
        return mapToDto(updatedPriority);
    }

    public PriorityDto deletePriority(Long id) {
        Priority priority = priorityRepository.findById(id).orElse(null);
        priorityRepository.delete(priority);
        return mapToDto(priority);
    }

    public List<PriorityDto> getAllByUserId(Long userId) {
        List<Priority> priorities = priorityRepository.findAllByUserId(userId);
        List<PriorityDto> dtoList = priorities.stream().map(this::mapToDto).collect(Collectors.toList());
        return dtoList;
    }
}
