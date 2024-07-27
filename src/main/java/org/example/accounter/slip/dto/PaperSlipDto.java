package org.example.accounter.slip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.accounter.core.constants.SlipType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaperSlipDto {

    private Long id;
    private LocalDateTime date;
    private SubjectDto subject;
    private SlipType slip;
    private String desc;
    private Long amount;
    private String item;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SubjectDto {

        private Long creditId;
        private String credit;
        private Long debitId;
        private String debit;
    }

}
