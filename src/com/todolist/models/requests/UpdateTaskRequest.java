package com.todolist.models.requests;

import com.todolist.models.Priority;

import java.time.LocalDate;

public class UpdateTaskRequest {

    private String userId;
    private String taskId;
    private String content;
    private LocalDate dueDate;
    private Priority priority;

    public UpdateTaskRequest() {
    }

    public UpdateTaskRequest(String userId, String taskId, String content, LocalDate dueDate, Priority priority) {
        this.userId = userId;
        this.taskId = taskId;
        this.content = content;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "UpdateTaskRequest{" +
                "userId='" + userId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", content='" + content + '\'' +
                ", dueDate=" + (dueDate != null ? dueDate.toString() : "null") +
                ", priority='" + priority + '\'' +
                '}';
    }
}
