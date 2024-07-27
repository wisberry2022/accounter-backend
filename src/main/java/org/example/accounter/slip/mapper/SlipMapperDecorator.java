package org.example.accounter.slip.mapper;

import org.example.accounter.basic_info.account_subject.entity.AccountSubject;
import org.example.accounter.basic_info.account_subject.service.AccountSubjectService;
import org.example.accounter.slip.dto.*;
import org.example.accounter.slip.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public abstract class SlipMapperDecorator implements SlipMapper {

    @Autowired
    private AccountSubjectService accountService;
    @Autowired
    private SlipMapper delegate;


    @Override
    public ReceiptSlip toEntity(ReceiptSlipRequest request) {
        ReceiptSlip slip = delegate.toEntity(request);
        AccountSubject credit = accountService.get(request.getCreditId());
        AccountSubject debit = accountService.get("현금");
        slip.setCredit(credit);
        slip.setDebit(debit);
        return slip;
    }

    @Override
    public WithdrawalSlip toEntity(WithdrawalSlipRequest request) {
        WithdrawalSlip slip = delegate.toEntity(request);
        AccountSubject debit = accountService.get(request.getDebitId());
        AccountSubject credit = accountService.get("현금");
        slip.setDebit(debit);
        slip.setCredit(credit);
        return slip;
    }

    @Override
    public TransferSlip toEntity(TransferSlipRequest request) {
        TransferSlip slip = delegate.toEntity(request);
        slip.setCredits(getEntries(request.getCredits()));
        slip.setDebits(getEntries(request.getDebits()));
        return slip;
    }

    @Override
    public PaperSlipDto toDto(Slip entity) {
        PaperSlipDto dto = delegate.toPaperSlipDto(entity);
        if(entity instanceof ReceiptSlip) {
            dto.setSubject(
                    PaperSlipDto.SubjectDto
                            .builder()
                            .creditId(getAccountId(((ReceiptSlip) entity).getCredit()))
                            .credit(getAccountName(((ReceiptSlip) entity).getCredit()))
                            .build()
            );
        }
        if(entity instanceof WithdrawalSlip) {
            dto.setSubject(
                    PaperSlipDto.SubjectDto
                            .builder()
                            .debitId(getAccountId(((WithdrawalSlip) entity).getDebit()))
                            .debit(getAccountName(((WithdrawalSlip) entity).getDebit()))
                            .build()
            );
        }
        return dto;
    }

    private Long getAccountId(AccountSubject subject) {
        if(subject == null) {
            return 0L;
        }
        return Optional.ofNullable(subject.getId()).orElse(0L);
    }

    private String getAccountName(AccountSubject subject) {
        if(subject == null) {
            return "";
        }
        return Optional.ofNullable(subject.getName()).orElse("");
    }

    private List<SlipEntry> getEntries(List<SlipEntryDto> dto) {
        return dto.stream()
                .map(ent ->
                        SlipEntry.of(ent, accountService.get(ent.getSubjectId()))
                ).toList();
    }

}
