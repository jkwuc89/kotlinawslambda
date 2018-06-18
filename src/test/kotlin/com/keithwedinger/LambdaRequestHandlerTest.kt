package com.keithwedinger

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Tests for LambdaRequestHandler
 * @author Keith Wedinger <br></br>
 * Created On: 6/14/18
 */
class LambdaRequestHandlerTest {
    private var testHandler = LambdaRequestHandler()

    @Test
    internal fun testCreateNewTask() {
        val expectedNewTaskDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val newTask = testHandler.createNewTask(NewTaskRequest("Test Task", "Assignee"))
        Assertions.assertEquals("Test Task", newTask.name)
        Assertions.assertEquals("Assignee", newTask.assignedTo)
        Assertions.assertEquals(expectedNewTaskDate, newTask.dueDate)
    }
}