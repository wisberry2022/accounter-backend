package org.example.accounter.slip.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.accounter.core.constants.SlipType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "slipType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ReceiptSlipRequest.class, name = "RECEIPT"),
        @JsonSubTypes.Type(value = WithdrawalSlipRequest.class, name = "WITHDRAWAL")
})
public class SlipRequest {

    private String desc;
    private String amount;
    private String item;
    private SlipType slipType;


}
