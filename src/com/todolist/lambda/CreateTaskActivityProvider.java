package com.todolist.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.todolist.dependency.App;
import com.todolist.models.requests.CreateTaskRequest;
import com.todolist.models.results.CreateTaskResult;

public class CreateTaskActivityProvider implements RequestHandler<CreateTaskRequest, CreateTaskResult> {

    private static App app;

    public CreateTaskActivityProvider() {
    }

    @Override
    public CreateTaskResult handleRequest(final CreateTaskRequest createTaskRequest, Context context) {
        return getApp().provideCreateTaskActivity().handleRequest(createTaskRequest, context);
    }

    private static App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }
}
