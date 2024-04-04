package com.todolist.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.todolist.dynamodb.TaskDao;
import com.todolist.dynamodb.models.Task;
import com.todolist.models.TaskModel;
import com.todolist.models.requests.GetTaskRequest;
import com.todolist.models.results.GetTaskResult;
import com.todolist.converters.ModelConverter;

import java.util.List;
import java.util.stream.Collectors;

public class GetTaskActivity implements RequestHandler<GetTaskRequest, GetTaskResult> {

    private final TaskDao taskDao;

    public GetTaskActivity(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public GetTaskResult handleRequest(GetTaskRequest request, Context context) {
        if (request == null || request.getUserId() == null || request.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("Request or userId cannot be null or empty");
        }

        String userId = request.getUserId();
        // Check if user exists
        if (!taskDao.userExists(userId)) {
            throw new IllegalArgumentException("User does not exist");
        }

        List<Task> tasks = taskDao.getTasksByUserId(userId);

        List<TaskModel> taskModels = tasks.stream()
                .map(task -> new ModelConverter().toTaskModel(task))
                .collect(Collectors.toList());

        return GetTaskResult.builder()
                .withTasks(taskModels)
                .build();
    }
}
