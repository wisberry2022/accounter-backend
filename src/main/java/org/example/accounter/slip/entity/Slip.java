package org.example.accounter.slip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.accounter.core.constants.SlipType;
import org.example.accounter.core.entity.BaseTimeEntity;
import org.example.accounter.slip.dto.PaperSlipDto;
import org.example.accounter.slip.dto.SlipRequest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "slip_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "slip")
@Entity
public class Slip extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "slip_type", insertable = false, updatable = false)
    private SlipType type;

    @Column(name = "slip_desc")
    private String desc;

    @Column(name = "amount")
    private Long amount;

    @Column(name= "slip_item")
    private String item;

    @Column(name = "tran_dttm")
    private LocalDateTime transactionDateTime;

    public void update(PaperSlipDto request) {
        this.desc = request.getDesc();
        this.amount = request.getAmount();
        this.item = request.getItem();
        this.transactionDateTime = request.getDate();
    }
}
