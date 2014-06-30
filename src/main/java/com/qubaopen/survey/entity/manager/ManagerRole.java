package com.qubaopen.survey.entity.manager;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by duel on 2014/6/23.
 */

@Entity
@Table(name = "MANAGER_ROLE")
public class ManagerRole extends AbstractPersistable<Long> {

    private String description;

    @Column(columnDefinition = "tinyint(1) DEFAULT 0")
    private Integer isRemoved;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(Integer isRemoved) {
        this.isRemoved = isRemoved;
    }
}