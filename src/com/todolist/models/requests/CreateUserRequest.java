package com.todolist.models.requests;

import java.time.Instant;
import java.util.Objects;

public class CreateUserRequest {

    private String userId;
    private String email;
    // The createdAt field is maintained in the class but not populated directly by the request payload.
    private Instant createdAt;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String userId, String email) {
        this.userId = userId;
        this.email = email;
        // Do not initialize createdAt here as it will be set within the application
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static final class Builder {
        private String userId;
        private String email;

        private Builder() {}

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CreateUserRequest build() {
            // Note: createdAt is not included in the builder
            return new CreateUserRequest(userId, email);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUserRequest that = (CreateUserRequest) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(email, that.email) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, createdAt);
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
