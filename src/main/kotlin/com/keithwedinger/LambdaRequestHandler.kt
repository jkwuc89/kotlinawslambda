package com.keithwedinger

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger
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
    // val LOCAL_TASKTRACKER_API_URL = "http://localhost:8080/api/tasks"
    private var logger: LambdaLogger? = null

    /**
     * Request handler entry point for the AWS Lambda function
     */
    override fun handleRequest(newTaskRequest: NewTaskRequest, context: Context): Task {
        logger = context.logger
        logger?.log("handleRequest: Handling incoming task request. Input: $newTaskRequest")
        val newTask = createNewTask(newTaskRequest)
        return postNewTask(newTask)!!
    }

    /**
     * Create a new Task from the incoming task request
     */
    fun createNewTask(newTaskRequest: NewTaskRequest): Task {
        val newTaskDueDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        logger?.log("createNewTask: Creating new task with dueDate ${newTaskDueDate}")
        return Task(name = newTaskRequest.name, assignedTo = newTaskRequest.assignedTo, dueDate = newTaskDueDate)
    }

    /**
     * Post the new task to the web service
     */
    private fun postNewTask(newTask: Task): Task? {
        val newTaskPayload = JSONObject(newTask)
        logger?.log("postNewTask: Posting ${newTask} to ${TASKTRACKER_API_URL}")
        val newTaskResponse = khttp.post(TASKTRACKER_API_URL, json = newTaskPayload)
        return if (newTaskResponse.statusCode == 200) {
            logger?.log("postNewTask: Post successful. Response = ${newTaskResponse.text}")
            val gson = Gson()
            gson.fromJson(newTaskResponse.text, Task::class.java)
        } else {
            logger?.log("*** ERROR ***: Posting new task failed with status code ${newTaskResponse.statusCode}")
            null
        }
    }
}