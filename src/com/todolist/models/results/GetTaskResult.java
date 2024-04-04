package com.todolist.models.results;

import com.todolist.models.TaskModel;
import java.util.List;
import java.util.Objects;

public class GetTaskResult {

    private List<TaskModel> tasks;

    public GetTaskResult() {
    }

    public GetTaskResult(List<TaskModel> tasks) {
        this.tasks = tasks;
    }

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<TaskModel> tasks;

        private Builder() {
        }

        public Builder withTasks(List<TaskModel> tasks) {
            this.tasks = tasks;
            return this;
        }

        public GetTaskResult build() {
            return new GetTaskResult(this.tasks);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetTaskResult)) return false;
        GetTaskResult that = (GetTaskResult) o;
        return Objects.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks);
    }

    @Override
    public String toString() {
        return "GetTaskResult{" +
                "tasks=" + tasks +
                '}';
    }
}
