package org.example.accounter.basic_info.sply.dto;

import lombok.*;
import org.example.accounter.core.constants.MainType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SupplierResponse {

    private Long id;
    private String name;
    private String representationName;
    private String corpCode;
    private String address;
    private String category;
    private MainType main;
    private LocalDateTime regDttm;
    private LocalDateTime modDttm;

}
