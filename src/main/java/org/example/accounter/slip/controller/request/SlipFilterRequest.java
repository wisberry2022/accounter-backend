package org.example.accounter.slip.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.accounter.core.constants.SlipType;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SlipFilterRequest {

    private LocalDateTime startRegDttm;
    private LocalDateTime endRegDttm;
    private LocalDateTime startTranDttm;
    private LocalDateTime endTranDttm;
    private SlipType slip;
    private String keyword;

}
