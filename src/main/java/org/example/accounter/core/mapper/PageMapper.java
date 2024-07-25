package org.example.accounter.core.mapper;

import org.example.accounter.core.dto.SimplePageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PageMapper {

    @Mapping(target = "content", source="page.content")
    SimplePageDto toPageDto(Page page);

}
