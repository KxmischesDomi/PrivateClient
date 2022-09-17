package de.kxmischesdomi.kxmischesdomi.mixin.jeb;

import com.mojang.blaze3d.vertex.PoseStack;
import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.client.JebEverythingModule;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(RenderLayer.class)
public abstract class RenderLayerMixin<T extends Entity> {

	@ModifyArgs(method = "renderColoredCutoutModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderToBuffer(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;IIFFFF)V"))
	private static <T extends LivingEntity> void onRender(Args args, EntityModel<T> model, ResourceLocation texture, PoseStack matrices, MultiBufferSource vertexConsumers, int light, T entity, float red, float green, float blue) {

		if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(JebEverythingModule.class)) {
			int age = entity.tickCount;
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

			args.set(4, v);
			args.set(5, w);
			args.set(6, x);
		}

	}

}
    