package org.example.accounter.basic_info.dto;

import lombok.*;
import org.example.accounter.core.constants.MainType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SupplierAddRequest {

    private String name;
    private String representationName;
    private String corpCode;
    private String address;
    private String category;
    private MainType main;

}
