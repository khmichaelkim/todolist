package com.todolist.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.todolist.converters.ModelConverter;
import com.todolist.dynamodb.TaskDao;
import com.todolist.dynamodb.models.Task;
import com.todolist.models.Priority;
import com.todolist.models.TaskModel;
import com.todolist.models.requests.UpdateTaskRequest;
import com.todolist.models.results.UpdateTaskResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class UpdateTaskActivity implements RequestHandler<UpdateTaskRequest, UpdateTaskResult> {
    private static final Logger log = LogManager.getLogger(UpdateTaskActivity.class);
    private final TaskDao taskDao;

    public UpdateTaskActivity(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public UpdateTaskResult handleRequest(UpdateTaskRequest request, Context context) {
        log.info("Received UpdateTaskRequest: {}", request);

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
            throw new IllegalArgumentException("Partial taskId is required for updating tasks.");
        }

        // Assuming tasks list has exactly one element at this point
        Task task = tasks.get(0);

        // Update task details from request
        task.setContent(request.getContent());
        if (request.getDueDate() != null) {
            String dueDateString = request.getDueDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
            task.setDueDate(dueDateString);
        }
        if (request.getPriority() != null) {
            // Convert the input value to uppercase before parsing as enum
            String priorityValue = String.valueOf(request.getPriority()).toUpperCase();
            Priority priority = Priority.valueOf(priorityValue);
            task.setPriority(priority);
        }

        // Save or update the task in the database
        taskDao.saveTask(task);

        TaskModel taskModel = new ModelConverter().toTaskModel(task);

        return UpdateTaskResult.builder()
                .withTask(taskModel)
                .build();
    }
}
