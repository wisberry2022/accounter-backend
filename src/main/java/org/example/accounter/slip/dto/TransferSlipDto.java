package org.example.accounter.slip.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.accounter.slip.entity.SlipEntry;

import java.util.List;
import java.util.Optional;

@Data
@SuperBuilder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public class TransferSlipDto extends PaperSlipDto {

    private List<TransferEntry> entries;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TransferEntry {
        private Long seq;
        private TransferSlipDto.SimpleEntryDto debit;
        private TransferSlipDto.SimpleEntryDto credit;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SimpleEntryDto {
        private Long id;
        private String subject;
        private String desc;
        private Long amount;

        public static SimpleEntryDto fromEntity(SlipEntry entity) {
            return SimpleEntryDto
                    .builder()
                    .id(entity.getSubject().getId())
                    .subject(entity.getSubject().getName())
                    .desc(entity.getDesc())
                    .amount(entity.getAmount())
                    .build();
        }

        public static SimpleEntryDto fromNull() {
            return SimpleEntryDto
                    .builder()
                    .id(null)
                    .subject(null)
                    .amount(0L)
                    .build();
        }

    }

}
