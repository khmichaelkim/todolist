package com.todolist.models.results;

import com.todolist.models.TaskModel;

import java.util.Objects;

public class CreateTaskResult {

    private TaskModel task;

    public CreateTaskResult() {
    }

    public CreateTaskResult(TaskModel task) {
        this.task = task;
    }

    public TaskModel getTask() {
        return task;
    }

    public void setTask(TaskModel task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTaskResult that = (CreateTaskResult) o;
        return Objects.equals(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }

    @Override
    public String toString() {
        return "CreateTaskResult{" +
                "task=" + task +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private TaskModel task;

        public Builder withTask(TaskModel task) {
            this.task = task;
            return this;
        }

        public CreateTaskResult build() {
            return new CreateTaskResult(task);
        }
    }
}
