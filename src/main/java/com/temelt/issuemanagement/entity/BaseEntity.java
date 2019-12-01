package com.temelt.issuemanagement.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {
    @Column(name = "crated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "crated_by", length = 100)
    private String createdBy;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "updated_by", length = 100)
    private String updatedBy;
    @Column(name = "status")
    private Boolean status;


}
