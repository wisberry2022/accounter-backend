package org.example.accounter.slip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.accounter.core.constants.SlipType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceiptSlipRequest extends SlipRequest {

    private Long creditId;

    @Override
    public SlipType getSlipType() {
        return SlipType.RECEIPT;
    }

}
