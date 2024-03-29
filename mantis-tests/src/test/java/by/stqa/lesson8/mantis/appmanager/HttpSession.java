package by.stqa.lesson8.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession{
    private CloseableHttpClient httpClient;
    private  ApplicationManager app;

    public  HttpSession(ApplicationManager app){
        this.app = app;
        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    public boolean login(String username, String password) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
        List<NameValuePair> parama = new ArrayList<NameValuePair>();
        parama.add(new BasicNameValuePair("username", username));
        parama.add(new BasicNameValuePair("password", password));
        parama.add(new BasicNameValuePair("secure_session", "on"));
        parama.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(parama));
        CloseableHttpResponse response = httpClient.execute(post);
        String body = geTextFrom(response);
        return body.contains(String.format("<a href=\"/mantisbt-2.22.0/account_page.php\">%s ( " + username + " ) </a>", username));

    }

    private String geTextFrom(CloseableHttpResponse response) throws IOException {
        try{
            return EntityUtils.toString(response.getEntity());
        }finally{
            response.close();
        }
    }

    public boolean isLoggedInAs(String username) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
        CloseableHttpResponse response = httpClient.execute(get);
        String body = geTextFrom(response);
        return body.contains(String.format("<a href=\"/mantisbt-2.22.0/account_page.php\">%s</a>", username));
    }
}