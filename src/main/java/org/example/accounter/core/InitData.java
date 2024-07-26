package org.example.accounter.core;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.accounter.core.constants.SlipType;
import org.example.accounter.slip.entity.ReceiptSlip;
import org.example.accounter.slip.entity.Slip;
import org.example.accounter.slip.entity.TransferSlip;
import org.example.accounter.slip.entity.WithdrawalSlip;
import org.example.accounter.slip.repository.SlipRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitData {

    @Value("${test.data.slip.active}")
    private boolean isCreateTempSlip;

    @Value("${test.data.slip.size}")
    private int createSize;

    private final SlipRepository slipRepo;

    @PostConstruct
    public void initData() {
        createTempSlip();
    }

    private void createTempSlip() {
        if(isCreateTempSlip) {
            List<Slip> data = new ArrayList<>();
            for(int i = 0; i<createSize; i++) {
                Slip test = getType(i)
                        .type(SlipType.RECEIPT)
                        .desc("테스트 적요".concat(String.valueOf(i+1)))
                        .transactionDateTime(LocalDateTime.now())
                        .build();
                data.add(test);
            }
            slipRepo.saveAll(data);
            log.info("TEMP SLIP CREATED!");
            return;
        }
        log.info("TEMP SLIP NOT CREATED!");
    }

    private Slip.SlipBuilder<?, ?> getType(int i) {
        int mod = i%3;
        if(mod == 0) {
            return ReceiptSlip.builder().type(SlipType.RECEIPT);
        }
        if(mod == 1) {
            return WithdrawalSlip.builder().type(SlipType.WITHDRAWAL);
        }
        if (mod == 2) {
            return TransferSlip.builder().type(SlipType.TRANSFER);
        }
        return ReceiptSlip.builder().type(SlipType.RECEIPT);
    }

}
