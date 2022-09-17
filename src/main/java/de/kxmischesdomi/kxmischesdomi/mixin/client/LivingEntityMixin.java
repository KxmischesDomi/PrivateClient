package de.kxmischesdomi.kxmischesdomi.mixin.client;

import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import de.kxmischesdomi.kxmischesdomi.module.modules.client.NoEffectParticlesModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Inject(method = "tickEffects", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"), cancellable = true)
	public void addParticleMixin(CallbackInfo ci) {
		if (KxmischesDomiMod.getInstance().getModuleWrapper().isModuleEnabled(NoEffectParticlesModule.class)) {
			if ((Object) this instanceof LocalPlayer player && player == Minecraft.getInstance().player) {
				ci.cancel();
			}
		}
	}

}
