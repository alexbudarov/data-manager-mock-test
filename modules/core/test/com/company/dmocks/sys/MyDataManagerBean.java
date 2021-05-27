package com.company.dmocks.sys;

import com.haulmont.cuba.core.app.DataManagerBean;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import org.mockito.Mockito;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Replaces normal DataManager implementation.
 * Delegates loading data to the mock object.
 */
public class MyDataManagerBean extends DataManagerBean {

    private DataManager mock = Mockito.mock(DataManager.class);
    @Override
    public <E extends Entity> List<E> loadList(LoadContext<E> context) {
        return mock.loadList(context);
    }

    @Nullable
    @Override
    public <E extends Entity> E load(LoadContext<E> context) {
        return mock.load(context);
    }

    public DataManager getMockDelegate() {
        return mock;
    }
}
