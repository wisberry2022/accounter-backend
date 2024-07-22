package org.example.accounter.slip.repository;

import org.example.accounter.slip.entity.Slip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlipRepository extends JpaRepository<Slip, Long> {
}
