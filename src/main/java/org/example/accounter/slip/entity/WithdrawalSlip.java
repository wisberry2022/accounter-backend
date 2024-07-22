package org.example.accounter.slip.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.accounter.basic_info.account_subject.entity.AccountSubject;

@Getter
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("WITHDRAWAL")
@Entity
public class WithdrawalSlip extends Slip {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debit_sbj_id")
    private AccountSubject subject;

}
