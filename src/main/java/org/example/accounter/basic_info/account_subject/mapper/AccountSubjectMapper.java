package org.example.accounter.basic_info.account_subject.mapper;

import org.example.accounter.basic_info.account_subject.dto.AccountAddRequest;
import org.example.accounter.basic_info.account_subject.dto.AccountResponse;
import org.example.accounter.basic_info.account_subject.entity.AccountSubject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountSubjectMapper {

    @Mapping(target = "id", ignore = true)
    AccountSubject toEntity(AccountAddRequest request);

    List<AccountSubject> toEntities(List<AccountAddRequest> requests);

    @Mapping(target = "regDttm", source="entity.registrationDate")
    @Mapping(target = "modDttm", source="entity.modificationDate")
    AccountResponse toResponse(AccountSubject entity);

    List<AccountResponse> toResponses(List<AccountSubject> entities);

}
