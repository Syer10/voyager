package cafe.adriel.voyager.routing

import dev.programadorthi.routing.core.application
import dev.programadorthi.routing.core.install
import dev.programadorthi.routing.core.routing
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertIs
import kotlin.test.assertNotNull

internal class VoyagerNavigatorPluginTest {

    @Test
    fun shouldThrowExceptionWhenGetANotInstalledVoyagerNavigatorManager() {
        // GIVEN
        val router = routing { }
        val result = assertFails {
            // WHEN
            router.application.voyagerNavigatorManager
        }
        // THEN
        assertIs<IllegalStateException>(result)
        assertEquals(result.message, "No instance for key AttributeKey: VoyagerNavigatorManager")
    }

    @Test
    fun shouldNotThrowExceptionWhenGetAInstalledVoyagerNavigatorManager() {
        // GIVEN
        val router = routing {
            install(VoyagerNavigator)
        }
        // WHEN
        val result = router.application.voyagerNavigatorManager
        // THEN
        assertNotNull(result)
    }
}
