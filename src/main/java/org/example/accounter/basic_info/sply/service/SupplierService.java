package org.example.accounter.basic_info.sply.service;

import lombok.RequiredArgsConstructor;
import org.example.accounter.basic_info.sply.dto.SupplierAddRequest;
import org.example.accounter.basic_info.sply.dto.SupplierResponse;
import org.example.accounter.basic_info.sply.dto.SupplierUpdateRequest;
import org.example.accounter.basic_info.sply.entity.Supplier;
import org.example.accounter.basic_info.sply.mapper.SupplierMapper;
import org.example.accounter.basic_info.sply.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository repo;
    private final SupplierMapper mapper;

    private Supplier get(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            throw new RuntimeException("존재하지 않는 거래처입니다!");
        });
    }

    @Transactional
    public SupplierResponse getDto(Long id) {
        return mapper.toResponse(get(id));
    }

    @Transactional
    public List<SupplierResponse> getAll() {
        List<Supplier> list = repo.findAll();
        return mapper.toResponses(list);
    }

    @Transactional
    public void add(SupplierAddRequest request) {
        repo.save(mapper.toEntity(request));
    }

    @Transactional
    public void update(Long id, SupplierUpdateRequest update) {
        Supplier sply = get(id);
        sply.update(update);
    }

    @Transactional
    public void delete(List<Long> ids) {
        repo.deleteAllById(ids);
    }

}
