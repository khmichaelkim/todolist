package com.todolist.models;

import java.util.Objects;

public class TaskModel {

    private String userId; // Identifier for the user who owns the task
    private String taskId; // Unique identifier for the task
    private String content; // Content of the task
    private String dueDate; // Due date for the task
    private Priority priority; // Priority of the task

    public TaskModel() {
    }

    public TaskModel(String userId, String taskId, String content, String dueDate, Priority priority) {
        this.userId = userId;
        this.taskId = taskId;
        this.content = content;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    // Getters and Setters
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskModel taskModel = (TaskModel) o;
        return Objects.equals(userId, taskModel.userId) &&
                Objects.equals(taskId, taskModel.taskId) &&
                Objects.equals(content, taskModel.content) &&
                Objects.equals(dueDate, taskModel.dueDate) &&
                Objects.equals(priority, taskModel.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, taskId, content, dueDate, priority);
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "userId='" + userId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", content='" + content + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }

    public static class Builder {
        private String userId;
        private String taskId;
        private String content;
        private String dueDate;
        private Priority priority;

        public Builder() {
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withTaskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withDueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder withPriority(Priority priority) {
            this.priority = priority;
            return this;
        }

        public TaskModel build() {
            return new TaskModel(userId, taskId, content, dueDate, priority);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
