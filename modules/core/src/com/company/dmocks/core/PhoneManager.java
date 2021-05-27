package com.company.dmocks.core;

import com.company.dmocks.entity.PhoneSettings;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component(PhoneManager.NAME)
public class PhoneManager {
    public static final String NAME = "dmmocks_PhoneManager";

    @Inject
    private DataManager dataManager;

    public String getPhoneNumber(User user) {
        PhoneSettings sett = dataManager.load(PhoneSettings.class)
                .query("select s from dmmocks_PhoneSettings s where s.user = :user")
                .parameter("user", user)
                .optional()
                .orElse(null);
        return sett != null ? sett.getPhoneNumber() : "";
    }

}