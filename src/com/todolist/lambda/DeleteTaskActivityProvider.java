package com.todolist.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.todolist.dependency.App;
import com.todolist.models.requests.DeleteTaskRequest;
import com.todolist.models.results.DeleteTaskResult;

public class DeleteTaskActivityProvider implements RequestHandler<DeleteTaskRequest, DeleteTaskResult> {

    private static App app;

    public DeleteTaskActivityProvider() {
    }

    @Override
    public DeleteTaskResult handleRequest(DeleteTaskRequest deleteTaskRequest, Context context) {
        return getApp().provideDeleteTaskActivity().handleRequest(deleteTaskRequest, context);
    }

    private static App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }
}
