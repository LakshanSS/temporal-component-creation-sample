import ballerina/http;
import ballerina/log;
import ballerina/time;

service /cicd on new http:Listener(9093) {

    // resource function get greeting() returns string {
    //     return "Hello, World!";
    // }

    resource function get component(string componentName) returns string|error? {
        time:Utc startedTime = time:utcNow();
        log:printInfo("startedTime " + startedTime.toString());
        
        log:printInfo("cicd: " + componentName);

        time:Utc endTime = time:utcNow();
        log:printInfo("endTime " + endTime.toString());

        time:Seconds duration = time:utcDiffSeconds(endTime, startedTime);
        log:printInfo("duration " + duration.toString());
        
        return "Success from cicd!";
    }
}
