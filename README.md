# data-manager-mock-test

This is a sample project demonstrating mocking DataManager in CUBA middleware tests that instantiate the whole Spring (core module) context.

Noteable files:
- `build.gradle` - mockito library dependency.
- `com.company.dmocks.sys.MyDataManagerBean` - extended DataManager bean delegating some of its methods to a mock object.
- `mocks-test-app.properties` and `mocks-spring.xml` - configuration files necessary to override DataManager bean with MyDataManagerBean.
- `com.company.dmocks.DmmocksMockedDMTestContainer` - second TestContainer class for tests that need DataManager mocking.
- `com.company.dmocks.core.MockedPhoneManagerTest` - class testing logic of the PhoneManager business bean with mocks

Business logic:
- `com.company.dmocks.entity.PhoneSettings` - entity associating to User.
- `com.company.dmocks.core.PhoneManager` - Spring bean with business logic that we are testing.

Normal test doing the same testing without mocking the DataManager - `com.company.dmocks.core.PhoneManagerTest`.
