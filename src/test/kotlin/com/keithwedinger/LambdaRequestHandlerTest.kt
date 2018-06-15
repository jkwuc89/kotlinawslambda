package com.keithwedinger

import org.junit.jupiter.api.Test

/**
 * Tests for LambdaRequestHandler
 * @author Keith Wedinger <br></br>
 * Created On: 6/14/18
 */
class LambdaRequestHandlerTest {
    private var testHandler = LambdaRequestHandler()

    @Test
    internal fun testGetBreweryInfo() {
        testHandler.getBreweryInfo()
    }
}