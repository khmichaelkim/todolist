package com.todolist.converters;

import com.todolist.dynamodb.models.Task;
import com.todolist.dynamodb.models.User;
import com.todolist.models.TaskModel;
import com.todolist.models.UserModel;

public class ModelConverter {

    public UserModel toUserModel(User user) {
        if (user == null) {
            return null;
        }

        return UserModel.builder()
                .withUserId(user.getUserId())
                .withEmail(user.getEmail())
                .withCreatedAt(user.getCreatedAt())
                .build();
    }

    public TaskModel toTaskModel(Task task) {
        if (task == null) {
            return null;
        }

        return TaskModel.builder()
                .withUserId(task.getUserId())
                .withTaskId(task.getTaskId())
                .withContent(task.getContent())
                .withDueDate(task.getDueDate())
                .withPriority(task.getPriority())
                .build();
    }

}
