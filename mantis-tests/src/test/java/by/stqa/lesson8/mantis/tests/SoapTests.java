package by.stqa.lesson8.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.*;
import by.stqa.lesson8.mantis.model.Issue;
import by.stqa.lesson8.mantis.model.Project;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{
    @BeforeMethod
    public void checkStatusIssue() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = app.soap().getMantisConnect();
        Set<Project> projectSet = app.soap().getProjects();
        IssueData[] issueData = mc.mc_project_get_issues(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"),
                BigInteger.valueOf(projectSet.iterator().next().getId()), BigInteger.ONE, BigInteger.valueOf(10));
        skipIfNotFixed(issueData[0].getId().intValue());

    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue NEW").withDescription("Test issue description NEW")
                .withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());

    }

    @Test(enabled = false)
    public void testGetProgect() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

}
