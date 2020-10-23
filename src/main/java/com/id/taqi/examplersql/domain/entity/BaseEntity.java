package com.id.taqi.examplersql.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdDate", "updatedDate"}, allowGetters = true)
@Data
@NoArgsConstructor
public class BaseEntity implements Serializable {
    @Column(name = "created_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate = new Date();

    @Column(name = "updated_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedDate;

    @Column(name = "created_user")
    @CreatedBy
    private String createdUser;

    @Column(name = "updated_user")
    @LastModifiedBy
    private String updatedUser;

    @PrePersist
    public void prePersist() {
        createdDate = updatedDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = new Date();
    }
}