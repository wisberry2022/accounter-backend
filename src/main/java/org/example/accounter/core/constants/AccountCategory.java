package org.example.accounter.core.constants;

import lombok.Getter;

@Getter
public enum AccountCategory {

    CAPITAL("CAPITAL", "자본"),
    ASSET("ASSET", "자산"),
    DEBT("DEBT", "부채"),
    REVENUE("REVENUE", "수익"),
    EXPENSE("EXPENSE", "비용");

    private final String category;
    private final String desc;

    AccountCategory(String category, String desc) {
        this.category = category;
        this.desc = desc;
    }
}
