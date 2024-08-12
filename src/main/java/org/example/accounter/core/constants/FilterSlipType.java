package org.example.accounter.core.constants;

import lombok.Getter;

@Getter
public enum FilterSlipType {

    RECEIPT, WITHDRAWAL, TRANSFER, TOTAL;

    public SlipType convert() {
        return this.name().equals("TOTAL") ? null : SlipType.valueOf(this.name());
    }

}
