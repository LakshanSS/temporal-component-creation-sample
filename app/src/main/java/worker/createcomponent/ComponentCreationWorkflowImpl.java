package worker.createcomponent;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import io.temporal.common.RetryOptions;
import javaworkflowservice.createcomponent.ComponentCreationActivity;
import javaworkflowservice.createcomponent.ComponentCreationWorkflow;

import java.time.Duration;

public class ComponentCreationWorkflowImpl implements ComponentCreationWorkflow {
    // RetryOptions specify how to automatically handle retries when Activities fail.
    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100))
            .setBackoffCoefficient(2)
            .setMaximumAttempts(500)
            .build();
    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            // Timeout options specify when to automatically timeout Activities if the process is taking too long.
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            // Optionally provide customized RetryOptions.
            // Temporal retries failures by default, this is simply an example.
            .setRetryOptions(retryoptions)
            .build();

    private final ComponentCreationActivity compCreation = Workflow.newActivityStub(ComponentCreationActivity.class,
            defaultActivityOptions);

    @Override
    public void createComponent(String componentName) {
        compCreation.callProjectManager(componentName);
        compCreation.callRudder(componentName);
        compCreation.callCicd(componentName);
    }
}
