package com.todolist.models.requests;

import java.util.Objects;

public class GetUserRequest {
    private String userId;

    public GetUserRequest() {
    }

    public GetUserRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetUserRequest)) return false;
        GetUserRequest that = (GetUserRequest) o;
        return Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    @Override
    public String toString() {
        return "GetUserRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
