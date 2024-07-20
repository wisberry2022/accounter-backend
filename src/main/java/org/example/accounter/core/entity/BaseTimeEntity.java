package org.example.accounter.core.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @Column(name = "reg_dttm", updatable = false)
    @CreatedDate
    private LocalDateTime registrationDate;

    @Column(name = "mod_dttm")
    @LastModifiedDate
    private LocalDateTime modificationDate;

    public LocalDateTime getRegistrationDate() {
        return this.registrationDate;
    }

    public LocalDateTime getModificationDate() {
        return this.modificationDate;
    }

}