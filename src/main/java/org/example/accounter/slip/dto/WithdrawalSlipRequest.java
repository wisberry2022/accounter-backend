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
public class WithdrawalSlipRequest extends SlipRequest {

    private Long debitId;

    @Override
    public SlipType getSlipType() {
        return SlipType.WITHDRAWAL;
    }

}
