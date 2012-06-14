package tests;

import org.junit.After;
import org.junit.Before;
import play.test.FakeApplication;
import play.test.Helpers;

public class BaseTest {
    public static FakeApplication app;

    @Before
    public void startApp() {
        app = Helpers.fakeApplication(Helpers.inMemoryDatabase());
        Helpers.start(app);
    }

    @After
    public void stopApp() {
        Helpers.stop(app);
    }


}
