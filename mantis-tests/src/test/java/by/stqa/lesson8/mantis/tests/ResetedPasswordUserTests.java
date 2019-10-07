package by.stqa.lesson8.mantis.tests;

import by.stqa.lesson8.mantis.appmanager.HttpSession;
import by.stqa.lesson8.mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class ResetedPasswordUserTests extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testResetPasswordUser() throws Exception {
        long now = System.currentTimeMillis();
        String user =  String.format("user%s", now);
        String email = String.format("user%s@localhost.localdomain", now);
        String password = "passw";
        registrationUser(user, email, password);

        app.reset().goToLoginPage();
        app.reset().loginToMantis( "username", "administrator");
        app.reset().loginToMantis( "password", "secret");
        app.reset().controlUsers();
        app.reset().selectUser(user);
        app.reset().resetPassword();

        password = "pass";
        setNewPassword(user, email, password);

        HttpSession session = app.newSession();
        assertTrue(session.login(user, password));
    }

    private void setNewPassword(String user, String email, String password) {
        String confirmLinkLast = getLink(email);
        app.reset().password(confirmLinkLast, password, user);
    }

    private void registrationUser(String user, String email, String password) {
        app.registration().start(user, email);
        String confirmationLink = getLink(email);
        app.registration().finish(confirmationLink, password, user);
    }

    private String getLink(String email) {
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        return findConfirmationLink(mailMessages, email);
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        List<MailMessage> emails = mailMessages.stream().filter((m) -> m.to.equals(email)).collect(Collectors.toList());
        MailMessage lastMessage = emails.get(emails.size() - 1);
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(lastMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

}
