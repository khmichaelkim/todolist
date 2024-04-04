package com.todolist.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.todolist.converters.ModelConverter;
import com.todolist.dynamodb.UserDao;
import com.todolist.dynamodb.models.User;
import com.todolist.models.UserModel;
import com.todolist.models.requests.CreateUserRequest;
import com.todolist.models.results.CreateUserResult;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.Instant;

public class CreateUserActivity implements RequestHandler<CreateUserRequest, CreateUserResult> {
    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    public CreateUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public CreateUserResult handleRequest(final CreateUserRequest createUserRequest, Context context) {
        log.info("Received CreateUserRequest {}", createUserRequest);

        // Validate the CreateUserRequest and its fields
        if (createUserRequest == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (createUserRequest.getUserId() == null || createUserRequest.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("userId cannot be null or empty");
        }
        if (createUserRequest.getEmail() == null || createUserRequest.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }

        String userId = createUserRequest.getUserId();
        String email = createUserRequest.getEmail();

        // Basic validation of userId for alphanumerics
        if (!userId.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Invalid username format.");
        }

        // UserId uniqueness check
        if (userDao.getUser(userId) != null) {
            throw new IllegalArgumentException("User with the given userId already exists.");
        }

        // Basic email validation
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format.");
        }

        Instant createdAt = Instant.now();
        User user = new User();
        user.setUserId(userId);
        user.setEmail(email);
        user.setCreatedAt(createdAt);

        userDao.saveUser(user);

        UserModel userModel = new ModelConverter().toUserModel(user);

        return CreateUserResult.builder()
                .withUser(userModel)
                .build();
    }

}
