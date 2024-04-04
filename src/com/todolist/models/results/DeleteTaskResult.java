package com.todolist.models.results;

public class DeleteTaskResult {

    private boolean success;

    public DeleteTaskResult() {
    }

    public DeleteTaskResult(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private boolean success;

        private Builder() {
        }

        public Builder withSuccess(boolean success) {
            this.success = success;
            return this;
        }

        public DeleteTaskResult build() {
            return new DeleteTaskResult(success);
        }
    }

    @Override
    public String toString() {
        return "DeleteTaskResult{" +
                "success=" + success +
                '}';
    }
}
