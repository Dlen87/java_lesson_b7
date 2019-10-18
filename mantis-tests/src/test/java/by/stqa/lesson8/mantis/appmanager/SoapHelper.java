package by.stqa.lesson8.mantis.appmanager;


import biz.futureware.mantis.rpc.soap.client.*;
import by.stqa.lesson8.mantis.model.Issue;
import by.stqa.lesson8.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper extends HelperBase{

    public SoapHelper(ApplicationManager app){
        super(app);
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
                .collect(Collectors.toSet());
    }

    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                    .getMantisConnectPort(new URL("http://localhost/mantisbt-2.22.0/api/soap/mantisconnect.php"));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories("administrator", "root",
                                                                    BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setCategory(categories[0]);
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        BigInteger issueId = mc.mc_issue_add("administrator", "root", issueData);
        IssueData createdIssue = mc.mc_issue_get("administrator", "root", issueId);
        return new Issue().withSummary(createdIssue.getSummary()).withDescription(createdIssue.getDescription())
                .withProject(new Project().withId(createdIssue.getProject().getId().intValue())
                                          .withName(createdIssue.getProject().getName()));
    }
}