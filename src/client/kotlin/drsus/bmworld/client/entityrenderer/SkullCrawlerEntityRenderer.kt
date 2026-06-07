package drsus.bmworld.client.entityrenderer

import drsus.bmworld.BogusMogusWorld
import drsus.bmworld.entities.SkullCrawlerEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.Identifier


class SkullCrawlerEntityRenderer(context: EntityRendererProvider.Context) :
    MobRenderer<SkullCrawlerEntity, SkullCrawlerEntityRenderState, SkullCrawlerModel>(
        context,
        SkullCrawlerModel(context.bakeLayer(ModEntityModelLayers.SKULLCRAWLER)),
        0.375f
    ) {
    override fun createRenderState(): SkullCrawlerEntityRenderState {
        return SkullCrawlerEntityRenderState()
    }

    override fun getTextureLocation(livingEntityRenderState: SkullCrawlerEntityRenderState): Identifier {
        return TEXTURE
    }

    companion object {
        private val TEXTURE: Identifier =
            Identifier.fromNamespaceAndPath(BogusMogusWorld.mod_id, "textures/entity/skullcrawler.png")
    }

    override fun extractRenderState(
        livingEntity: SkullCrawlerEntity,
        livingEntityRenderState: SkullCrawlerEntityRenderState,
        f: Float
    ) {
        super.extractRenderState(livingEntity, livingEntityRenderState, f)
        livingEntityRenderState.dancingAnimationState.copyFrom(livingEntity.dancingAnimationState)
        livingEntityRenderState.walkingAnimationState.copyFrom(livingEntity.walkingAnimationState)
    }
}

