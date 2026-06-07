package drsus.bmworld.entities

import drsus.bmworld.BogusMogusWorld.logger
import net.minecraft.core.BlockPos
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.AnimationState
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.storage.ValueInput
import net.minecraft.world.level.storage.ValueOutput
import kotlin.jvm.optionals.getOrElse
import kotlin.random.Random


class SkullCrawlerEntity(entityType: EntityType<out PathfinderMob>, world: Level) : PathfinderMob(entityType, world) {
    private var dancingTimeLeft = 0
    private var jukebox: BlockPos? = null
    val dancingAnimationState: AnimationState = AnimationState()
    val walkingAnimationState: AnimationState = AnimationState()

    companion object {
        val dancing: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(SkullCrawlerEntity::class.java, EntityDataSerializers.BOOLEAN)

        fun createCubeAttributes(): AttributeSupplier.Builder {

            return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.00)
                .add(Attributes.SCALE, Random.nextDouble(0.90, 1.10))
                .add(Attributes.MOVEMENT_SPEED, 0.22)
                .add(Attributes.TEMPT_RANGE, 20.00)
        }

    }

    override fun registerGoals() {
        this.goalSelector.addGoal(0, PanicGoal(this, 1.25))
        this.goalSelector.addGoal(1, TemptGoal(this, 1.0, Ingredient.of(Items.WHEAT), false))
        this.goalSelector.addGoal(2, RandomStrollGoal(this, 1.0))
        this.goalSelector.addGoal(3, LookAtPlayerGoal(this, Player::class.java, 4f))
        this.goalSelector.addGoal(4, RandomLookAroundGoal(this))
    }

    fun isDancing(): Boolean {
        return entityData.get(dancing)
    }


    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(dancing, false)
    }

    private fun setDancing(dancing: Boolean) {
        entityData.set(Companion.dancing, dancing)
    }

    override fun updateWalkAnimation(f: Float) {
        super.updateWalkAnimation(f)
    }

    override fun mobInteract(player: Player, interactionHand: InteractionHand): InteractionResult {
        val itemStack = player.getItemInHand(interactionHand)
        if (itemStack.`is`(Items.WHEAT)) {
            val var5 = this.level()
            if (var5 is ServerLevel) {
                setDancing(true)
                this.dancingTimeLeft = 100
                dancingAnimationState.start(this.tickCount)
            }
            return InteractionResult.CONSUME
        } else {
            return super.mobInteract(player, interactionHand)
        }
    }

    override fun tick() {
        super.tick()

        if (!level().isClientSide) {
            logger.info("[World] Dancing: ${isDancing()}, ticks: ${this.tickCount}, left: $dancingTimeLeft")
            if (isDancing()) {
                logger.info(dancingTimeLeft.toString())
                if (dancingTimeLeft-- <= 0) {
                    setDancing(false)
                }
            }
        }
    }

    override fun addAdditionalSaveData(valueOutput: ValueOutput) {
        super.addAdditionalSaveData(valueOutput)
        valueOutput.putInt("dancing_time_left", dancingTimeLeft)
    }

    override fun readAdditionalSaveData(valueInput: ValueInput) {
        super.readAdditionalSaveData(valueInput)
        dancingTimeLeft = valueInput.getInt("dancing_time_left").getOrElse { 0 }
    }

    override fun onSyncedDataUpdated(entityDataAccessor: EntityDataAccessor<*>) {
        super.onSyncedDataUpdated(entityDataAccessor)

        if (entityDataAccessor == dancing) {
            logger.info("[Data changed] Dancing: ${isDancing()}, ticks: ${this.tickCount}, left: $dancingTimeLeft")
            dancingAnimationState.animateWhen(isDancing(), this.tickCount)
        }
    }
}