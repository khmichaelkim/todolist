package com.todolist.models.results;

import com.todolist.models.TaskModel;

public class UpdateTaskResult {

    private TaskModel task;

    public UpdateTaskResult() {
    }

    public UpdateTaskResult(TaskModel task) {
        this.task = task;
    }

    public TaskModel getTask() {
        return task;
    }

    public void setTask(TaskModel task) {
        this.task = task;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private TaskModel task;

        private Builder() {
        }

        public Builder withTask(TaskModel task) {
            this.task = task;
            return this;
        }

        public UpdateTaskResult build() {
            return new UpdateTaskResult(task);
        }
    }

    @Override
    public String toString() {
        return "UpdateTaskResult{" +
                "task=" + (task != null ? task.toString() : "null") +
                '}';
    }
}
