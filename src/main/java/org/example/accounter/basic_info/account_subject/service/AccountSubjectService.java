package org.example.accounter.basic_info.account_subject.service;

import lombok.RequiredArgsConstructor;
import org.example.accounter.basic_info.account_subject.dto.AccountAddRequest;
import org.example.accounter.basic_info.account_subject.dto.AccountResponse;
import org.example.accounter.basic_info.account_subject.dto.AccountUpdateRequest;
import org.example.accounter.basic_info.account_subject.entity.AccountSubject;
import org.example.accounter.basic_info.account_subject.mapper.AccountSubjectMapper;
import org.example.accounter.basic_info.account_subject.repository.AccountSubjectRepository;
import org.example.accounter.core.constants.AccountCategory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountSubjectService {

    private final AccountSubjectRepository repo;
    private final AccountSubjectMapper mapper;

    @Transactional
    public AccountSubject get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 계정과목입니다!"));
    }

    @Transactional
    public AccountSubject get(String name) {
        return repo.findByName(name).orElseThrow(() -> new RuntimeException("존재하지 않는 계정과목입니다!"));
    }

    @Transactional
    public List<AccountSubject> getAll(List<Long> ids) {
        return repo.findAllById(ids);
    }

    @Transactional
    public List<AccountResponse> getAll() {
        return mapper.toResponses(repo.findAll());
    }

    @Transactional
    public AccountResponse getDto(Long id) {
        return mapper.toResponse(get(id));
    }

    @Transactional
    public void add(AccountAddRequest request) {
        repo.save(mapper.toEntity(request));
    }

    @Transactional
    public void autoAdd(List<AccountAddRequest> requests) {
        repo.deleteAll();
        repo.saveAll(mapper.toEntities(requests));
    }

    @Transactional
    public void update(Long id, AccountUpdateRequest request) {
        AccountSubject target = get(id);
        target.update(request);
        repo.save(target);
    }

    @Transactional
    public void delete(List<Long> ids) {
        repo.deleteAllById(ids);
    }
}
