package org.example.accounter.slip.service;

import lombok.RequiredArgsConstructor;
import org.example.accounter.basic_info.account_subject.service.AccountSubjectService;
import org.example.accounter.core.constants.SlipType;
import org.example.accounter.slip.controller.response.SlipListResponse;
import org.example.accounter.slip.dto.PaperSlipDto;
import org.example.accounter.slip.dto.ReceiptSlipRequest;
import org.example.accounter.slip.dto.SlipRequest;
import org.example.accounter.slip.dto.TransferSlipDto;
import org.example.accounter.slip.entity.ReceiptSlip;
import org.example.accounter.slip.entity.Slip;
import org.example.accounter.slip.entity.TransferSlip;
import org.example.accounter.slip.entity.WithdrawalSlip;
import org.example.accounter.slip.mapper.SlipMapper;
import org.example.accounter.slip.repository.SlipRepository;
import org.example.accounter.slip.repository.rdto.RSlipListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SlipService {

    private final SlipRepository repo;
    private final SlipMapper mapper;
    private final AccountSubjectService subjectService;

    @Transactional
    public Slip get(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            throw new RuntimeException("존재하지 않는 전표입니다!");
        });
    }

    @Transactional
    public PaperSlipDto getDto(Long id) {
        Slip slip = get(id);
        if(slip.getType().equals(SlipType.TRANSFER)) {
            TransferSlipDto dto = mapper.toTransferSlipDto((TransferSlip) slip);
            return dto;
        }
        PaperSlipDto dto = mapper.toDto(slip);
        return dto;
    }

    @Transactional
    public Page<SlipListResponse> getAllDto(int page, int size) {
         Pageable pageable = PageRequest.of(page, size);
         return repo.findAllDto(pageable).map(SlipListResponse::fromRDto);

    }

    @Transactional
    public void write(SlipRequest request) {
        Slip slip = mapper.toEntity(request);
        repo.save(slip);
    }

    @Transactional
    public void update(PaperSlipDto update) {
        Slip slip = get(update.getId());
        if(update.getSlip().equals(SlipType.RECEIPT)) {
            ReceiptSlip rSlip = (ReceiptSlip)slip;
            rSlip.update(update, subjectService.get(update.getSubject().getCreditId()));
            repo.save(rSlip);
        }
        if(update.getSlip().equals(SlipType.WITHDRAWAL)) {
            WithdrawalSlip wSlip = (WithdrawalSlip)slip;
            wSlip.update(update, subjectService.get(update.getSubject().getDebitId()));
            repo.save(wSlip);
        }
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
