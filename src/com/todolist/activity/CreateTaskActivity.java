package com.todolist.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.todolist.converters.ModelConverter;
import com.todolist.dynamodb.TaskDao;
import com.todolist.dynamodb.models.Task;
import com.todolist.models.TaskModel;
import com.todolist.models.requests.CreateTaskRequest;
import com.todolist.models.results.CreateTaskResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreateTaskActivity implements RequestHandler<CreateTaskRequest, CreateTaskResult> {
    private final Logger log = LogManager.getLogger();
    private final TaskDao taskDao;

    public CreateTaskActivity(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public CreateTaskResult handleRequest(final CreateTaskRequest createTaskRequest, Context context) {
        log.info("Received CreateTaskRequest: {}", createTaskRequest);

        if (createTaskRequest == null) {
            throw new IllegalArgumentException("Request cannot be null.");
        }
        if (createTaskRequest.getUserId() == null || createTaskRequest.getUserId().trim().isEmpty()) {
            throw new IllegalArgumentException("UserID cannot be null or empty.");
        }
        if (createTaskRequest.getContent() == null || createTaskRequest.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty.");
        }

        Task task = new Task();
        task.setUserId(createTaskRequest.getUserId());
        task.setTaskId(taskDao.generateTaskId());
        task.setContent(createTaskRequest.getContent());
        task.setDueDate(createTaskRequest.getDueDate() != null ? LocalDate.parse(createTaskRequest.getDueDate(), DateTimeFormatter.ISO_LOCAL_DATE).toString() : null);
        task.setPriority(createTaskRequest.getPriority());

        taskDao.saveTask(task);

        TaskModel taskModel = new ModelConverter().toTaskModel(task);

        return CreateTaskResult.builder()
                .withTask(taskModel)
                .build();
    }
}
