package drsus.bmworld.entities

import drsus.bmworld.BogusMogusWorld
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntityType.EntityFactory
import net.minecraft.world.entity.MobCategory


object ModEntityTypes {
    val SKULL_CRAWLER: EntityType<SkullCrawlerEntity> = ModEntityTypes.register<T?>(
        "skull_crawler",
        EntityType.Builder.of<SkullCrawlerEntity>(EntityFactory { SkullCrawlerEntity() }, MobCategory.MISC)
            .sized(0.75f, 1.75f)
    )

    private fun <T : Entity?> register(name: String?, builder: EntityType.Builder<T?>): EntityType<T?> {
        val key: ResourceKey<EntityType<*>?> =
            ResourceKey.create<T?>(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(BogusMogusWorld.mod_id, name))
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key))
    }

    fun registerModEntityTypes() {
        BogusMogusWorld.logger.info("Registering EntityTypes for " + BogusMogusWorld.mod_id)
    }

    fun registerAttributes() {
        FabricDefaultAttributeRegistry.register(SKULL_CRAWLER, SkullCrawlerEntity.createCubeAttributes())
    }
}