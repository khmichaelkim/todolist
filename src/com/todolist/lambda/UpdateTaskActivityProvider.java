package com.todolist.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.todolist.dependency.App;
import com.todolist.models.requests.UpdateTaskRequest;
import com.todolist.models.results.UpdateTaskResult;

public class UpdateTaskActivityProvider implements RequestHandler<UpdateTaskRequest, UpdateTaskResult> {

    private static App app;

    public UpdateTaskActivityProvider() {
    }

    @Override
    public UpdateTaskResult handleRequest(UpdateTaskRequest updateTaskRequest, Context context) {
        // Delegate the request to UpdateTaskActivity through the App class
        return getApp().provideUpdateTaskActivity().handleRequest(updateTaskRequest, context);
    }

    private static App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }
}
