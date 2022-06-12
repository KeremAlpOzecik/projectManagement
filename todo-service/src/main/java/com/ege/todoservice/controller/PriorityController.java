package com.ege.todoservice.controller;

import com.ege.todoservice.model.dto.PriorityDto;
import com.ege.todoservice.model.requests.CreatePriorityRequest;
import com.ege.todoservice.model.requests.UpdatePriorityRequest;
import com.ege.todoservice.service.PriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/todo/priority")
@RequiredArgsConstructor
public class PriorityController {

    private final PriorityService service;

    @PostMapping("/create")
    public ResponseEntity<PriorityDto> createPriority(@RequestBody CreatePriorityRequest request) {
        return ResponseEntity.ok(service.createPriority(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriorityDto> getPriority(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPriority(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<PriorityDto> updatePriority(@PathVariable Long id) {
        return ResponseEntity.ok(service.updatePriority(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PriorityDto> deletePriority(@PathVariable Long id) {
        return ResponseEntity.ok(service.deletePriority(id));
    }

    @GetMapping("/getAllByUserId/{userId}")
    public ResponseEntity<List<PriorityDto>> getAllByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getAllByUserId(userId));
    }
}
