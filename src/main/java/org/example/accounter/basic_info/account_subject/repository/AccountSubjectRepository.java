package org.example.accounter.basic_info.account_subject.repository;

import org.example.accounter.basic_info.account_subject.entity.AccountSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSubjectRepository extends JpaRepository<AccountSubject, Long> {

}
