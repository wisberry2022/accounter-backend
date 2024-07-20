package org.example.accounter.basic_info.account_subject.dto;

import lombok.*;
import org.example.accounter.core.constants.AccountCategory;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountUpdateRequest {

    private AccountCategory category;
    private String name;
    private String code;
    private Boolean activated;

}
