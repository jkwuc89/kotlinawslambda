package com.keithwedinger.tasktracker.data

/**
 * Task entity data class
 * Kotlin data class takes care of getters and setters
 * It also automatically generates equals(), hashcode(), toString() and copy() methods
 *
 * @author Keith Wedinger <br>
 * Created On: 6/17/18
 */
data class Task (
    val id: Long = 0,
    val name: String = "",
    val dueDate: String = "",
    val assignedTo: String = ""
)