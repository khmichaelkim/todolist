package com.todolist.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.todolist.converters.PriorityConverter;
import com.todolist.models.Priority;

@DynamoDBTable(tableName = "Tasks")
public class Task {
    private String userId; // Identifier for the user who owns the task
    private String taskId; // Unique identifier for the task, to be auto-generated
    private String content; // Content of the task
    private String dueDate; // ISO-8601 String format for simplicity
    private Priority priority;

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBRangeKey(attributeName = "taskId")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @DynamoDBAttribute(attributeName = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @DynamoDBAttribute(attributeName = "dueDate")
    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @DynamoDBTypeConverted(converter = PriorityConverter.class)
    @DynamoDBAttribute(attributeName = "priority")
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
