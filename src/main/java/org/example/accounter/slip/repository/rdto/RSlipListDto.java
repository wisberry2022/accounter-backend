package org.example.accounter.slip.repository.rdto;

import lombok.*;
import org.example.accounter.core.constants.SlipType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RSlipListDto {

    private Long id;
    private SlipType type;
    private String desc;
    private LocalDateTime transDttm;
    private LocalDateTime regDttm;

}
