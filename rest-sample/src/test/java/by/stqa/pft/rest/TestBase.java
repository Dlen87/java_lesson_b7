package by.stqa.pft.rest;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    public boolean isIssueOpen(int issueId) throws IOException {
        JsonElement issue = getIssueFromBugify(issueId);
        boolean state = false;
        String state_name = issue.getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();
        if (state_name.equals("Open")){
            state = true;
        }
        return state;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public Set<Issue> getIssues() throws IOException {
        JsonElement issues = getIssuesFromBugify();
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private JsonElement getIssuesFromBugify() throws IOException {
        String json = getExecuter().execute(Request.Get("https://bugify.stqa.ru/api/issues.json?limit=500")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issues");
    }

    private JsonElement getIssueFromBugify(int idIssue) throws IOException {
        String json = getExecuter().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + idIssue + ".json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issues");
    }

    public int getIdIssue() throws IOException {
        JsonElement issues = getIssuesFromBugify();
        JsonElement next = issues.getAsJsonArray().iterator().next();
        return next.getAsJsonObject().get("id").getAsInt();
    }

    public int getIdIssueClosed() throws IOException {
        int idClosedIssue = 0;
        JsonElement issues = getIssuesFromBugify();
        for (JsonElement a: issues.getAsJsonArray()){
            String state_name = a.getAsJsonObject().get("state_name").getAsString();
            if ((state_name.equals("Closed") || (state_name.equals("Resolved")))){
                idClosedIssue = a.getAsJsonObject().getAsJsonPrimitive("id").getAsInt();
                break;
            }
        }
        return idClosedIssue;
    }

    public Executor getExecuter() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecuter().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription()))).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

}
