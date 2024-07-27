package org.example.accounter.slip.mapper;

import org.example.accounter.slip.dto.*;
import org.example.accounter.slip.entity.ReceiptSlip;
import org.example.accounter.slip.entity.Slip;
import org.example.accounter.slip.entity.TransferSlip;
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
        if (request instanceof TransferSlipRequest) {
            return toEntity((TransferSlipRequest)request);
        }
        return null;
    }

    default PaperSlipDto toDto(Slip entity) {
        if(entity == null) {
            return null;
        }

        if(entity instanceof TransferSlip) {
            return null;
        }

        return toPaperSlipDto(entity);
    }


    @Mapping(target = "id", ignore = true)
    ReceiptSlip toEntity(ReceiptSlipRequest request);

    @Mapping(target = "id", ignore = true)
    WithdrawalSlip toEntity(WithdrawalSlipRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "credits", ignore = true)
    @Mapping(target = "debits", ignore = true)
    TransferSlip toEntity(TransferSlipRequest request);

    @Mapping(target = "date", source="entity.transactionDateTime")
    @Mapping(target = "slip", source="entity.type")
    @Mapping(target = "subject", ignore = true)
    PaperSlipDto toPaperSlipDto(Slip entity);

}
