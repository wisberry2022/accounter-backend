package org.example.accounter.basic_info.account_subject.controller;

import lombok.RequiredArgsConstructor;
import org.example.accounter.basic_info.account_subject.dto.AccountAddRequest;
import org.example.accounter.basic_info.account_subject.dto.AccountResponse;
import org.example.accounter.basic_info.account_subject.dto.AccountUpdateRequest;
import org.example.accounter.basic_info.account_subject.service.AccountSubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0/basic-info/account-subject")
public class AccountSubjectController {

    private final AccountSubjectService service;

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAll() {
        List<AccountResponse> list = service.getAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody AccountAddRequest request) {
        service.add(request);
        return ResponseEntity.ok("등록되었습니다!");
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestBody List<Long> ids) {
        service.delete(ids);
        return ResponseEntity.ok("삭제되었습니다.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody AccountUpdateRequest request) {
        service.update(id, request);
        return ResponseEntity.ok("수정되었습니다.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> get(@PathVariable("id") Long id) {
        AccountResponse result = service.getDto(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/auto")
    public ResponseEntity<String> autoRegister(@RequestBody List<AccountAddRequest> requests) {
        service.autoAdd(requests);
        return ResponseEntity.ok("등록되었습니다.");
    }


}
