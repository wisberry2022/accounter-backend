package org.example.accounter.basic_info.mapper;

import org.example.accounter.basic_info.dto.SupplierAddRequest;
import org.example.accounter.basic_info.dto.SupplierResponse;
import org.example.accounter.basic_info.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    Supplier toEntity(SupplierAddRequest request);

    SupplierResponse toResponse(Supplier entity);

    List<SupplierResponse> toResponses(List<Supplier> entities);

}
