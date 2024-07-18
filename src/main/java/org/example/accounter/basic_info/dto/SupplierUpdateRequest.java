package org.example.accounter.basic_info.dto;

import lombok.*;
import org.example.accounter.core.constants.MainType;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SupplierUpdateRequest {

    private Long id;
    private String name;
    private String representationName;
    private String corpCode;
    private String address;
    private String category;
    private MainType main;

}
