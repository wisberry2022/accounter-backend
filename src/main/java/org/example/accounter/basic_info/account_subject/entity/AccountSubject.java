package org.example.accounter.basic_info.account_subject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.accounter.basic_info.account_subject.dto.AccountUpdateRequest;
import org.example.accounter.core.constants.AccountCategory;
import org.example.accounter.core.entity.BaseTimeEntity;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "act_sbj")
public class AccountSubject extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "act_category", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private AccountCategory category;

    @Column(name = "act_nm", nullable = false, length = 50)
    private String name;

    @Column(name = "act_code", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "is_act", nullable = false)
    private Boolean activated;

    public void update(AccountUpdateRequest request) {
        this.category = request.getCategory();
        this.name = request.getName();
        this.code = request.getCode();
        this.activated = request.getActivated();
    }


}
