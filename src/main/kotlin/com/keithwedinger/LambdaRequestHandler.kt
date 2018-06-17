package com.keithwedinger

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.google.gson.Gson
import com.keithwedinger.tasktracker.data.Task
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * LambdaRequestHandler
 *
 * @author Keith Wedinger <br>
 * Created On: 3/7/18
 */
class LambdaRequestHandler : RequestHandler<NewTaskRequest, Task> {
    val TASKTRACKER_API_URL = "https://tasktrackerapp.azurewebsites.net/tasktracker-1.0.0-SNAPSHOT/api/tasks"
    val LOCAL_TASKTRACKER_API_URL = "http://localhost:8080/api/tasks"

    override fun handleRequest(newTaskRequest: NewTaskRequest, context: Context): Task {
        context.logger.log("Input: $newTaskRequest")
        return Task()
    }

    fun createNewTask(newTaskRequest: NewTaskRequest): Task {
        val newTaskDueDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val newTask = Task(name = newTaskRequest.name, assignedTo = newTaskRequest.assignedTo, dueDate = newTaskDueDate)
        val newTaskPayload = JSONObject(newTask)
        val newTaskResponse = khttp.post(TASKTRACKER_API_URL, json = newTaskPayload)
        val gson = Gson()
        val createdTask = gson.fromJson(newTaskResponse.text, Task::class.java)
        return createdTask
    }
}