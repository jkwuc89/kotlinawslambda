package com.keithwedinger

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler

/**
 * LambdaRequestHandler
 *
 * @author Keith Wedinger <br>
 * Created On: 3/7/18
 */
class LambdaRequestHandler : RequestHandler<WidgetRequest, Widget> {
    override fun handleRequest(widgetRequest: WidgetRequest, context: Context): Widget {
        context.logger.log("Input: $widgetRequest")
        return Widget(widgetRequest.id, "My Widget " + widgetRequest.id)
    }
}