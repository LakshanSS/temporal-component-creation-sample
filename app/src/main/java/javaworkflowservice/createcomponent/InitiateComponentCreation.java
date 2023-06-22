package javaworkflowservice.createcomponent;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

public class InitiateComponentCreation {
    public static void main(String[] args) throws Exception {
//        excecuteCreateCompWorkflow("testComponent1");
//        System.exit(0);
    }

    public static void excecuteCreateCompWorkflow(String compName) throws Exception {

        // WorkflowServiceStubs is a gRPC stubs wrapper that talks to the local Docker instance of the Temporal server.
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(TaskQueues.COMPONENT_CREATION_TASK_QUEUE)
                // A WorkflowId prevents this it from having duplicate instances, remove it to duplicate.
                .setWorkflowId("component-creation-workflow")
                .build();
        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);
        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        ComponentCreationWorkflow workflow = client.newWorkflowStub(ComponentCreationWorkflow.class, options);

        // Asynchronous execution. This process will exit after making this call.
        WorkflowExecution we = WorkflowClient.start(workflow::createComponent, compName);
        System.out.printf("\nComponent: %s is being created\n", compName);
        System.out.printf("\nWorkflowID: %s RunID: %s", we.getWorkflowId(), we.getRunId());
    }
}
