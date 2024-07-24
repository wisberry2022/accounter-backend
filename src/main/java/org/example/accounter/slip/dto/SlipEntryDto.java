package org.example.accounter.slip.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SlipEntryDto {

    private Long subjectId;
    private String desc;
    private Long amount;

}
