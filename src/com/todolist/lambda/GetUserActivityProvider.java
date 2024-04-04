package com.todolist.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.todolist.dependency.App;
import com.todolist.models.requests.GetUserRequest;
import com.todolist.models.results.GetUserResult;

public class GetUserActivityProvider implements RequestHandler<GetUserRequest, GetUserResult> {

    private static App app;

    public GetUserActivityProvider() {
    }

    @Override
    public GetUserResult handleRequest(final GetUserRequest getUserRequest, Context context) {
        return getApp().provideGetUserActivity().handleRequest(getUserRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }
}
