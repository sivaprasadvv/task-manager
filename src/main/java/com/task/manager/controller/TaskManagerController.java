package com.task.manager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.task.manager.domain.Task;
import com.task.manager.service.TaskManagerService;

@RestController
public class TaskManagerController {
	
	private TaskManagerService service = new TaskManagerService();
	
	@RequestMapping(value="/tasks", method=RequestMethod.GET, headers="Accept=application/json")
	public List<Task> getAllTasks() {
		return service.getAllTasks();
	}
	
	@RequestMapping(value="/tasks/archive/{taskIds}", method=RequestMethod.POST, headers="Accept=application/json")
	public List<Task> archiveAllTasks(@PathVariable int[] taskIds) {
		for (int taskId : taskIds) {
			service.archiveTask(taskId);
		}
		return service.getAllTasks();
	}
	
	@RequestMapping(value="tasks/{taskId}/{taskStatus}", method=RequestMethod.POST, headers="Accept=application/json")
	public List<Task> changeTaskStatus(@PathVariable int taskId, @PathVariable String taskStatus){
		service.changeTaskStatus(taskId, taskStatus);
		return service.getAllTasks();
	}
	
	@RequestMapping(value="tasks/{taskName}/{taskDescription}/{taskPriority}/{taskStatus}", method=RequestMethod.POST, headers="Accept=application/json")
	public List<Task> addTask(@PathVariable String taskName, @PathVariable String taskDescription, @PathVariable String taskPriority, @PathVariable String taskStatus) {
		Task task = new Task();
		task.setTaskName(taskName);
		task.setTaskDescription(taskDescription);
		task.setTaskPriority(taskPriority);
		task.setTaskStatus(taskStatus);
		service.addTask(task);
		return service.getAllTasks();
	}

}
