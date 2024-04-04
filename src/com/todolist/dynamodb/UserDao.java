package com.todolist.dynamodb;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.todolist.dynamodb.models.User;

public class UserDao {

    private final DynamoDBMapper dynamoDbMapper;

    public UserDao() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();
        this.dynamoDbMapper = new DynamoDBMapper(client);
    }

    public UserDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    public User getUser(String id) {
        return this.dynamoDbMapper.load(User.class, id);
    }

    public void saveUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Could not find user: " + user);
        }

        dynamoDbMapper.save(user);
    }
}
