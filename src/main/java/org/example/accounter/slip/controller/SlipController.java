package org.example.accounter.slip.controller;

import lombok.RequiredArgsConstructor;
import org.example.accounter.slip.controller.response.SlipListResponse;
import org.example.accounter.slip.dto.SlipRequest;
import org.example.accounter.slip.service.SlipService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/slip")
@RequiredArgsConstructor
public class SlipController {

    private final SlipService service;

    @GetMapping
    public ResponseEntity<List<SlipListResponse>> getAll(@RequestParam int page, @RequestParam int size) {
        Page<SlipListResponse> list = service.getAllDto(page, size);
        return ResponseEntity.ok(list.getContent());
    }

    @PostMapping
    public ResponseEntity<String> write(@RequestBody SlipRequest request) {
        service.write(request);
        return ResponseEntity.ok("전표가 작성되었습니다.");
    }

}
