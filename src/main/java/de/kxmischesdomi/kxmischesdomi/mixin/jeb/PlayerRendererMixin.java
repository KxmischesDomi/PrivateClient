package de.kxmischesdomi.kxmischesdomi.mixin.jeb;

import com.mojang.blaze3d.vertex.PoseStack;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.fun.JebEverythingModule;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {

	@Inject(method = "renderHand", at = @At(value = "INVOKE", shift = At.Shift.AFTER, target = "Lnet/minecraft/client/model/PlayerModel;setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V"), cancellable = true)
	public void renderHandMixin(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer abstractClientPlayer, ModelPart modelPart, ModelPart modelPart2, CallbackInfo ci) {

		if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(JebEverythingModule.class)) {
			int age = abstractClientPlayer.tickCount;
			int n = age / 25;
			int o = DyeColor.values().length;
			int p = n % o;
			int q = (n + 1) % o;
			float r = ((float)(age % 25)) / 25.0F;
			float[] fs = Sheep.getColorArray(DyeColor.byId(p));
			float[] gs = Sheep.getColorArray(DyeColor.byId(q));
			float v = fs[0] * (1.0F - r) + gs[0] * r;
			float w = fs[1] * (1.0F - r) + gs[1] * r;
			float x = fs[2] * (1.0F - r) + gs[2] * r;

			modelPart.xRot = 0.0f;
			modelPart.render(poseStack, multiBufferSource.getBuffer(RenderType.entitySolid(abstractClientPlayer.getSkinTextureLocation())), i, OverlayTexture.NO_OVERLAY, 255, v, w, x);
			modelPart.xRot = 0.0f;
			modelPart2.render(poseStack, multiBufferSource.getBuffer(RenderType.entityTranslucent(abstractClientPlayer.getSkinTextureLocation())), i, OverlayTexture.NO_OVERLAY, 255, v, w, x);
			ci.cancel();
		}
	}

}
