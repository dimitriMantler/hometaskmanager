package de.martin701.hometaskmanager.dto;

import de.martin701.hometaskmanager.entities.Task;
import de.martin701.hometaskmanager.models.TaskModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TaskDto extends GeneralDto<Task, TaskModel>{

    public static List<Task> writeEntities(List<TaskModel> models){
        List<Task> entities = null;
        entities = models.stream().map(TaskDto::writeEntity).collect(Collectors.toList());
        return entities;
    }

    public static List<TaskModel> writeModels(List<Task> tasks){
        List<TaskModel> models = null;
        models = tasks.stream().map(TaskDto::writeModel).collect(Collectors.toList());
        return models;
    }

    public static Task writeEntity(TaskModel model){
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

    //TODO set userId and UserName if available
    public static TaskModel writeModel(Task task, GeneralDto.Action action){
        TaskModel model = writeModel(task);
        switch (action){
            case CREATE -> {
                model.setCreateDate(LocalDateTime.now());
                model.setCreateUserId(0);
                model.setCreateUserName("DM");
            }
            case CHANGE -> {
                model.setChangeDate(LocalDateTime.now());
                model.setChangeUserId(0);
                model.setChangeUserName("DM");
            }
        }
        return model;
    }

    public static TaskModel writeModel(Task task){
        TaskModel model = new TaskModel();
        model.setId(task.getId());
        model.setDescription(task.getDescription());
        model.setName(task.getName());
        model.setPriority(task.getPriority());
        model.setStartDate(task.getStartDate());
        model.setEndDate(task.getEndDate());
        model.setListId(task.getListId());
        model.setProjectId(task.getProjectId());
        return model;
    }

    public static Task updateTaskValues(Task task, Task newTaskValues){
        if(newTaskValues.getDescription() != null)
            task.setDescription(newTaskValues.getDescription());
        if(newTaskValues.getName() != null)
            task.setName(newTaskValues.getName());
        if(newTaskValues.getPriority() != null)
            task.setPriority(newTaskValues.getPriority());
        if(newTaskValues.getStartDate() != null)
            task.setStartDate(newTaskValues.getStartDate());
        if(newTaskValues.getEndDate() != null)
            task.setEndDate(newTaskValues.getEndDate());
        if(newTaskValues.getListId() != null)
            task.setListId(newTaskValues.getListId());
        if(newTaskValues.getProjectId() != null)
            task.setProjectId(newTaskValues.getProjectId());
        return task;
    }
}
