package org.example.accounter.slip.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.accounter.slip.entity.SlipEntry;

import java.util.List;

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
        private Long seq;
        private String subject;
        private String desc;
        private Long amount;

        public static SimpleEntryDto fromEntity(SlipEntry entity) {
            return SimpleEntryDto
                    .builder()
                    .seq(entity.getId())
                    .subject(entity.getSubject().getName())
                    .desc(entity.getDesc())
                    .amount(entity.getAmount())
                    .build();
        }

        public static SimpleEntryDto fromNull(int seq) {
            return SimpleEntryDto
                    .builder()
                    .seq(Long.parseLong(String.valueOf(seq)))
                    .amount(0L)
                    .build();
        }

    }

}
