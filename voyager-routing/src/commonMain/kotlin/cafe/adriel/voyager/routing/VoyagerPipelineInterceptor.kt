package cafe.adriel.voyager.routing

import cafe.adriel.voyager.core.screen.Screen
import io.ktor.util.pipeline.PipelineContext

public typealias VoyagerPipelineInterceptor<TSubject, TContext> =
    suspend PipelineContext<TSubject, TContext>.(TSubject) -> Screen
