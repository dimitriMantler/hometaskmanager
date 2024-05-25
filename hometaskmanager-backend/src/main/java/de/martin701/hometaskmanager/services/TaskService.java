package de.martin701.hometaskmanager.services;

import de.martin701.hometaskmanager.dto.GeneralDto;
import de.martin701.hometaskmanager.dto.TaskDto;
import de.martin701.hometaskmanager.entities.Task;
import de.martin701.hometaskmanager.models.TaskModel;
import de.martin701.hometaskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    private final TaskDto dto = new TaskDto();

    public List<Task> findAll(){
        return dto.writeEntities(taskRepository.findAll());
    }

    public Task findById(Long id){
        Optional<TaskModel> model = taskRepository.findById(id);
        if(model.isPresent())
            return dto.writeEntity(model.get());
        else
            return null;
    }

    public Task updateTask(Task oldTask, Task newTask){
        return dto.updateEntityValues(oldTask, newTask);
    }

    public Task save(Task task){
        TaskModel taskModel = dto.writeModel(task, GeneralDto.Action.CREATE);
        taskModel = taskRepository.save(taskModel);
        return dto.writeEntity(taskModel);
    }

    public List<String> checkValidity(Task task){
        List<String> problems = new ArrayList<>();
        //TODO check Field Sizes
        return problems;
    }

    public boolean delete(Task task){
        TaskModel taskModel = dto.writeModel(task, GeneralDto.Action.CHANGE);
        taskRepository.delete(taskModel);
        return true;
    }
}
