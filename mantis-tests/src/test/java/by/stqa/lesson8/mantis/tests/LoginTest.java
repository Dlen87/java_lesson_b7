package by.stqa.lesson8.mantis.tests;


import by.stqa.lesson8.mantis.appmanager.HttpSession;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTest  extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator" , "secret"));
        assertTrue(session.isLoggedInAs("administrator" ));
    }

}
