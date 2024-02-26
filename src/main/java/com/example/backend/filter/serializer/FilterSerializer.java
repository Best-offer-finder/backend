package com.example.backend.filter.serializer;

import com.example.backend.filter.dto.FilterDto;
import com.example.backend.filter.model.Filter;
import com.example.backend.user.serializer.UserSerializer;

import java.util.HashMap;
import java.util.Map;

public class FilterSerializer {

    private static final Map<Task, SerializationTask> serializationTasks = initializeSerializationTasks();

    public enum Task {
        BASE, USER;
    }

    private interface SerializationTask {
        void serialize(FilterDto filterDto, Filter filter);
    }

    private static Map<Task, SerializationTask> initializeSerializationTasks() {
        Map<Task, SerializationTask> tasks = new HashMap<>();
        tasks.put(Task.BASE, FilterSerializer::base);
        tasks.put(Task.USER, FilterSerializer::user);
        return tasks;
    }

    private static void base(FilterDto filterDto, Filter filter) {
        filterDto.setId(filter.getId());
        filterDto.setData(filter.getData());
    }

    private static void user(FilterDto filterDto, Filter filter) {
        filterDto.setUser(UserSerializer.serialize(filter.getUser()));
    }


    public static FilterDto serialize(Filter filter, Task... tasks) {
        FilterDto dto = new FilterDto();

        for (Task task : tasks) {
            SerializationTask serializationTask = serializationTasks.get(task);
            if (serializationTask != null)
                serializationTask.serialize(dto, filter);
        }

        return dto;
    }

}
