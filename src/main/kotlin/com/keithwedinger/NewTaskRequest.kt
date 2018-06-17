package com.keithwedinger

/**
 * AWS Lambda Request class implementation
 *
 * @author Keith Wedinger <br>
 * Created On: 3/8/18
 */
data class NewTaskRequest (
    val name: String = "",
    val assignedTo: String = ""
)