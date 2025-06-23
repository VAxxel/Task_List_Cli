package com.task_cli.to_do;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ToDoApplication implements CommandLineRunner {

	TaskService service;

	public ToDoApplication(TaskService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(ToDoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		boolean salir = false;
		while (!salir) {
			System.out.println("Task tracker CLI");
			System.out.println("1. Crear tarea");
			System.out.println("2. Mostrar todas las tareas");
			System.out.println("3. Actualizar estado de tarea");
			System.out.println("4. Eliminar tarea");
			System.out.println("5. Mostrar tareas completadas");
			System.out.println("6. Mostrar tareas en progreso");
			System.out.println("7. Mostrar tareas pendientes");
			System.out.println("8. Salir");
			System.out.print("Seleccione una opción: ");
			int opcion = scanner.nextInt();
			scanner.nextLine(); // Consumir el salto de línea después de la entrada numérica

			switch (opcion){
				case 1:
					System.out.println("Ingrese la descripcion de la nueva tarea");
					String descripcion = scanner.nextLine();
					System.out.println(service.createTask(descripcion));
				break;
				case 2:
					List<TaskModel> tasks  = service.allTasks();
					if (tasks.isEmpty()){
						System.out.println("Aun no hay tareas registradas");
					}else {
						System.out.println("Lista de tareas: ");
						tasks.forEach(taskModel -> System.out.printf("ID: %d | Descripcion: %s | Status: %s%n",
								taskModel.getTaskId(), taskModel.getDescripcion(), taskModel.getStatus()));
					}
					break;
				case 3:
					System.out.println("Ingresa el ID de la tarea a actualizar");
					Long id = scanner.nextLong();
					scanner.nextLine();
					System.out.println("Nuevo estado -> TO-DO | IN-PROGRESS | DONE");
					String status = scanner.next();
					System.out.println(service.updateStatus(id, status));
					break;
				case 4:
					System.out.println("Ingresa el ID a eliminar");
					long idEliminar = scanner.nextInt();
					scanner.nextLine();
					service.deleteTask(idEliminar);
					break;
				case 5:
					List<TaskModel> tasksDone = service.taskDone();
					if (tasksDone.isEmpty()){
						System.out.println("No hay tareas completadas(DONE)");
					}else {
						System.out.println("Tareas completadas: ");
						tasksDone.forEach(taskModel -> System.out.printf("ID: %d | Descripcion: %s | Fecha de finalizacion: %s%n",
								taskModel.getTaskId(), taskModel.getDescripcion(), taskModel.getUpdatedAt()));
					}
					break;
				case 6:
					List<TaskModel> tasksInProgress = service.taskInProgress();
					if (tasksInProgress.isEmpty()){
						System.out.println("No hay tareas en progreso(IN-PROGRESS)");
					}else {
						System.out.println("Tareas in progress: ");
						tasksInProgress.forEach(taskModel -> System.out.printf("ID: %d | Descripcion: %s | Fecha de finalizacion: %s%n",
								taskModel.getTaskId(), taskModel.getDescripcion(), taskModel.getUpdatedAt()));
					}
					break;
				case 7:
					List<TaskModel> tasksToDo = service.taskToDo();
					if (tasksToDo.isEmpty()){
						System.out.println("No hay tareas por hacer(TO-DO)");
					}else {
						System.out.println("Tareas por hacer: ");
						tasksToDo.forEach(taskModel -> System.out.printf("ID: %d | Descripcion: %s | Fecha de finalizacion: %s%n",
								taskModel.getTaskId(), taskModel.getDescripcion(), taskModel.getUpdatedAt()));
					}
					break;
				case 8:
					System.out.println("Saliendo de task tracker...");
					salir = true;
					break;
				default:
					System.out.println("Opcion no valida, intente de nuevo");
			}
		}
	}
}
