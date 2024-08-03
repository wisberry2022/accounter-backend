package org.example.accounter.slip.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.accounter.basic_info.account_subject.entity.AccountSubject;
import org.example.accounter.slip.dto.PaperSlipDto;
import org.example.accounter.slip.dto.SlipRequest;

@Getter
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("WITHDRAWAL")
@Entity
public class WithdrawalSlip extends Slip {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debit_sbj_id")
    private AccountSubject debit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_sbj_id")
    private AccountSubject credit;

    public void update(PaperSlipDto update, AccountSubject debit) {
        super.update(update);
        this.debit = debit;
    }

    public void setDebit(AccountSubject debit) {
        this.debit = debit;
    }

    public void setCredit(AccountSubject credit) {
        this.credit =  credit;
    }

}
