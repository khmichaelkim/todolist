package com.todolist.models;

import java.util.Objects;

public class UserModel {

    private String userId;
    private String email;
    private String createdAt;

    // Constructors
    public UserModel(String userId, String email, String createdAt) {
        this.userId = userId;
        this.email = email;
        this.createdAt = createdAt;
    }

    public UserModel() {
    }

    // Builder Pattern for easier object creation
    public static class Builder {
        private String userId;
        private String email;
        private String createdAt;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserModel build() {
            return new UserModel(userId, email, createdAt);
        }
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    // Override equals, hashCode, and toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel)) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(userId, userModel.userId) &&
                Objects.equals(email, userModel.email) &&
                Objects.equals(createdAt, userModel.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, createdAt);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    // Static method to get a Builder instance
    public static Builder builder() {
        return new Builder();
    }
}
