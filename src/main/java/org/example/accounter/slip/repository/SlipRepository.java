package org.example.accounter.slip.repository;

import org.example.accounter.slip.entity.Slip;
import org.example.accounter.slip.repository.rdto.RSlipListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SlipRepository extends JpaRepository<Slip, Long> {

    @Query("SELECT new org.example.accounter.slip.repository.rdto.RSlipListDto(" +
            "s.id, " +
            "s.type, " +
            "s.desc, " +
            "s.transactionDateTime, " +
            "s.registrationDate " +
            ") FROM Slip s")
    Page<RSlipListDto> findAllDto(Pageable pageable);

}
