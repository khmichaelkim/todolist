package com.todolist.models.requests;

import java.util.Objects;

public class GetTaskRequest {

    private String userId;
    private String taskId; // Optional: for fetching a specific task

    public GetTaskRequest() {
    }

    public GetTaskRequest(String userId, String taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() { return taskId; }

    public void setTaskId(String taskId) { this.taskId = taskId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetTaskRequest)) return false;
        GetTaskRequest that = (GetTaskRequest) o;
        return Objects.equals(userId, that.userId) && Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, taskId);
    }

    @Override
    public String toString() {
        return "GetTaskRequest{" +
                "userId='" + userId + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
