package drsus.bmworld.client.entityrenderer

import net.minecraft.client.animation.KeyframeAnimation
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.*

class SkullCrawlerModel : EntityModel<SkullCrawlerEntityRenderState> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    var dancing: KeyframeAnimation
    var walking: KeyframeAnimation

    private var skullcrabby: ModelPart
    private var body: ModelPart
    private var skull: ModelPart
    private var arm_rechts: ModelPart
    private var arm_links: ModelPart
    private var leg_left_1: ModelPart
    private var leg_right_1: ModelPart
    private var leg_left_2: ModelPart
    private var leg_right_2: ModelPart
    private var leg_left_3: ModelPart
    private var leg_right_3: ModelPart

    constructor(root: ModelPart) : super(root) {
        this.skullcrabby = root.getChild("skullcrabby")
        this.body = this.skullcrabby.getChild("body")
        this.skull = this.body.getChild("skull")
        this.arm_rechts = this.skullcrabby.getChild("arm_rechts")
        this.arm_links = this.skullcrabby.getChild("arm_links")
        this.leg_left_1 = this.skullcrabby.getChild("leg_left_1")
        this.leg_right_1 = this.skullcrabby.getChild("leg_right_1")
        this.leg_left_2 = this.skullcrabby.getChild("leg_left_2")
        this.leg_right_2 = this.skullcrabby.getChild("leg_right_2")
        this.leg_left_3 = this.skullcrabby.getChild("leg_left_3")
        this.leg_right_3 = this.skullcrabby.getChild("leg_right_3")

        this.dancing = SkullCrawlerAnimation.dance.bake(root)
        this.walking = SkullCrawlerAnimation.walk.bake(root)
    }

    companion object {
        fun createBodyLayer(): LayerDefinition {
            val mesh = MeshDefinition()
            val root = mesh.root

            val skullcrabby = root.addOrReplaceChild(
                "skullcrabby",
                CubeListBuilder.create(),
                PartPose.offset(0.0f, 20.1111f, -0.6111f)
            )

            val body = skullcrabby.addOrReplaceChild(
                "body",
                CubeListBuilder.create().texOffs(45, 6)
                    .addBox(2.0f, -3.0f, -3.5f, 2.0f, 6.0f, 2.0f, CubeDeformation(0.0f))
                    .texOffs(0, 44).addBox(-4.0f, -3.0f, -3.5f, 2.0f, 6.0f, 2.0f, CubeDeformation(0.0f))
                    .texOffs(20, 36).addBox(-4.0f, 4.0f, 3.0f, 8.0f, 4.0f, 3.0f, CubeDeformation(0.0f))
                    .texOffs(0, 22).addBox(-5.0f, 2.0f, -3.0f, 10.0f, 6.0f, 6.0f, CubeDeformation(0.0f)),
                PartPose.offset(0.0f, -6.1111f, 0.6111f)
            )

            val skull: PartDefinition = body.addOrReplaceChild(
                "skull",
                CubeListBuilder.create().texOffs(0, 0)
                    .addBox(-5.5f, -6.5f, -4.5f, 11.0f, 11.0f, 11.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(0.0f, 1.5f, 3.5f, -0.2182f, 0.0f, 0.0f)
            )

            val arm_rechts: PartDefinition = skullcrabby.addOrReplaceChild(
                "arm_rechts",
                CubeListBuilder.create().texOffs(0, 34)
                    .addBox(-2.0f, -2.0f, -4.0f, 4.0f, 4.0f, 6.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(-6.5f, -1.1111f, -2.3889f, -0.0988f, 0.106f, 0.1179f)
            )

            val arm_links: PartDefinition = skullcrabby.addOrReplaceChild(
                "arm_links",
                CubeListBuilder.create().texOffs(32, 22)
                    .addBox(-2.0f, -3.0f, -4.5f, 4.0f, 6.0f, 8.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(6.5f, -1.1111f, -3.8889f, 0.534f, 0.1886f, 0.1104f)
            )

            val leg_left_1: PartDefinition = skullcrabby.addOrReplaceChild(
                "leg_left_1",
                CubeListBuilder.create().texOffs(42, 36)
                    .addBox(-0.5f, -0.5f, -1.0f, 9.0f, 1.0f, 2.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(5.5f, 1.3889f, -1.3889f, 0.0f, 0.2618f, 0.3491f)
            )

            val leg_right_1: PartDefinition = skullcrabby.addOrReplaceChild(
                "leg_right_1",
                CubeListBuilder.create().texOffs(20, 43)
                    .addBox(-8.5f, -0.5f, -1.0f, 9.0f, 1.0f, 2.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(-5.5f, 1.3889f, -1.3889f, 0.0f, -0.2618f, -0.3491f)
            )

            val leg_left_2: PartDefinition = skullcrabby.addOrReplaceChild(
                "leg_left_2",
                CubeListBuilder.create().texOffs(42, 39)
                    .addBox(-0.5f, -0.5f, -1.0f, 9.0f, 1.0f, 2.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(5.5f, 1.3889f, 0.6111f, 0.0f, -0.3054f, 0.3491f)
            )

            val leg_right_2: PartDefinition = skullcrabby.addOrReplaceChild(
                "leg_right_2",
                CubeListBuilder.create().texOffs(44, 0)
                    .addBox(-8.5f, -0.5f, -1.0f, 9.0f, 1.0f, 2.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(-5.5f, 1.3889f, 0.6111f, 0.0f, 0.3054f, -0.3491f)
            )

            val leg_left_3: PartDefinition = skullcrabby.addOrReplaceChild(
                "leg_left_3",
                CubeListBuilder.create().texOffs(42, 42)
                    .addBox(-0.5f, -0.5f, -1.0f, 9.0f, 1.0f, 2.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(4.5f, 1.3889f, 3.6111f, 0.0f, -0.5236f, 0.3491f)
            )

            skullcrabby.addOrReplaceChild(
                "leg_right_3",
                CubeListBuilder.create().texOffs(44, 3)
                    .addBox(-8.5f, -0.5f, -1.0f, 9.0f, 1.0f, 2.0f, CubeDeformation(0.0f)),
                PartPose.offsetAndRotation(-4.5f, 1.3889f, 3.6111f, 0.0f, 0.5236f, -0.3491f)
            )

            return LayerDefinition.create(mesh, 128, 128)
        }

    }

    override fun setupAnim(state: SkullCrawlerEntityRenderState) {
        super.setupAnim(state)

        if (state.dancingAnimationState.isStarted) {
            this.dancing.apply(state.dancingAnimationState, state.ageInTicks)
        } else {
            this.walking.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2.0f, 2.5f)
        }
    }
}






