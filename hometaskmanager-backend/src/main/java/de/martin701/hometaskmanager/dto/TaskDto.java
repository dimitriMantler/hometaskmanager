package de.martin701.hometaskmanager.dto;

import de.martin701.hometaskmanager.entities.Task;
import de.martin701.hometaskmanager.models.TaskModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public class TaskDto extends GeneralDto<Task, TaskModel, TaskDto.TaskEntityConverter> {

    public TaskDto() {
        super(Mappers.getMapper(TaskEntityConverter.class));
    }

    @Override
    public Task updateEntityValues(Task task, Task newTaskValues) {
        if (newTaskValues.getDescription() != null) task.setDescription(newTaskValues.getDescription());
        if (newTaskValues.getName() != null) task.setName(newTaskValues.getName());
        if (newTaskValues.getPriority() != null) task.setPriority(newTaskValues.getPriority());
        if (newTaskValues.getStartDate() != null) task.setStartDate(newTaskValues.getStartDate());
        if (newTaskValues.getEndDate() != null) task.setEndDate(newTaskValues.getEndDate());
        if (newTaskValues.getListId() != null) task.setListId(newTaskValues.getListId());
        if (newTaskValues.getProjectId() != null) task.setProjectId(newTaskValues.getProjectId());
        return task;
    }

    @Mapper
    public interface TaskEntityConverter extends EntityConverter<Task, TaskModel> {}
}