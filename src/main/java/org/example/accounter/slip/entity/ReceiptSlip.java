package org.example.accounter.slip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.accounter.basic_info.account_subject.entity.AccountSubject;

@Getter
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("RECEIPT")
@Entity
public class ReceiptSlip extends Slip {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_sbj_id")
    private AccountSubject credit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debit_sbj_id")
    private AccountSubject debit;

    public void setCredit(AccountSubject credit) {
        this.credit = credit;
    }

    public void setDebit(AccountSubject debit) {
        this.debit = debit;
    }
}
