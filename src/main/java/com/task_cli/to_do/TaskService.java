package com.task_cli.to_do;

import java.sql.Timestamp;
import java.util.List;

public class TaskService {

    ITaskRepository repository;

    public TaskService(ITaskRepository repository) {
        this.repository = repository;
    }

    public TaskModel createTask(String desciption){
    TaskModel task = new TaskModel();
    task.setDescripcion(desciption);
    task.setStatus("TO-DO");
    task.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    return repository.save(task);
    }

    public List<TaskModel> allTasks(){
        return repository.findAll();
    }

    public String updateStatus(long id, String status){
        try {
            TaskModel task = repository.findById(id).get();
            if (task != null) {
                task.setStatus(status);
                task.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                repository.save(task);
                return "Task actualizada correctamente";
            }else {
                return "No se encontro la tarea con ID: "+id;
            }
        }catch (Exception e){
        return "Error al actualizar la tarea" + e.getMessage();
        }
    }


}
