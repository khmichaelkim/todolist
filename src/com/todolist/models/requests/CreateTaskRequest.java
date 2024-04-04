package com.todolist.models.requests;

import com.todolist.models.Priority;

import java.util.Objects;

public class CreateTaskRequest {

    private String userId;
    private String content;
    private String dueDate;
    private Priority priority;

    public CreateTaskRequest() {
    }

    public CreateTaskRequest(String userId, String content, String dueDate, Priority priority) {
        this.userId = userId;
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
        CreateTaskRequest that = (CreateTaskRequest) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(content, that.content) &&
                Objects.equals(dueDate, that.dueDate) &&
                Objects.equals(priority, that.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, content, dueDate, priority);
    }

    @Override
    public String toString() {
        return "CreateTaskRequest{" +
                "userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
