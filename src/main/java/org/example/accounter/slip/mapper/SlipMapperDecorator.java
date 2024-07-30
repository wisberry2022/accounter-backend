package org.example.accounter.slip.mapper;

import lombok.With;
import org.example.accounter.basic_info.account_subject.entity.AccountSubject;
import org.example.accounter.basic_info.account_subject.service.AccountSubjectService;
import org.example.accounter.core.util.NullableGetter;
import org.example.accounter.slip.dto.*;
import org.example.accounter.slip.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

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
                            .creditId(NullableGetter
                                        .getLong(((ReceiptSlip) entity).getCredit(),
                                                    ((ReceiptSlip) entity).getCredit()::getId
                                                )
                                      )
                            .credit(NullableGetter
                                        .getStr(((ReceiptSlip) entity).getCredit(),
                                                    ((ReceiptSlip) entity).getCredit()::getName
                                        )
                                    )
                            .build()
            );
        }
        if(entity instanceof WithdrawalSlip) {
            dto.setSubject(
                    PaperSlipDto.SubjectDto
                            .builder()
                            .debitId(
                                    NullableGetter
                                            .getLong(
                                                ((WithdrawalSlip) entity).getDebit(), ((WithdrawalSlip) entity).getDebit()::getId
                                            )
                                    )
                            .debit(
                                    NullableGetter
                                            .getStr(
                                                ((WithdrawalSlip) entity).getDebit(), ((WithdrawalSlip) entity).getDebit()::getName
                                            )
                                   )
                            .build()
            );
        }
        return dto;
    }

    @Override
    public TransferSlipDto toTransferSlipDto(TransferSlip entity) {
        TransferSlipDto dto = delegate.toTransferSlipDto(entity);

        int creditSize = entity.getCredits().size();
        int debitSize = entity.getDebits().size();

        List<SlipEntry> credits = entity.getCredits();
        List<SlipEntry> debits = entity.getDebits();

        List<TransferSlipDto.TransferEntry> list = null;
        // 차변 개수 == 대변 개수의 경우
        if(debitSize == creditSize) {
            list = IntStream.range(0, debitSize)
                    .mapToObj(i -> {
                      TransferSlipDto.SimpleEntryDto debit = TransferSlipDto.SimpleEntryDto.fromEntity(debits.get(i));
                      TransferSlipDto.SimpleEntryDto credit = TransferSlipDto.SimpleEntryDto.fromEntity(credits.get(i));

                      return TransferSlipDto.TransferEntry
                              .builder()
                              .seq(Long.parseLong(String.valueOf(i)))
                              .debit(debit)
                              .credit(credit)
                              .build();
                    })
                    .toList();
        }
        // 차변 개수 > 대변 개수
        if(debitSize > creditSize) {
            list = IntStream.range(0, debitSize)
                    .mapToObj(i -> {
                        TransferSlipDto.SimpleEntryDto debit = TransferSlipDto.SimpleEntryDto.fromEntity(debits.get(i));
                        TransferSlipDto.SimpleEntryDto credit = TransferSlipDto.SimpleEntryDto.fromEntity(credits.size() > i ? credits.get(i) : null);

                        return TransferSlipDto.TransferEntry
                                .builder()
                                .seq(Long.parseLong(String.valueOf(i)))
                                .debit(debit)
                                .credit(credit)
                                .build();
                    }).toList();
        }
        // 차변 개수 < 대변 개수
        if(debitSize < creditSize) {
            list = IntStream.range(0, creditSize)
                    .mapToObj(i -> {
                        TransferSlipDto.SimpleEntryDto credit = TransferSlipDto.SimpleEntryDto.fromEntity(credits.get(i));
                        TransferSlipDto.SimpleEntryDto debit = TransferSlipDto.SimpleEntryDto.fromEntity(debits.size() > i ? debits.get(i) : null);

                        return TransferSlipDto.TransferEntry
                                .builder()
                                .seq(Long.parseLong(String.valueOf(i)))
                                .credit(credit)
                                .debit(debit)
                                .build();
                    })
                    .toList();
        }

        dto.setEntries(list);
        return dto;
    }


    private List<SlipEntry> getEntries(List<SlipEntryDto> dto) {
        return dto.stream()
                .map(ent ->
                        SlipEntry.of(ent, accountService.get(ent.getSubjectId()))
                ).toList();
    }

}
