package javaworkflowservice.createcomponent;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface ComponentCreationActivity {
    @ActivityMethod
    void callProjectManager(String componentName);

    @ActivityMethod
    void callRudder(String componentName);

    @ActivityMethod
    void callCicd(String componentName);
}
