package com.company.dmocks.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "DMMOCKS_PHONE_SETTINGS")
@Entity(name = "dmmocks_PhoneSettings")
public class PhoneSettings extends StandardEntity {
    private static final long serialVersionUID = -6404272053002111774L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private User user;

    @NotNull
    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}