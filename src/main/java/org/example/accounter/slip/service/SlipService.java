package org.example.accounter.slip.service;

import lombok.RequiredArgsConstructor;
import org.example.accounter.slip.dto.ReceiptSlipRequest;
import org.example.accounter.slip.dto.SlipRequest;
import org.example.accounter.slip.entity.Slip;
import org.example.accounter.slip.entity.TransferSlip;
import org.example.accounter.slip.mapper.SlipMapper;
import org.example.accounter.slip.repository.SlipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SlipService {

    private final SlipRepository repo;
    private final SlipMapper mapper;

    @Transactional
    public void write(SlipRequest request) {
        Slip slip = mapper.toEntity(request);
        repo.save(slip);
    }
}
