package com.todolist.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.todolist.models.Priority;

public class PriorityConverter implements DynamoDBTypeConverter<String, Priority> {

    @Override
    public String convert(final Priority priority) {
        return priority == null ? null : priority.name();
    }

    @Override
    public Priority unconvert(final String priorityString) {
        return priorityString == null ? null : Priority.valueOf(priorityString);
    }
}
