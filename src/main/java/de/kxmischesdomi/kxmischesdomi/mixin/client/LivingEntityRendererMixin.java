package de.kxmischesdomi.kxmischesdomi.mixin.client;

import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.fun.DinnerboneEverythingModule;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

	@Inject(method = "isEntityUpsideDown", at = @At("HEAD"), cancellable = true)
	private static void isEntityUpsideDownInject(LivingEntity livingEntity, CallbackInfoReturnable<Boolean> cir) {
		if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(DinnerboneEverythingModule.class)) {
			cir.setReturnValue(true);
		}
	}

}
