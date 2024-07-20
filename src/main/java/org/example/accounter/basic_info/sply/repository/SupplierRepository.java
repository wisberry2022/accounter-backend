package org.example.accounter.basic_info.sply.repository;

import org.example.accounter.basic_info.sply.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
