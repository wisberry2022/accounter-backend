package org.example.accounter.core.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SimplePageDto<DTO> {

    private Long totalElements;
    private int totalPages;
    private List<DTO> content;

}
