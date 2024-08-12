package org.example.accounter.slip.repository;

import org.example.accounter.slip.controller.request.SlipFilterRequest;
import org.example.accounter.slip.repository.rdto.RSlipListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SlipCustomRepository {

    Page<RSlipListDto> findAllDto(Pageable pageable, SlipFilterRequest request);

}
