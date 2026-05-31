package drsus.bmworld

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object BogusMogusWorld : ModInitializer {
    val logger = LoggerFactory.getLogger("bogus-mogus-world")
    val mod_id = "bogusmogusworld"


    override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hello Fabric world!")
	}
}