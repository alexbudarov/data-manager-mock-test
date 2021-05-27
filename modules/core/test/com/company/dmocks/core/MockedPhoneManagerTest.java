package com.company.dmocks.core;

import com.company.dmocks.DmmocksMockedDMTestContainer;
import com.company.dmocks.entity.PhoneSettings;
import com.company.dmocks.sys.MyDataManagerBean;
import com.google.common.collect.Lists;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.testsupport.TestContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
// See https://doc.cuba-platform.com/manual-7.2/integration_tests_mw.html

class MockedPhoneManagerTest {

    @RegisterExtension
    static TestContainer container = DmmocksMockedDMTestContainer.Common.INSTANCE;
    static MyDataManagerBean dataManager;

    private static PhoneManager phoneManager;

    @BeforeAll
    static void beforeAll() {
        dataManager = (MyDataManagerBean) AppBeans.get(DataManager.class);
        phoneManager = AppBeans.get(PhoneManager.class);
    }

    @AfterAll
    static void afterAll() {

    }

    @Test
    void testPhoneSettingsExists() {
        User u = container.metadata().create(User.class);

        PhoneSettings ps = container.metadata().create(PhoneSettings.class);
        ps.setPhoneNumber("+44155");
        ps.setUser(u);

        when(dataManager.getMockDelegate().load(any(LoadContext.class)))
                .thenReturn(ps);

        String phoneNumber = phoneManager.getPhoneNumber(u);
        assertEquals("+44155", phoneNumber);
    }

    @Test
    void testNoSettings() {
        when(dataManager.getMockDelegate().load(any(LoadContext.class)))
                .thenReturn(null);

        User u = container.metadata().create(User.class);
        String phoneNumber = phoneManager.getPhoneNumber(u);
        assertEquals("", phoneNumber);
    }

}