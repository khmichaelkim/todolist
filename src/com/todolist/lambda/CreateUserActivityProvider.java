package com.todolist.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.todolist.dependency.App;
import com.todolist.models.requests.CreateUserRequest;
import com.todolist.models.results.CreateUserResult;

public class CreateUserActivityProvider implements RequestHandler<CreateUserRequest, CreateUserResult> {

    private static App app;

    public CreateUserActivityProvider(){
    }

    @Override
    public CreateUserResult handleRequest(final CreateUserRequest createUserRequest, Context context) {
        return getApp().provideCreateUserActivity().handleRequest(createUserRequest, context);
    }

    private App getApp() {
        if (app == null) {
            app = new App();
        }
        return app;
    }
}
