package com.example.backend.user.serializer;

import com.example.backend.filter.serializer.FilterSerializer;
import com.example.backend.user.dto.UserDto;
import com.example.backend.user.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserSerializer {

    private static final Map<Task, SerializationTask> serializationTasks = initializeSerializationTasks();

    public enum Task {
        BASE, FILTERS;
    }

    private interface SerializationTask {
        void serialize(UserDto userDto, User user);
    }

    private static Map<Task, SerializationTask> initializeSerializationTasks() {
        Map<Task, SerializationTask> tasks = new HashMap<>();
        tasks.put(Task.BASE, UserSerializer::base);
        tasks.put(Task.FILTERS, UserSerializer::filters);
        return tasks;
    }

    private static void base(UserDto userDto, User user) {
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
    }

    private static void filters(UserDto userDto, User user) {
        userDto.setFilters(user.getFilters().stream().map((filter) -> FilterSerializer.serialize(filter, FilterSerializer.Task.BASE)).toList());
    }


    public static UserDto serialize(User user, Task... tasks) {
        UserDto dto = new UserDto();

        for (Task task : tasks) {
            SerializationTask serializationTask = serializationTasks.get(task);
            if (serializationTask != null)
                serializationTask.serialize(dto, user);
        }

        return dto;
    }

}
