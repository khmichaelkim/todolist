package com.todolist.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.todolist.dependency.App;
import com.todolist.models.requests.GetTaskRequest;
import com.todolist.models.results.GetTaskResult;

public class GetTaskActivityProvider implements RequestHandler<GetTaskRequest, GetTaskResult> {

    private static App app;

    public GetTaskActivityProvider() {
    }

    @Override
    public GetTaskResult handleRequest(final GetTaskRequest getTaskRequest, Context context) {
        return getApp().provideGetTaskActivity().handleRequest(getTaskRequest, context);
    }

    private static App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }
}
