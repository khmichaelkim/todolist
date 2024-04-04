package com.todolist.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.todolist.dynamodb.UserDao;
import com.todolist.dynamodb.models.User;
import com.todolist.models.UserModel;
import com.todolist.models.requests.GetUserRequest;
import com.todolist.models.results.GetUserResult;
import com.todolist.converters.ModelConverter;

public class GetUserActivity implements RequestHandler<GetUserRequest, GetUserResult> {

    private final UserDao userDao;

    public GetUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public GetUserResult handleRequest(GetUserRequest request, Context context) {
        if (request == null || request.getUserId() == null || request.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("Request or userId cannot be null or empty");
        }

        String userId = request.getUserId();
        User user = userDao.getUser(userId);

        if (user == null) {
            throw new RuntimeException("User not found with userId: " + userId);
        }

        UserModel userModel = new ModelConverter().toUserModel(user);

        return GetUserResult.builder()
                .withUser(userModel)
                .build();
    }
}
