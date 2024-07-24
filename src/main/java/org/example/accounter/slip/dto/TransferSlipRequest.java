package org.example.accounter.slip.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.accounter.core.constants.SlipType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferSlipRequest extends SlipRequest {

    private List<SlipEntryDto> credits;
    private List<SlipEntryDto> debits;

    @Override
    public SlipType getSlipType() {return SlipType.TRANSFER;}

}
