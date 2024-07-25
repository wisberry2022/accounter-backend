package org.example.accounter.slip.controller.response;

import lombok.*;
import org.example.accounter.core.constants.SlipType;
import org.example.accounter.slip.repository.rdto.RSlipListDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SlipListResponse {

    private Long id;
    private SlipType type;
    private String desc;
    private LocalDateTime transDttm;
    private LocalDateTime regDttm;

    public static SlipListResponse fromRDto(RSlipListDto dto) {
        return SlipListResponse
                .builder()
                .id(dto.getId())
                .type(dto.getType())
                .desc(dto.getDesc())
                .transDttm(dto.getTransDttm())
                .regDttm(dto.getRegDttm())
                .build();
    }

}
