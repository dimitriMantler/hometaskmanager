package de.martin701.hometaskmanager.dto;

import de.martin701.hometaskmanager.entities.Task;
import de.martin701.hometaskmanager.models.TaskModel;

import java.util.List;

public class TaskDto extends GeneralDto<Task, TaskModel>{

    public static List<Task> writeEntities(List<TaskModel> models) {
        return GeneralDto.writeEntities(models, new TaskEntityConverter());
    }

    public static List<TaskModel> writeModels(List<Task> tasks, Action action) {
        return GeneralDto.writeModels(tasks, action, new TaskEntityConverter());
    }

    public static Task writeEntity(TaskModel model) {
        return GeneralDto.writeEntity(model, new TaskEntityConverter());
    }

    public static TaskModel writeModel(Task task, Action action) {
        return GeneralDto.writeModel(task, action, new TaskEntityConverter());
    }

    public static Task updateTaskValues(Task task, Task newTaskValues) {
        if (newTaskValues.getDescription() != null) task.setDescription(newTaskValues.getDescription());
        if (newTaskValues.getName() != null) task.setName(newTaskValues.getName());
        if (newTaskValues.getPriority() != null) task.setPriority(newTaskValues.getPriority());
        if (newTaskValues.getStartDate() != null) task.setStartDate(newTaskValues.getStartDate());
        if (newTaskValues.getEndDate() != null) task.setEndDate(newTaskValues.getEndDate());
        if (newTaskValues.getListId() != null) task.setListId(newTaskValues.getListId());
        if (newTaskValues.getProjectId() != null) task.setProjectId(newTaskValues.getProjectId());
        return task;
    }

    private static class TaskEntityConverter implements EntityConverter<Task, TaskModel> {
        @Override
        public Task toEntity(TaskModel model) {
            Task task = new Task();
            task.setId(model.getId());
            task.setDescription(model.getDescription());
            task.setName(model.getName());
            task.setPriority(model.getPriority());
            task.setEndDate(model.getEndDate());
            task.setListId(model.getListId());
            task.setProjectId(model.getProjectId());
            task.setStartDate(model.getStartDate());
            return task;
        }

        @Override
        public TaskModel toModel(Task entity) {
            TaskModel model = new TaskModel();
            model.setId(entity.getId());
            model.setDescription(entity.getDescription());
            model.setName(entity.getName());
            model.setPriority(entity.getPriority());
            model.setStartDate(entity.getStartDate());
            model.setEndDate(entity.getEndDate());
            model.setListId(entity.getListId());
            model.setProjectId(entity.getProjectId());
            return model;
        }
    }
}
