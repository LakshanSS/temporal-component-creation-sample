import ballerina/http;
import ballerina/log;
import ballerina/time;

const string projectUrl = "http://localhost:9091/projects";
const string rudderUrl = "http://localhost:9092/rudder";
const string cicdUrl = "http://localhost:9093/cicd";
const string wfServiceUrl = "http://localhost:9099/workflow";

isolated service /graphql on new http:Listener(9090) {

    isolated resource function post component(Component component) returns string|error? {
        time:Utc startedTime = time:utcNow();
        log:printInfo("startedTime " + startedTime.toString());
        log:printInfo(component.toString());

        // Call project manager
        http:Client projectClient = check new (projectUrl);
        string projectResp = check projectClient->get("/component?componentName=" + component.componentName);
        log:printInfo("projectResp: " + projectResp);

        // Call rudder
        http:Client rudderClient = check new (rudderUrl);
        string rudderResp = check rudderClient->get("/component?componentName=" + component.componentName);
        log:printInfo("rudderResp: " + rudderResp);

        // Call cicd
        http:Client cicdClient = check new (cicdUrl);
        string cicdResp = check cicdClient->get("/component?componentName=" + component.componentName);
        log:printInfo("cicdResp: " + cicdResp);

        time:Utc endTime = time:utcNow();
        log:printInfo("endTime " + endTime.toString());

        time:Seconds duration = time:utcDiffSeconds(endTime, startedTime);
        log:printInfo("duration " + duration.toString());
        return "cp-graphql: Component creation v1. Duration: " + duration.toString() + " seconds";
    }

    isolated resource function post component/v2(Component component) returns string|error? {
        time:Utc startedTime = time:utcNow();
        log:printInfo("startedTime " + startedTime.toString());
        log:printInfo(component.toString());

        // Call workflow service
        http:Client workflowClient = check new (wfServiceUrl);
        string wfServiceResp = check workflowClient->get("/component/" + component.componentName);
        log:printInfo("wfServiceResp: " + wfServiceResp);

        time:Utc endTime = time:utcNow();
        log:printInfo("endTime " + endTime.toString());

        time:Seconds duration = time:utcDiffSeconds(endTime, startedTime);
        log:printInfo("duration " + duration.toString());
        return "cp-graphql: Component creation v2. Duration: " + duration.toString() + " seconds";
    }
}

public type Component record {
    string componentName;
    string orgUuid;
    string projectUuid;
};
