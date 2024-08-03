package org.example.accounter.slip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.accounter.basic_info.account_subject.entity.AccountSubject;
import org.example.accounter.slip.dto.SlipEntryDto;
import org.example.accounter.slip.dto.TransferSlipDto;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SlipEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "slip_id")
    private TransferSlip slip;

    @Comment("계정과목")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "act_sbj_id")
    private AccountSubject subject;

    @Comment("적요")
    @Column(name = "entry_desc")
    private String desc;

    @Comment("금액")
    @Column(name = "amount")
    private Long amount;

    public static SlipEntry of(SlipEntryDto dto, AccountSubject subject) {
        return SlipEntry
                .builder()
                .subject(subject)
                .desc(dto.getDesc())
                .amount(dto.getAmount())
                .build();
    }

    public static SlipEntry of(Slip slip, TransferSlipDto.SimpleEntryDto dto, AccountSubject subject) {
        return SlipEntry
                .builder()
                .slip((TransferSlip) slip)
                .subject(subject)
                .desc(dto.getDesc())
                .amount(dto.getAmount())
                .build();
    }

    public void update(TransferSlipDto.SimpleEntryDto update, AccountSubject account) {
        this.amount = update.getAmount();
        this.desc = update.getDesc();
        this.subject = account;
    }


}
