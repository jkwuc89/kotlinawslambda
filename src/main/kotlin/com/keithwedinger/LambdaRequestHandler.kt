package com.keithwedinger

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import khttp.get

/**
 * LambdaRequestHandler
 *
 * @author Keith Wedinger <br>
 * Created On: 3/7/18
 */
class LambdaRequestHandler : RequestHandler<WidgetRequest, Widget> {
    val BEER_DB_API_BASE_URL = "http://prost.herokuapp.com/api/v1"
    val GET_BREWERY_URL = "${BEER_DB_API_BASE_URL}/brewery"

    override fun handleRequest(widgetRequest: WidgetRequest, context: Context): Widget {
        context.logger.log("Input: $widgetRequest")
        return Widget(widgetRequest.id, "My Widget " + widgetRequest.id)
    }

    fun getBreweryInfo() {
        val response = khttp.get("${GET_BREWERY_URL}/founders")
        println(response)
    }
}