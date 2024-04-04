package com.todolist.models.requests;

public class DeleteTaskRequest {

    private String userId;
    private String taskId;

    public DeleteTaskRequest() {
    }

    public DeleteTaskRequest(String userId, String taskId) {
        this.userId = userId;
        this.taskId = taskId;
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

    @Override
    public String toString() {
        return "DeleteTaskRequest{" +
                "userId='" + userId + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
