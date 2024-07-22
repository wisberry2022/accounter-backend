package org.example.accounter.slip.mapper;

import org.example.accounter.basic_info.account_subject.entity.AccountSubject;
import org.example.accounter.basic_info.account_subject.service.AccountSubjectService;
import org.example.accounter.slip.dto.ReceiptSlipRequest;
import org.example.accounter.slip.dto.SlipRequest;
import org.example.accounter.slip.entity.ReceiptSlip;
import org.example.accounter.slip.entity.Slip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        slip.setCredit(credit);
        return slip;
    }

}
