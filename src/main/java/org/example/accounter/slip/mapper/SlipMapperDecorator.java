package org.example.accounter.slip.mapper;

import org.example.accounter.basic_info.account_subject.entity.AccountSubject;
import org.example.accounter.basic_info.account_subject.service.AccountSubjectService;
import org.example.accounter.slip.dto.ReceiptSlipRequest;
import org.example.accounter.slip.dto.SlipEntryDto;
import org.example.accounter.slip.dto.TransferSlipRequest;
import org.example.accounter.slip.dto.WithdrawalSlipRequest;
import org.example.accounter.slip.entity.ReceiptSlip;
import org.example.accounter.slip.entity.SlipEntry;
import org.example.accounter.slip.entity.TransferSlip;
import org.example.accounter.slip.entity.WithdrawalSlip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class SlipMapperDecorator implements SlipMapper {

    @Autowired
    private AccountSubjectService accountService;
    @Autowired
    private SlipMapper delegate;
    @Autowired
    private SlipEntryMapper entryMapper;

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

    private List<SlipEntry> getEntries(List<SlipEntryDto> dto) {
        return dto.stream()
                .map(ent ->
                        SlipEntry.of(ent, accountService.get(ent.getSubjectId()))
                ).toList();
    }

}
