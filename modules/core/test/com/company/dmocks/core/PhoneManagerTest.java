package com.company.dmocks.core;

import com.company.dmocks.DmmocksTestContainer;
import com.company.dmocks.entity.PhoneSettings;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.Group;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.testsupport.TestContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.*;
// See https://doc.cuba-platform.com/manual-7.2/integration_tests_mw.html

class PhoneManagerTest {

    @RegisterExtension
    static TestContainer container = DmmocksTestContainer.Common.INSTANCE;
    static DataManager dataManager;

    private static PhoneManager phoneManager;

    private static User u1, u2;
    private static PhoneSettings p1;

    private static Group group;

    @BeforeAll
    static void beforeAll() {
        dataManager = AppBeans.get(DataManager.class);
        phoneManager = AppBeans.get(PhoneManager.class);

        group = dataManager.load(Group.class).list().get(0);
    }

    @AfterAll
    static void afterAll() {
        container.persistence().runInTransaction(em -> {
            em.createNativeQuery("delete from DMMOCKS_PHONE_SETTINGS where ID = ?")
                    .setParameter(1, p1.getId())
                    .executeUpdate();

            em.createNativeQuery("delete from SEC_USER_ROLE where USER_ID in (?, ?)")
                    .setParameter(1, u1.getId())
                    .setParameter(2, u2.getId())
                    .executeUpdate();

            em.createNativeQuery("delete from SEC_USER where ID in (?, ?)")
                    .setParameter(1, u1.getId())
                    .setParameter(2, u2.getId())
                    .executeUpdate();
        });

    }

    @Test
    void myTestMethod() {
        u1 = container.metadata().create(User.class);
        u1.setLogin("U1");
        u1.setName("U1");
        u1.setGroup(group);

        u2 = container.metadata().create(User.class);
        u2.setLogin("U2");
        u2.setName("U2");
        u2.setGroup(group);

        p1 = container.metadata().create(PhoneSettings.class);
        p1.setPhoneNumber("+44155");
        p1.setUser(u1);

        dataManager.commit(u1, u2, p1);

        String phoneNumber = phoneManager.getPhoneNumber(u1);
        assertEquals("+44155", phoneNumber);

        phoneNumber = phoneManager.getPhoneNumber(u2);
        assertEquals("", phoneNumber);
    }
}