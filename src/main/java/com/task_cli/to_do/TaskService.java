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
                if (!isValidStatus(status)){
                    return "El estado no es valido, debe ser TO-DO, IN-PROGRESS o DONE";
                }
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
    private boolean isValidStatus(String status){
        return status.equals("TO-DO") || status.equals("IN-PROGRESS") || status.equals("DONE");
    }

    public void deleteTask(long id){
        repository.deleteById(id);
    }

    public TaskModel taskById(long id){
        return repository.findById(id).get();
    }

    public List<TaskModel> taskDone(){
     List<TaskModel> taskAllToDone = repository.findAll();
     return taskAllToDone.stream().filter(taskModel -> "DONE".equalsIgnoreCase(taskModel.getStatus())).toList();
    }

    public List<TaskModel> taskInProgress() {
        List<TaskModel> taskAllToInProgress = repository.findAll();
        return taskAllToInProgress.stream().
                filter(taskModel -> "IN-PROGRESS".equalsIgnoreCase(taskModel.getStatus())).toList();
    }

    public List<TaskModel> taskToDo(){
        List<TaskModel> taskAllToDo = repository.findAll();
        return taskAllToDo.stream().filter(taskModel -> "TO-DO".equalsIgnoreCase(taskModel.getStatus())).toList();
    }
}
