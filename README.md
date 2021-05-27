# data-manager-mock-test

This is a sample project demonstrating mocking DataManager in CUBA middleware tests.

Noteable files:
- `build.gradle` - mockito library dependency.
- `com.company.dmocks.sys.MyDataManagerBean` - extended DataManager bean delegating some of its methods to a mock object.
- `mocks-test-app.properties` and `mocks-spring.xml` - configuration files necessary to override DataManager bean with MyDataManagerBean.
- `com.company.dmocks.DmmocksMockedDMTestContainer` - base class for tests that need DataManager mocking.
- `com.company.dmocks.core.MockedPhoneManagerTest` - class testing logic of the PhoneManager business bean with mocks

Normal test doing the same testing without mocking the DataManager - `com.company.dmocks.core.PhoneManagerTest`.
