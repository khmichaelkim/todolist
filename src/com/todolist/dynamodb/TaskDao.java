package com.todolist.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.todolist.dynamodb.models.Task;
import com.todolist.dynamodb.models.User;

import java.util.*;

public class TaskDao {

    private final DynamoDBMapper dynamoDbMapper;

    public TaskDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    public Task getTask(String userId, String taskId) {
        return this.dynamoDbMapper.load(Task.class, userId, taskId);
    }

    public void saveTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        this.dynamoDbMapper.save(task);
    }

    public boolean deleteTask(String userId, String taskId) {
        try {
            // Create a Task object with the primary key set
            Task taskToDelete = new Task();
            taskToDelete.setUserId(userId);
            taskToDelete.setTaskId(taskId);

            // Use DynamoDBMapper to delete the task
            dynamoDbMapper.delete(taskToDelete);

            // Assuming delete is successful if no exception is thrown
            return true;
        } catch (Exception e) {
            // Log the exception
            System.err.println("Unable to delete task: " + e.getMessage());
            return false;
        }
    }

    public String generateTaskId() {
        return UUID.randomUUID().toString();
    }

    public boolean userExists(String userId) {
        User user = this.dynamoDbMapper.load(User.class, userId);
        return user != null;
    }

    public List<Task> getTasksByUserId(String userId) {
        HashMap<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(userId));

        DynamoDBQueryExpression<Task> queryExpression = new DynamoDBQueryExpression<Task>()
                .withKeyConditionExpression("userId = :val1")
                .withExpressionAttributeValues(eav);

        PaginatedQueryList<Task> results = this.dynamoDbMapper.query(Task.class, queryExpression);

        return new ArrayList<>(results);
    }

    public List<Task> findTasksByPartialId(String userId, String partialTaskId) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("begins_with(taskId, :partialTaskId) and userId = :userId")
                .withExpressionAttributeValues(Map.of(
                        ":partialTaskId", new AttributeValue().withS(partialTaskId),
                        ":userId", new AttributeValue().withS(userId)));

        PaginatedScanList<Task> scanResult = dynamoDbMapper.scan(Task.class, scanExpression);
        return new ArrayList<>(scanResult);
    }
}
