package org.example.accounter.slip.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.accounter.slip.dto.PaperSlipDto;
import org.example.accounter.slip.dto.SlipRequest;
import org.example.accounter.slip.dto.TransferSlipRequest;

import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder
@DiscriminatorValue("TRANSFER")
@Entity
public class TransferSlip extends Slip {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "slip_credit_entry_id")
    private List<SlipEntry> credits;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "slip_debit_entry_id")
    private List<SlipEntry> debits;

    public void update(PaperSlipDto update, List<SlipEntry> credits, List<SlipEntry> debits) {
        super.update(update);
        this.credits = credits;
        this.debits = debits;
    }

    public void addCredit(SlipEntry entry) {
        this.credits.add(entry);
    }

    public void addDebit(SlipEntry entry) {
        this.debits.add(entry);
    }

    public void removeCredit(Long id) {
        this.credits = this.credits
                .stream()
                .filter(credit -> credit.getId().equals(id))
                .toList();
    }

    public void removeDebit(Long id) {
        this.debits = this.debits
                .stream()
                .filter(debit -> debit.getId().equals(id))
                .toList();
    }

    public void setCredits(List<SlipEntry> entries) {
        this.credits = entries;
    }

    public void setDebits(List<SlipEntry> entries) {
        this.debits = entries;
    }

}
