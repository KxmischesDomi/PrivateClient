package de.kxmischesdomi.kxmischesdomi.mixin;

import de.kxmischesdomi.kxmischesdomi.KxmischesDomiMod;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author KxmischesDomi | https://github.com/kxmischesdomi
 * @since 1.0
 */
@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Inject(method = "pauseGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V"))
	private void pauseGameMixin(boolean bl, CallbackInfo ci) {
		KxmischesDomiMod.getInstance().getConfigLoader().save();
	}

}
