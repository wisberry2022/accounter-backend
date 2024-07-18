package org.example.accounter.basic_info.service;

import lombok.RequiredArgsConstructor;
import org.example.accounter.basic_info.dto.SupplierAddRequest;
import org.example.accounter.basic_info.dto.SupplierResponse;
import org.example.accounter.basic_info.dto.SupplierUpdateRequest;
import org.example.accounter.basic_info.entity.Supplier;
import org.example.accounter.basic_info.mapper.SupplierMapper;
import org.example.accounter.basic_info.repository.SupplierRepository;
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
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
