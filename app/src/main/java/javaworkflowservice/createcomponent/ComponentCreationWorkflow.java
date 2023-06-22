package javaworkflowservice.createcomponent;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface ComponentCreationWorkflow {
    // The Workflow method is called by the initiator either via code or CLI.
    @WorkflowMethod
    void createComponent(String componentName);
}
