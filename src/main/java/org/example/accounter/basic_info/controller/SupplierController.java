package org.example.accounter.basic_info.controller;

import lombok.RequiredArgsConstructor;
import org.example.accounter.basic_info.dto.SupplierAddRequest;
import org.example.accounter.basic_info.dto.SupplierResponse;
import org.example.accounter.basic_info.dto.SupplierUpdateRequest;
import org.example.accounter.basic_info.service.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/basic-info/sply")
public class SupplierController {

    private final SupplierService service;

    @GetMapping
    public ResponseEntity<List<SupplierResponse>> getAll() {
        List<SupplierResponse> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody SupplierAddRequest request) {
        service.add(request);
        return ResponseEntity.ok("등록되었습니다!");
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody SupplierUpdateRequest request) {
        service.update(request.getId(), request);
        return ResponseEntity.ok("수정되었습니다!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok("삭제되었습니다!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponse> get(@PathVariable("id") Long id) {
        SupplierResponse resp = service.getDto(id);
        return ResponseEntity.ok(resp);
    }
}
