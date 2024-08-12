package org.example.accounter.slip.service;

import lombok.RequiredArgsConstructor;
import org.example.accounter.basic_info.account_subject.service.AccountSubjectService;
import org.example.accounter.core.constants.SlipType;
import org.example.accounter.slip.controller.request.SlipFilterRequest;
import org.example.accounter.slip.controller.response.SlipListResponse;
import org.example.accounter.slip.dto.PaperSlipDto;
import org.example.accounter.slip.dto.SlipRequest;
import org.example.accounter.slip.dto.TransferSlipDto;
import org.example.accounter.slip.entity.*;
import org.example.accounter.slip.mapper.SlipMapper;
import org.example.accounter.slip.repository.SlipCustomRepository;
import org.example.accounter.slip.repository.SlipRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlipService {

    private final SlipRepository repo;
    private final SlipCustomRepository qRepo;
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
            return mapper.toTransferSlipDto((TransferSlip) slip);
        }
        return mapper.toDto(slip);
    }

    @Transactional
    public Page<SlipListResponse> getAllDto(int page, int size, SlipFilterRequest request) {
         Pageable pageable = PageRequest.of(page, size);
         return qRepo.findAllDto(pageable, request).map(SlipListResponse::fromRDto);
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
        if(update.getSlip().equals(SlipType.TRANSFER)) {
            TransferSlip tSlip = (TransferSlip)slip;
            TransferSlipDto tDto = (TransferSlipDto)update;

            List<TransferSlipDto.SimpleEntryDto> creditDtos = new ArrayList<>();
            List<TransferSlipDto.SimpleEntryDto> debitDtos = new ArrayList<>();
            
            tDto.getEntries().forEach(entry -> {
                TransferSlipDto.SimpleEntryDto credit = entry.getCredit();
                TransferSlipDto.SimpleEntryDto debit = entry.getDebit();
                if(credit != null) {
                    creditDtos.add(credit);
                }
                if(debit != null) {
                    debitDtos.add(debit);
                }
            });

            // 차변 업데이트            
            List<SlipEntry> debits = tSlip.getDebits();

            debits.removeIf(entity -> !debitDtos
                    .stream()
                    .map(TransferSlipDto.SimpleEntryDto::getId)
                    .toList()
                    .contains(entity.getId())
            );

            for(TransferSlipDto.SimpleEntryDto debit:debitDtos) {
                Long id = debit.getId();
                if(id != null) {
                    // 업데이트
                    debits.stream()
                            .filter(entity -> entity.getId().equals(id))
                            .findFirst()
                            .ifPresent(entity -> entity.update(debit, subjectService.get(debit.getSubjectId())));
                }
                if(id == null && debit.getSubjectId() != null) {
                    // 신규 추가
                    SlipEntry newEntry = SlipEntry.of(
                            tSlip, debit, subjectService.get(debit.getSubjectId())
                    );
                    tSlip.addDebit(newEntry);
                }
            }

            // 대변 업데이트            
            List<SlipEntry> credits =  tSlip.getCredits();

            credits.removeIf(entity -> !creditDtos
                    .stream()
                    .map(TransferSlipDto.SimpleEntryDto::getId)
                    .toList()
                    .contains(entity.getId())
            );

            for(TransferSlipDto.SimpleEntryDto credit:creditDtos) {
                Long id = credit.getId();
                if(id != null) {
                    // 업데이트
                    credits.stream()
                            .filter(entity -> entity.getId().equals(id))
                            .findFirst()
                            .ifPresent(entity -> entity.update(credit, subjectService.get(credit.getSubjectId())));
                }
                if(id == null && credit.getSubjectId() != null) {
                    // 신규 추가
                    SlipEntry newEntry = SlipEntry.of(
                            tSlip, credit, subjectService.get(credit.getSubjectId())
                    );
                    tSlip.addCredit(newEntry);
                }
            }
            repo.save(tSlip);
        }
    }
    
    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
