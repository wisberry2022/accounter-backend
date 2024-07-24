package org.example.accounter.slip.mapper;

import org.example.accounter.slip.dto.SlipEntryDto;
import org.example.accounter.slip.entity.SlipEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SlipEntryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subject", ignore = true)
    SlipEntry toEntity(SlipEntryDto dto);

    List<SlipEntry> toEntities(List<SlipEntryDto> dtoList);

}
