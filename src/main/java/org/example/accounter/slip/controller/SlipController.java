package org.example.accounter.slip.controller;

import lombok.RequiredArgsConstructor;
import org.example.accounter.slip.dto.SlipRequest;
import org.example.accounter.slip.service.SlipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/slip")
@RequiredArgsConstructor
public class SlipController {

    private final SlipService service;

    @PostMapping
    public ResponseEntity<String> write(@RequestBody SlipRequest request) {
        service.write(request);
        return ResponseEntity.ok("전표가 작성되었습니다.");
    }

}
