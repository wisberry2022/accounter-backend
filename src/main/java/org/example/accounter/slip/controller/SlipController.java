package org.example.accounter.slip.controller;

import lombok.RequiredArgsConstructor;
import org.example.accounter.core.dto.SimplePageDto;
import org.example.accounter.core.mapper.PageMapper;
import org.example.accounter.slip.controller.response.SlipListResponse;
import org.example.accounter.slip.dto.SlipRequest;
import org.example.accounter.slip.service.SlipService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/slip")
@RequiredArgsConstructor
public class SlipController {

    private final SlipService service;
    private final PageMapper pageMapper;

    @PostMapping
    public ResponseEntity<String> write(@RequestBody SlipRequest request) {
        service.write(request);
        return ResponseEntity.ok("전표가 작성되었습니다.");
    }

    @GetMapping("/list")
    public ResponseEntity<SimplePageDto<SlipListResponse>> getAll(@RequestParam int page, @RequestParam int size) {
        Page<SlipListResponse> list = service.getAllDto(page, size);
        return ResponseEntity.ok(pageMapper.toPageDto(list));
    }

}
