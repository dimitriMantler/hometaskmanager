package de.martin701.hometaskmanager.dto;

import de.martin701.hometaskmanager.entities.Task;
import de.martin701.hometaskmanager.models.TaskModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

public class TaskDto extends GeneralDto<Task, TaskModel, TaskDto.TaskEntityConverter> {

    public TaskDto() {
        super(Mappers.getMapper(TaskEntityConverter.class));
    }

    @Override
    public Task updateEntityValues(Task task, Task newTaskValues) {
        Optional.ofNullable(newTaskValues.getDescription()).ifPresent(task::setDescription);
        Optional.ofNullable(newTaskValues.getName()).ifPresent(task::setName);
        Optional.ofNullable(newTaskValues.getPriority()).ifPresent(task::setPriority);
        Optional.ofNullable(newTaskValues.getStartDate()).ifPresent(task::setStartDate);
        Optional.ofNullable(newTaskValues.getEndDate()).ifPresent(task::setEndDate);
        Optional.ofNullable(newTaskValues.getListId()).ifPresent(task::setListId);
        Optional.ofNullable(newTaskValues.getProjectId()).ifPresent(task::setProjectId);
        return task;
    }

    @Mapper
    public interface TaskEntityConverter extends EntityConverter<Task, TaskModel> {}
}