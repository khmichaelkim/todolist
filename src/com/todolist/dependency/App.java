package com.todolist.dependency;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.todolist.activity.*;
import com.todolist.dynamodb.TaskDao;
import com.todolist.dynamodb.UserDao;

public class App {
    private DynamoDBMapper dynamoDBMapper;

    public CreateUserActivity provideCreateUserActivity() {
        return new CreateUserActivity(provideUserDao());
    }

    public GetUserActivity provideGetUserActivity() {
        return new GetUserActivity(provideUserDao());
    }

    public CreateTaskActivity provideCreateTaskActivity() {
        return new CreateTaskActivity(provideTaskDao());
    }

    public GetTaskActivity provideGetTaskActivity() {
        return new GetTaskActivity(provideTaskDao());
    }

    public UpdateTaskActivity provideUpdateTaskActivity() {
        return new UpdateTaskActivity(provideTaskDao());
    }

    public DeleteTaskActivity provideDeleteTaskActivity() {
        return new DeleteTaskActivity(provideTaskDao());
    }

    private UserDao provideUserDao() {
        return new UserDao(provideDynamoDBMapper());
    }

    private TaskDao provideTaskDao() {
        return new TaskDao(provideDynamoDBMapper());
    }

    private DynamoDBMapper provideDynamoDBMapper() {
        if (null == dynamoDBMapper) {
            AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                    .withRegion(Regions.US_WEST_2)
                    .build();
            dynamoDBMapper = new DynamoDBMapper(client);
        }
        return dynamoDBMapper;
    }
}
