package com.todolist.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.todolist.dynamodb.TaskDao;
import com.todolist.dynamodb.models.Task;
import com.todolist.models.requests.DeleteTaskRequest;
import com.todolist.models.results.DeleteTaskResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DeleteTaskActivity implements RequestHandler<DeleteTaskRequest, DeleteTaskResult> {
    private static final Logger log = LogManager.getLogger(DeleteTaskActivity.class);
    private final TaskDao taskDao;

    public DeleteTaskActivity(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public DeleteTaskResult handleRequest(final DeleteTaskRequest request, Context context) {
        log.info("Received DeleteTaskRequest: {}", request);

        // Validate request parameters
        if (request == null || request.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("UserId cannot be null or empty.");
        }

        List<Task> tasks;
        if (request.getTaskId() != null && !request.getTaskId().trim().isEmpty()) {
            tasks = taskDao.findTasksByPartialId(request.getUserId(), request.getTaskId());
            if (tasks.isEmpty()) {
                throw new RuntimeException(String.format("No tasks found with the partial ID %s for user %s", request.getTaskId(), request.getUserId()));
            } else if (tasks.size() > 1) {
                throw new IllegalArgumentException("Multiple tasks found with the given partial ID. Please provide a more specific partial ID.");
            }
        } else {
            throw new IllegalArgumentException("Partial taskId is required for deleting tasks.");
        }

        // Assuming tasks list has exactly one element at this point
        Task task = tasks.get(0);

        // Delete the task
        boolean success = taskDao.deleteTask(task.getUserId(), task.getTaskId());

        // Prepare and return the result
        return DeleteTaskResult.builder()
                .withSuccess(success)
                .build();
    }
}
