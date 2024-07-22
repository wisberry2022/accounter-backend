package org.example.accounter.slip.mapper;

import org.example.accounter.slip.dto.ReceiptSlipRequest;
import org.example.accounter.slip.dto.SlipRequest;
import org.example.accounter.slip.dto.WithdrawalSlipRequest;
import org.example.accounter.slip.entity.ReceiptSlip;
import org.example.accounter.slip.entity.Slip;
import org.example.accounter.slip.entity.WithdrawalSlip;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@DecoratedWith(SlipMapperDecorator.class)
public interface SlipMapper {

    default Slip toEntity(SlipRequest request) {
        if(request instanceof ReceiptSlipRequest) {
            return toEntity((ReceiptSlipRequest)request);
        }
        if(request instanceof WithdrawalSlipRequest) {
            return toEntity((WithdrawalSlipRequest)request);
        }
        return null;
    }

    @Mapping(target = "id", ignore = true)
    ReceiptSlip toEntity(ReceiptSlipRequest request);

    @Mapping(target = "id", ignore = true)
    WithdrawalSlip toEntity(WithdrawalSlipRequest request);
}
