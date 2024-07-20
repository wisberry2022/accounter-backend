package org.example.accounter.basic_info.sply.mapper;

import org.example.accounter.basic_info.sply.dto.SupplierAddRequest;
import org.example.accounter.basic_info.sply.dto.SupplierResponse;
import org.example.accounter.basic_info.sply.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    Supplier toEntity(SupplierAddRequest request);

    @Mapping(target = "regDttm", source="entity.registrationDate")
    @Mapping(target = "modDttm", source="entity.modificationDate")
    SupplierResponse toResponse(Supplier entity);

    List<SupplierResponse> toResponses(List<Supplier> entities);

}
