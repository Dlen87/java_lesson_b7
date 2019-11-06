package by.stqa.pft.rest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{
    @BeforeTest
    public void checkStatusIssue() throws IOException {
        int idIssue = getIdIssue();
        // int idIssue = getIdIssueClosed(); // for find some closed or resolved issue
        skipIfNotFixed(idIssue);
    }

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("Test dlen 2").withDescription("Description dlen 2");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

}
