package org.example.accounter.slip.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.accounter.core.constants.SlipType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TransferSlipDto.class, name = "TRANSFER")
})
@SuperBuilder
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
