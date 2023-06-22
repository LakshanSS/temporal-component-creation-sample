package worker.createcomponent;

import javaworkflowservice.createcomponent.ComponentCreationActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ComponentCreationActivityImpl implements ComponentCreationActivity {

    static final String projectUrl = "http://localhost:9091/projects";
    static final String rudderUrl = "http://localhost:9092/rudder";
    static final String cicdUrl = "http://localhost:9093/cicd";

    @Override
    public void callProjectManager(String componentName) {
        String resp = callProjectManagerService(componentName);
        System.out.println(resp + componentName);
    }

    @Override
    public void callRudder(String componentName) {
        String resp = callRudderService(componentName);
        System.out.println(resp + componentName);
    }

    @Override
    public void callCicd(String componentName) {
        String resp = callCicdService(componentName);
        System.out.println(resp + componentName);
    }

    public static String callProjectManagerService(String compName) {
        System.out.println("Calling project manager service");

        try {
            URL url = new URL(projectUrl + "/component?componentName=" + compName);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            System.out.println(status);
            System.out.println(content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("error in calling project manager");
        }

        return "called project manager service";
    }

    public static String callCicdService(String compName) {
        System.out.println("Calling cicd service");
        try {
            URL url = new URL(cicdUrl + "/component?componentName=" + compName);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            System.out.println(status);
            System.out.println(content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("error in calling cicd service");
        }

        return "called cicd service ";
    }

    public static String callRudderService(String compName) {
        System.out.println("Calling rudder service");

        try {
            URL url = new URL(rudderUrl + "/component?componentName=" + compName);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            System.out.println(status);
            System.out.println(content);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("error in calling cicd rudder");
        }
        return "called rudder service ";
    }
}
