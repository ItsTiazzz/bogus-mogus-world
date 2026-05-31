package drsus.bmworld.entities

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import kotlin.random.Random

class SkullCrawlerEntity(entityType: EntityType<out PathfinderMob>, world: Level) : PathfinderMob(entityType, world) {
    companion object {
        fun createCubeAttributes() : AttributeSupplier.Builder{

            return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.00)
                .add(Attributes.SCALE, Random.nextDouble(1.00, 2.00))
                .add(Attributes.MOVEMENT_SPEED, 0.3)
        }
    }
}