package com.task.manager.domain;

public class Task {
	
	private int taskId;
	private String taskName;
	private String taskDescription;
	private String taskPriority;
	private String taskStatus;
	private boolean taskArchived;
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getTaskPriority() {
		return taskPriority;
	}
	public void setTaskPriority(String taskPriority) {
		this.taskPriority = taskPriority;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}	
	public boolean isTaskArchived() {
		return taskArchived;
	}
	public void setTaskArchived(boolean taskArchived) {
		this.taskArchived = taskArchived;
	}
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName
				+ ", taskDescription=" + taskDescription + ", taskPriority="
				+ taskPriority + ", taskStatus=" + taskStatus
				+ ", taskArchived=" + taskArchived + "]";
	}
	
}
