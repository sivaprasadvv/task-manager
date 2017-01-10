package com.task.manager.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.task.manager.domain.Task;
import com.task.manager.util.DBUtility;

public class TaskManagerService {
	
	private Connection connection;
	
	public TaskManagerService() {
		connection = DBUtility.getConnection();
	}
	
	public void addTask(Task task) {
		try {
		PreparedStatement preparedStatement = connection
			     .prepareStatement("insert into task_list(task_name,task_description,task_priority,task_status,task_archived,task_start_time,task_end_time) values (?, ?, ?,?,?,?,?)");
			   System.out.println("Task:"+task.getTaskName());
			   preparedStatement.setString(1, task.getTaskName());
			   preparedStatement.setString(2, task.getTaskDescription());   
			   preparedStatement.setString(3, task.getTaskPriority());
			   preparedStatement.setString(4, task.getTaskStatus());
			   preparedStatement.setInt(5,0);
			   Date dt = new Date();
			 
			   SimpleDateFormat sdf = 
			        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 
			   String currentTime = sdf.format(dt);
			   preparedStatement.setString(6,currentTime);
			   preparedStatement.setString(7,currentTime);
			   preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void archiveTask(int taskId) {
		String sql = "update task_list set task_archived=? where task_id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setBoolean(1, true);
			ps.setInt(2, taskId);			
			int result =  ps.executeUpdate();
			System.out.println("is archiveTask success :" +result);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTask(Task task) {
		try {
			   PreparedStatement preparedStatement = connection
			     .prepareStatement("update task_list set task_name=?, task_description=?, task_priority=?,task_status=?" +
			       "where task_id=?");
			   preparedStatement.setString(1, task.getTaskName());
			   preparedStatement.setString(2, task.getTaskDescription());
			    
			   preparedStatement.setString(3, task.getTaskPriority());
			   preparedStatement.setString(4, task.getTaskStatus());
			   preparedStatement.setInt(4, task.getTaskId());
			   preparedStatement.executeUpdate();
			 
			  } catch (SQLException e) {
			   e.printStackTrace();
			  }
	}
	
	public void changeTaskStatus(int taskId, String status) {
		String sql = "update task_list set task_status=? where task_id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, taskId);			
			int result =  ps.executeUpdate();
			System.out.println("is changeTaskStatus success :" +result);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Task> getAllTasks() {
		List<Task> tasks = new ArrayList<Task>();
		String sql = "select * from task_list";
		try {
			Statement ps = connection.createStatement();
			ResultSet rs =  ps.executeQuery(sql);
			while (rs.next()) {
				Task task = new Task();
				task.setTaskId(rs.getInt("task_id"));
				task.setTaskName(rs.getString("task_name"));
				task.setTaskDescription(rs.getString("task_description"));
				task.setTaskStatus(rs.getString("task_status"));
				task.setTaskPriority(rs.getString("task_priority"));
				task.setTaskArchived(rs.getBoolean("task_archived"));
				tasks.add(task);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;		
	}
	
	public Task getTaskById(int taskId) {
		Task task = null;
		String sql = "select * from task_list where task_id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, taskId);
			ResultSet rs =  ps.executeQuery();
			if (rs.next()) {
				task = new Task();
				task.setTaskId(rs.getInt("task_id"));
				task.setTaskName(rs.getString("task_name"));
				task.setTaskDescription(rs.getString("task_description"));
				task.setTaskStatus(rs.getString("task_status"));
				task.setTaskPriority(rs.getString("task_priority"));
				task.setTaskArchived(rs.getBoolean("task_archived"));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;
	}
	

}
