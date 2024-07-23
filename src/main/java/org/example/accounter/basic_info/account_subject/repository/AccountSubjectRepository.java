package org.example.accounter.basic_info.account_subject.repository;

import org.example.accounter.basic_info.account_subject.entity.AccountSubject;
import org.example.accounter.core.constants.AccountCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountSubjectRepository extends JpaRepository<AccountSubject, Long> {

    Optional<AccountSubject> findByName(String name);

}
