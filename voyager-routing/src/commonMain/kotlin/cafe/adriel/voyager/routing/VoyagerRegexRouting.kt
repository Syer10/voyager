package cafe.adriel.voyager.routing

import dev.programadorthi.routing.core.Route
import dev.programadorthi.routing.core.application.ApplicationCall
import dev.programadorthi.routing.core.route

public fun Route.screen(
    path: Regex,
    body: VoyagerPipelineInterceptor<Unit, ApplicationCall>,
): Route = route(path) { screen(body) }
