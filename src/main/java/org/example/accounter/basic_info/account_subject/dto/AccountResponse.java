package org.example.accounter.basic_info.account_subject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.accounter.core.constants.AccountCategory;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountResponse {

    private Long id;
    private AccountCategory category;
    private String name;
    private String code;
    private Boolean activated;
    private LocalDateTime regDttm;
    private LocalDateTime modDttm;

}
